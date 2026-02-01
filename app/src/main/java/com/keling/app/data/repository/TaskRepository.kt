package com.keling.app.data.repository

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.Gson
import com.google.gson.JsonSyntaxException
import com.keling.app.data.local.dao.TaskDao
import com.keling.app.data.model.*
import com.keling.app.data.repository.AchievementRepository
import com.keling.app.data.repository.AchievementEvent
import com.keling.app.data.task.GradeTaskGenerator
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map
import java.util.UUID
import javax.inject.Inject
import kotlin.math.pow

interface TaskRepository {
    fun getActiveTasks(): Flow<List<Task>>
    fun getActiveTasksForGrade(grade: String): Flow<List<Task>>
    fun getTasksForGrade(grade: String): Flow<List<Task>>
    fun getTasksByType(type: TaskType): Flow<List<Task>>
    fun getTasksByStatus(status: TaskStatus): Flow<List<Task>>
    suspend fun getTaskById(taskId: String): Task?
    suspend fun completeTask(taskId: String): Result<Task>
    suspend fun updateTaskProgress(taskId: String, progress: Float)
    fun getCompletedTaskCount(): Flow<Int>
    fun getTodayStudyMinutes(): Flow<Int>
    suspend fun generateDynamicTask(user: User, knowledgeGraph: Map<String, Float>): Task
    /** 按年级生成可执行任务并写入 DB，返回新生成列表 */
    suspend fun generateAndSaveTasksForGrade(grade: String): List<Task>
    /** 保存一组任务到本地数据库（用于 AI 生成任务落盘） */
    suspend fun saveTasks(tasks: List<Task>)
    /** 记录一次来自任务的学习时长 */
    suspend fun recordStudyFromTask(task: Task, source: TaskActionType)
    /** 记录一次手动专注学习时长（番茄钟等） */
    suspend fun recordManualStudy(durationMinutes: Int)
    /** 测验：提交选项下标列表，返回校验结果；通过则自动完成任务 */
    suspend fun submitQuizCompletion(taskId: String, answers: List<Int>): TaskCompletionResult
    /** 阅读/视频/背诵：用户确认完成 */
    suspend fun submitReadingCompletion(taskId: String): TaskCompletionResult
    suspend fun submitVideoCompletion(taskId: String): TaskCompletionResult
    suspend fun submitMemorizationCompletion(taskId: String): TaskCompletionResult
    /** 习题：已勾选数量，当 totalCount 满足时完成 */
    suspend fun submitExerciseCompletion(taskId: String, checkedCount: Int): TaskCompletionResult
    /** 解析任务载荷，供执行页使用 */
    fun parseQuizPayload(task: Task): QuizPayload?
    fun parseReadingPayload(task: Task): ReadingPayload?
    fun parseExercisePayload(task: Task): ExercisePayload?
    fun parseVideoPayload(task: Task): VideoPayload?
    fun parseMemorizationPayload(task: Task): MemorizationPayload?

    /** 确保为指定年级生成当日的固定日常任务（预习、复习、校园跑等） */
    suspend fun ensureDailyTasksForToday(grade: String)

    /** 根据标题完成一条挑战任务（用于实践星球报名的活动完成） */
    suspend fun completeChallengeTaskByTitle(title: String)

    // 新增：更新单个 Task（最小、语义清晰）
    suspend fun update(task: Task)
}

/**
 * 预置的日常任务模板：围绕大学生健康学习生活的一天设计
 */
data class DailyTaskTemplate(
    val id: String,
    val title: String,
    val description: String,
    val exp: Int,
    val coin: Int = 0,
    val estimatedMinutes: Int = 20
)

