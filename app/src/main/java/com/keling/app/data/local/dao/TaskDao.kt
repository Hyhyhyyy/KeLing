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

    @Query("SELECT * FROM tasks WHERE id = :taskId")
    suspend fun getTaskById(taskId: String): Task?

    @Query("SELECT * FROM tasks WHERE id = :taskId")
    fun getTaskByIdFlow(taskId: String): Flow<Task?>

    @Query("SELECT * FROM tasks WHERE status = :status ORDER BY deadline ASC")
    fun getTasksByStatus(status: TaskStatus): Flow<List<Task>>

    @Query("SELECT * FROM tasks WHERE type = :type ORDER BY deadline ASC")
    fun getTasksByType(type: TaskType): Flow<List<Task>>

    @Query("SELECT * FROM tasks WHERE status IN ('PENDING', 'IN_PROGRESS') ORDER BY deadline ASC")
    fun getActiveTasks(): Flow<List<Task>>

    @Query("SELECT * FROM tasks WHERE courseId = :courseId ORDER BY `order`")
    fun getTasksByCourse(courseId: String): Flow<List<Task>>

    @Query("SELECT * FROM tasks WHERE (targetGrade IS NULL OR targetGrade = :grade) AND status IN ('PENDING', 'IN_PROGRESS') ORDER BY deadline ASC")
    fun getActiveTasksForGrade(grade: String): Flow<List<Task>>

    @Query("SELECT * FROM tasks WHERE (targetGrade IS NULL OR targetGrade = :grade) ORDER BY deadline ASC")
    fun getTasksForGrade(grade: String): Flow<List<Task>>

    @Query("SELECT * FROM tasks WHERE deadline < :timestamp AND status = 'PENDING'")
    suspend fun getExpiredTasks(timestamp: Long): List<Task>

    @Query("SELECT COUNT(*) FROM tasks WHERE status = 'COMPLETED'")
    fun getCompletedTaskCount(): Flow<Int>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertTask(task: Task)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertTasks(tasks: List<Task>)

    @Update
    suspend fun updateTask(task: Task)

    @Delete
    suspend fun deleteTask(task: Task)

    @Query("UPDATE tasks SET status = :status WHERE id = :taskId")
    suspend fun updateTaskStatus(taskId: String, status: TaskStatus)

    @Query("UPDATE tasks SET status = :status, completedAt = :completedAt WHERE id = :taskId")
    suspend fun completeTask(taskId: String, status: TaskStatus, completedAt: Long)

    @Query("SELECT * FROM task_progress WHERE taskId = :taskId AND userId = :userId")
    suspend fun getTaskProgress(taskId: String, userId: String): TaskProgress?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertTaskProgress(progress: TaskProgress)

    @Update
    suspend fun updateTaskProgress(progress: TaskProgress)

    @Query("SELECT * FROM team_tasks WHERE teamId = :teamId")
    fun getTeamTasksByTeam(teamId: String): Flow<List<TeamTask>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertTeamTask(teamTask: TeamTask)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertStudySession(session: StudySession)

    @Query("SELECT COALESCE(SUM(durationMinutes), 0) FROM study_sessions WHERE dayKey = :dayKey")
    fun getStudyMinutesForDay(dayKey: String): Flow<Int>
}
