package com.keling.app.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.keling.app.data.model.TaskRecord
import kotlinx.coroutines.flow.Flow

@Dao
interface TaskRecordDao {
    // 新增一条任务完成记录
    @Insert
    suspend fun insertTaskRecord(record: TaskRecord)
// 查询所有完成记录（按完成时间倒序）
@Query("SELECT * FROM task_records ORDER BY completeTime DESC")
fun getAllTaskRecords(): Flow<List<TaskRecord>>
//    // 可选：按时间范围查询（如今日/本周）
//    @Query("SELECT * FROM task_records WHERE completeTime BETWEEN :start AND :end
//            ORDER BY completeTime DESC")
//        fun getTaskRecordsByTime(start: LocalDateTime, end: LocalDateTime):
//                Flow<List<TaskRecord>>
@Query("SELECT * FROM task_records ORDER BY completeTime DESC")
fun getAll(): Flow<List<TaskRecord>>


}