private val defaultDailyTemplates = listOf(
    DailyTaskTemplate(
        id = "daily_preview",
        title = "课程预习",
        description = "根据明日课表预习 1~2 门课程，粗略浏览教材或课件，标记不懂的地方。",
        exp = 40,
        estimatedMinutes = 30
    ),
    DailyTaskTemplate(
        id = "daily_review",
        title = "课程复习",
        description = "复习今天一门课的笔记，整理 3~5 条容易混淆的知识点。",
        exp = 40,
        estimatedMinutes = 30
    ),
    DailyTaskTemplate(
        id = "daily_homework",
        title = "完成作业",
        description = "选择一门目前压力最大的课程，高质量完成当日布置的作业或实验。",
        exp = 60,
        estimatedMinutes = 45
    ),
    DailyTaskTemplate(
        id = "daily_run",
        title = "校园跑步 / 快走",
        description = "在校园跑步或快走累计 30 分钟以上，注意热身与拉伸。",
        exp = 50,
        estimatedMinutes = 30
    ),
    DailyTaskTemplate(
        id = "daily_reading",
        title = "阅读 30 分钟",
        description = "阅读专业书籍或非虚构类图书 30 分钟，提高专注与信息获取能力。",
        exp = 30,
        estimatedMinutes = 30
    ),
    DailyTaskTemplate(
        id = "daily_sleep",
        title = "按时早睡",
        description = "在 23:30 前关灯准备睡觉，保证充足睡眠。",
        exp = 20,
        estimatedMinutes = 10
    ),
    DailyTaskTemplate(
        id = "daily_water",
        title = "健康饮水与三餐",
        description = "今天保证 3 次正餐与足量饮水，减少含糖饮料摄入。",
        exp = 20,
        estimatedMinutes = 10
    ),
    DailyTaskTemplate(
        id = "daily_club",
        title = "社团 / 兴趣活动",
        description = "参与一次社团活动或兴趣练习，如乐队排练、篮球、舞蹈等。",
        exp = 40,
        estimatedMinutes = 40
    )
)

