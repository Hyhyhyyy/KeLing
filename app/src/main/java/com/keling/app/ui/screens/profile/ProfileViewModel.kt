package com.keling.app.ui.screens.profile

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import com.keling.app.data.local.dao.TaskRecordDao
import com.keling.app.data.model.TaskRecord

@HiltViewModel
class ProfileViewModel @Inject constructor(
    private val taskRecordDao: TaskRecordDao
) : ViewModel() {
    // 假设 DAO 方法为 getAll(): Flow<List<TaskRecord>>
    fun getAllTaskRecords(): Flow<List<TaskRecord>> = taskRecordDao.getAll()
}