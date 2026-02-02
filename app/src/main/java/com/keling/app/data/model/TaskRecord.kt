package com.keling.app.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.keling.app.data.local.Converters
import java.time.LocalDateTime

// 学习记录表，关联任务ID，记录完成时间
@Entity(tableName = "task_records")
@TypeConverters(Converters::class)
data class TaskRecord(
    @PrimaryKey(autoGenerate = true) val id: Long = 0,
    val taskId: String, // 关联任务模块的Task.id
    val taskTitle: String, // 任务标题（冗余存储，避免关联查询）
    val completeTime: LocalDateTime, // 任务完成时间
    val taskType: String? = null, // 可选：任务类型（如“作业/刷题/背单词”）
    val duration: Int? = null // 可选：学习时长（单位：分钟）
)