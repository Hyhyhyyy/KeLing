package com.keling.app.data.repository
import com.keling.app.data.local.dao.TaskRecordDao
import com.keling.app.data.model.TaskRecord
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
class TaskRecordRepository @Inject constructor(
    private val taskRecordDao: TaskRecordDao
) {
    suspend fun addTaskRecord(record: TaskRecord) =
        taskRecordDao.insertTaskRecord(record)
    fun getAllTaskRecords(): Flow<List<TaskRecord>> =
        taskRecordDao.getAllTaskRecords()
}