class TaskRepositoryImpl @Inject constructor(
    private val taskDao: TaskDao,
    private val gradeTaskGenerator: GradeTaskGenerator,
    private val gson: Gson,
    private val achievementRepository: AchievementRepository,
    private val userRepository: UserRepository
) : TaskRepository {

    private val difficultyAlpha = 0.25f
    private val difficultyBeta = 1.5f
    override suspend fun update(task: Task) {
        taskDao.updateTask(task)
    }
    override fun getActiveTasks(): Flow<List<Task>> = taskDao.getActiveTasks()

    override fun getActiveTasksForGrade(grade: String): Flow<List<Task>> =
        taskDao.getActiveTasksForGrade(grade)

    override fun getTasksForGrade(grade: String): Flow<List<Task>> =
        taskDao.getTasksForGrade(grade)

    override fun getTasksByType(type: TaskType): Flow<List<Task>> {
        return taskDao.getTasksByType(type)
    }

    override fun getTasksByStatus(status: TaskStatus): Flow<List<Task>> {
        return taskDao.getTasksByStatus(status)
    }

    override suspend fun getTaskById(taskId: String): Task? {
        return taskDao.getTaskById(taskId)
    }

    override suspend fun completeTask(taskId: String): Result<Task> {
        return try {
            val task = taskDao.getTaskById(taskId)
                ?: return Result.failure(Exception("任务不存在"))
            
            taskDao.completeTask(
                taskId = taskId,
                status = TaskStatus.COMPLETED,
                completedAt = System.currentTimeMillis()
            )
            
            val updatedTask = task.copy(
                status = TaskStatus.COMPLETED,
                progress = 1f,
                completedAt = System.currentTimeMillis()
            )
            
            Result.success(updatedTask)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    override suspend fun updateTaskProgress(taskId: String, progress: Float) {
        val task = taskDao.getTaskById(taskId) ?: return
        val newStatus = when {
            progress >= 1f -> TaskStatus.COMPLETED
            progress > 0f -> TaskStatus.IN_PROGRESS
            else -> TaskStatus.PENDING
        }
        taskDao.updateTask(task.copy(progress = progress, status = newStatus))
    }

    override fun getCompletedTaskCount(): Flow<Int> {
        return taskDao.getCompletedTaskCount()
    }

    override fun getTodayStudyMinutes(): Flow<Int> {
        return taskDao.getStudyMinutesForDay(currentDayKey())
    }

    /**
     * 动态难度算法生成任务
     * D_new = D_base × (1 + α×(P_current/P_target)^β)
     */
    override suspend fun generateDynamicTask(user: User, knowledgeGraph: Map<String, Float>): Task {
        // 找出薄弱章节
        val weakPoint = knowledgeGraph.minByOrNull { it.value }
        val targetChapter = weakPoint?.key ?: "default"
        val currentProgress = weakPoint?.value ?: 0.5f
        val targetProgress = 0.8f

        // 计算动态难度
        val baseDifficulty = 1f
        val ratio = currentProgress / targetProgress
        val dynamicFactor = 1 + difficultyAlpha * ratio.pow(difficultyBeta)
        val calculatedDifficulty = baseDifficulty * dynamicFactor

        val (difficulty, type, content) = when {
            calculatedDifficulty < 0.5f -> Triple(
                TaskDifficulty.EASY,
                TaskType.REVIEW,
                "完成${targetChapter}课后习题1-5"
            )
            calculatedDifficulty < 1f -> Triple(
                TaskDifficulty.MEDIUM,
                TaskType.PRACTICE,
                "观看${targetChapter}微课视频+完成练习"
            )
            calculatedDifficulty < 1.5f -> Triple(
                TaskDifficulty.HARD,
                TaskType.CHALLENGE,
                "${targetChapter}综合应用训练"
            )
            else -> Triple(
                TaskDifficulty.EXPERT,
                TaskType.CHALLENGE,
                "参与${targetChapter}编程实战项目"
            )
        }

        val expReward = when (difficulty) {
            TaskDifficulty.EASY -> 20
            TaskDifficulty.MEDIUM -> 50
            TaskDifficulty.HARD -> 100
            TaskDifficulty.EXPERT -> 200
        }

        val task = Task(
            id = UUID.randomUUID().toString(),
            title = "${targetChapter}学习任务",
            description = content,
            type = type,
            difficulty = difficulty,
            experienceReward = expReward,
            coinReward = expReward / 2,
            estimatedMinutes = when (difficulty) {
                TaskDifficulty.EASY -> 15
                TaskDifficulty.MEDIUM -> 30
                TaskDifficulty.HARD -> 45
                TaskDifficulty.EXPERT -> 60
            },
            deadline = System.currentTimeMillis() + 24 * 60 * 60 * 1000 // 24小时后
        )

        taskDao.insertTask(task)
        return task
    }

    override suspend fun generateAndSaveTasksForGrade(grade: String): List<Task> {
        val tasks = gradeTaskGenerator.generateForGrade(grade)
        taskDao.insertTasks(tasks)
        return tasks
    }

    override suspend fun saveTasks(tasks: List<Task>) {
        if (tasks.isNotEmpty()) {
            taskDao.insertTasks(tasks)
        }
    }

    override suspend fun recordStudyFromTask(task: Task, source: TaskActionType) {
        val minutes: Int = when (source) {
            TaskActionType.READING, TaskActionType.VIDEO -> {
                val payloadMinutes = when (source) {
                    TaskActionType.READING -> parsePayload<ReadingPayload>(task.actionType, task.actionPayload)?.durationMinutes
                    TaskActionType.VIDEO -> parsePayload<VideoPayload>(task.actionType, task.actionPayload)?.durationMinutes
                    else -> null
                }
                (payloadMinutes ?: task.estimatedMinutes ?: 0).coerceAtLeast(1)
            }
            TaskActionType.EXERCISE, TaskActionType.MEMORIZATION, TaskActionType.QUIZ -> {
                (task.estimatedMinutes ?: 0).coerceAtLeast(1)
            }
            else -> {
                // 默认兜底：使用估算时长或 1 分钟
                (task.estimatedMinutes ?: 0).coerceAtLeast(1)
            }
        }
// 在使用 session 的地方添加或恢复下面的构造（确保在同一函数/作用域中）
        val session = StudySession(
            id = UUID.randomUUID().toString(),      // 要和 StudySession 实体的 id 类型匹配（建议为 String）
            dayKey = currentDayKey(),
            source = "TASK_${source.name}",         // 或其它合适的 source 字符串
            taskId = task.id,                       // 若是手动记录，taskId 可能为 null
            durationMinutes = minutes.coerceAtLeast(1)
        )
        taskDao.insertStudySession(session)
    }

    override suspend fun recordManualStudy(durationMinutes: Int) {
        if (durationMinutes <= 0) return
        val session = StudySession(
            id = UUID.randomUUID().toString(),
            dayKey = currentDayKey(),
            source = "FOCUS",
            taskId = null,
            durationMinutes = durationMinutes.coerceAtLeast(1)
        )
        taskDao.insertStudySession(session)
    }

    private fun currentDayKey(): String {
        val cal = java.util.Calendar.getInstance()
        val year = cal.get(java.util.Calendar.YEAR)
        val month = cal.get(java.util.Calendar.MONTH) + 1
        val day = cal.get(java.util.Calendar.DAY_OF_MONTH)
        return String.format("%04d-%02d-%02d", year, month, day)
    }

    override suspend fun submitQuizCompletion(taskId: String, answers: List<Int>): TaskCompletionResult {
        val task = taskDao.getTaskById(taskId) ?: return TaskCompletionResult.Failure("任务不存在")
        val payload = parsePayload<QuizPayload>(task.actionType, task.actionPayload)
            ?: return TaskCompletionResult.Failure("任务格式错误")
        if (task.actionType != TaskActionType.QUIZ.name) return TaskCompletionResult.Failure("任务类型不是测验")
        val questions = payload.questions
        if (answers.size != questions.size) return TaskCompletionResult.Failure("答题数量与题目数不一致")
        var correct = 0
        for (i in questions.indices) {
            if (answers[i] == questions[i].correctIndex) correct++
        }
        val score = correct.toFloat() / questions.size
        return if (score >= payload.passRate) {
            val completedAt = System.currentTimeMillis()
            val updated = task.copy(progress = 1f, status = TaskStatus.COMPLETED, completedAt = completedAt)
            taskDao.updateTask(updated)
            recordStudyFromTask(updated, TaskActionType.QUIZ)
            TaskCompletionResult.Success(score, "通过！正确 $correct/${questions.size}，得分 ${(score * 100).toInt()}%")
        } else {
            TaskCompletionResult.Failure("未达到通过线 ${(payload.passRate * 100).toInt()}%。正确 $correct/${questions.size}，得分 ${(score * 100).toInt()}%")
        }
    }

    override suspend fun submitReadingCompletion(taskId: String): TaskCompletionResult =
        markConfirmCompletion(taskId, TaskActionType.READING)

    override suspend fun submitVideoCompletion(taskId: String): TaskCompletionResult =
        markConfirmCompletion(taskId, TaskActionType.VIDEO)

    override suspend fun submitMemorizationCompletion(taskId: String): TaskCompletionResult =
        markConfirmCompletion(taskId, TaskActionType.MEMORIZATION)

    override suspend fun submitExerciseCompletion(taskId: String, checkedCount: Int): TaskCompletionResult {
        val task = taskDao.getTaskById(taskId) ?: return TaskCompletionResult.Failure("任务不存在")
        if (task.actionType != TaskActionType.EXERCISE.name) return TaskCompletionResult.Failure("任务类型不是习题")
        val payload = parsePayload<ExercisePayload>(task.actionType, task.actionPayload)
            ?: return TaskCompletionResult.Failure("任务格式错误")
        return if (checkedCount >= payload.totalCount) {
            val completedAt = System.currentTimeMillis()
            val updated = task.copy(progress = 1f, status = TaskStatus.COMPLETED, completedAt = completedAt)
            taskDao.updateTask(updated)
            recordStudyFromTask(updated, TaskActionType.EXERCISE)
            TaskCompletionResult.Success(1f, "已完成 ${payload.totalCount} 题")
        } else {
            val progress = checkedCount.toFloat() / payload.totalCount
            taskDao.updateTask(task.copy(progress = progress, status = TaskStatus.IN_PROGRESS))
            TaskCompletionResult.Success(progress, "已完成 $checkedCount/${payload.totalCount} 题，请继续")
        }
    }

    private suspend fun markConfirmCompletion(taskId: String, expectType: TaskActionType): TaskCompletionResult {
        val task = taskDao.getTaskById(taskId) ?: return TaskCompletionResult.Failure("任务不存在")
        if (task.actionType != expectType.name) return TaskCompletionResult.Failure("任务类型不匹配")
        val completedAt = System.currentTimeMillis()
        val updated = task.copy(progress = 1f, status = TaskStatus.COMPLETED, completedAt = completedAt)
        taskDao.updateTask(updated)
        recordStudyFromTask(updated, expectType)
        return TaskCompletionResult.Success(1f, "已完成")
    }

    private inline fun <reified T> parsePayload(actionType: String?, json: String?): T? {
        if (actionType == null || json.isNullOrBlank()) return null
        return try {
            gson.fromJson(json, T::class.java)
        } catch (_: JsonSyntaxException) {
            null
        }
    }

    override fun parseQuizPayload(task: Task): QuizPayload? =
        if (task.actionType == TaskActionType.QUIZ.name) parsePayload(task.actionType, task.actionPayload) else null

    override fun parseReadingPayload(task: Task): ReadingPayload? =
        if (task.actionType == TaskActionType.READING.name) parsePayload(task.actionType, task.actionPayload) else null

    override fun parseExercisePayload(task: Task): ExercisePayload? =
        if (task.actionType == TaskActionType.EXERCISE.name) parsePayload(task.actionType, task.actionPayload) else null

    override fun parseVideoPayload(task: Task): VideoPayload? =
        if (task.actionType == TaskActionType.VIDEO.name) parsePayload(task.actionType, task.actionPayload) else null

    override fun parseMemorizationPayload(task: Task): MemorizationPayload? =
        if (task.actionType == TaskActionType.MEMORIZATION.name) parsePayload(task.actionType, task.actionPayload) else null

    override suspend fun ensureDailyTasksForToday(grade: String) {
        val dayKey = currentDayKey()
        // 以模板 ID + dayKey 作为当日任务的唯一 ID 前缀，避免重复生成
        val todayIds = defaultDailyTemplates.map { "${it.id}_$dayKey" }.toSet()
        val existing = taskDao.getTasksForGrade(grade).first()
        val existingIds = existing.map { it.id }.toSet()

        val toCreate = defaultDailyTemplates
            .filter { template -> "${template.id}_$dayKey" !in existingIds }
            .map { template ->
                Task(
                    id = "${template.id}_$dayKey",
                    title = template.title,
                    description = template.description,
                    type = TaskType.DAILY,
                    difficulty = TaskDifficulty.EASY,
                    status = TaskStatus.PENDING,
                    targetGrade = grade,
                    experienceReward = template.exp,
                    coinReward = template.coin,
                    estimatedMinutes = template.estimatedMinutes,
                    createdAt = System.currentTimeMillis()
                )
            }

        if (toCreate.isNotEmpty()) {
            taskDao.insertTasks(toCreate)
        }
    }

    override suspend fun completeChallengeTaskByTitle(title: String) {
        // 按标题查找一条未完成的挑战任务并标记完成，同时触发成就系统
        val allChallenges = taskDao.getTasksByType(TaskType.CHALLENGE).first()
        val target = allChallenges.firstOrNull { it.title == title && it.status != TaskStatus.COMPLETED }
            ?: return

        val completedAt = System.currentTimeMillis()
        val updated = target.copy(
            status = TaskStatus.COMPLETED,
            progress = 1f,
            completedAt = completedAt
        )
        taskDao.updateTask(updated)

        // 记录一次学习时长（按习题类任务估算时长）
        recordStudyFromTask(updated, TaskActionType.EXERCISE)

        // 成就系统：按总完成任务数触发任务成就（first_task, task_10, task_50, task_100 等）
        val userId = userRepository.getCurrentUser().first()?.id ?: return
        val completedCount = getCompletedTaskCount().first()
        achievementRepository.checkAndUnlockAchievements(
            userId = userId,
            event = AchievementEvent.TaskCompleted(completedCount)
        )
    }
}
