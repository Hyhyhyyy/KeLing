package com.keling.app.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "task_progress")
data class TaskProgress(
    @PrimaryKey(autoGenerate = true) val id: Long = 0,
    val taskId: String,
    val userId: String,
    val progress: Int = 0
)