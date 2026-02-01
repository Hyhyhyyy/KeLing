package com.keling.app.data.local.dao

import androidx.room.*
import com.keling.app.data.model.Task
import com.keling.app.data.model.TaskProgress
import com.keling.app.data.model.TaskStatus
import com.keling.app.data.model.TaskType
import com.keling.app.data.model.TeamTask
import com.keling.app.data.model.StudySession
import kotlinx.coroutines.flow.Flow

@Dao
interface TaskDao {

    // 根据 ID 获取任务（一次性）
    @Query("SELECT * FROM tasks WHERE id = :taskId")
    suspend fun getTaskById(taskId: String): Task?

    // 根据 ID 获取任务（Flow 订阅）
    @Query("SELECT * FROM tasks WHERE id = :taskId")
    fun getTaskByIdFlow(taskId: String): Flow<Task?>

    // 按状态查询（枚举字段已由 Converters 进行转换到 String）
    @Query("SELECT * FROM tasks WHERE status = :status ORDER BY deadline ASC")
    fun getTasksByStatus(status: TaskStatus): Flow<List<Task>>

    // 按类型查询
    @Query("SELECT * FROM tasks WHERE type = :type ORDER BY deadline ASC")
    fun getTasksByType(type: TaskType): Flow<List<Task>>

    // 活动中任务（PENDING 或 IN_PROGRESS）
    @Query("SELECT * FROM tasks WHERE status IN ('PENDING', 'IN_PROGRESS') ORDER BY deadline ASC")
    fun getActiveTasks(): Flow<List<Task>>

    // 课程下任务（按 order 排序）
    @Query("SELECT * FROM tasks WHERE courseId = :courseId ORDER BY `order`")
    fun getTasksByCourse(courseId: String): Flow<List<Task>>

    // 针对指定年级的活动任务
    @Query("SELECT * FROM tasks WHERE (targetGrade IS NULL OR targetGrade = :grade) AND status IN ('PENDING', 'IN_PROGRESS') ORDER BY deadline ASC")
    fun getActiveTasksForGrade(grade: String): Flow<List<Task>>

    // 针对指定年级的所有任务
    @Query("SELECT * FROM tasks WHERE (targetGrade IS NULL OR targetGrade = :grade) ORDER BY deadline ASC")
    fun getTasksForGrade(grade: String): Flow<List<Task>>

    // 过期任务示例（deadline 为时间戳）
    @Query("SELECT * FROM tasks WHERE deadline < :timestamp AND status = 'PENDING'")
    suspend fun getExpiredTasks(timestamp: Long): List<Task>

    // 完成数量统计
    @Query("SELECT COUNT(*) FROM tasks WHERE status = 'COMPLETED'")
    fun getCompletedTaskCount(): Flow<Int>

    // 插入 / 批量插入
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertTask(task: Task)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertTasks(tasks: List<Task>)

    // 更新整个实体（Room 根据主键匹配）
    @Update
    suspend fun updateTask(task: Task)

    @Delete
    suspend fun deleteTask(task: Task)

    // 只更新状态
    @Query("UPDATE tasks SET status = :status WHERE id = :taskId")
    suspend fun updateTaskStatus(taskId: String, status: TaskStatus)

    // 标记完成并写入完成时间戳
    @Query("UPDATE tasks SET status = :status, completedAt = :completedAt WHERE id = :taskId")
    suspend fun completeTask(taskId: String, status: TaskStatus, completedAt: Long)

    // TaskProgress 相关
    @Query("SELECT * FROM task_progress WHERE taskId = :taskId AND userId = :userId")
    suspend fun getTaskProgress(taskId: String, userId: String): TaskProgress?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertTaskProgress(progress: TaskProgress)

    @Update
    suspend fun updateTaskProgress(progress: TaskProgress)

    // TeamTask / StudySession 等
    @Query("SELECT * FROM team_tasks WHERE teamId = :teamId")
    fun getTeamTasksByTeam(teamId: String): Flow<List<TeamTask>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertTeamTask(teamTask: TeamTask)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertStudySession(session: StudySession)

    @Query("SELECT COALESCE(SUM(durationMinutes), 0) FROM study_sessions WHERE dayKey = :dayKey")
    fun getStudyMinutesForDay(dayKey: String): Flow<Int>
}