package com.keling.app.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * 任务/学习记录实体类（Room 数据库表对应的模型）
 * tableName：数据库表名，建议小写+下划线
 */
@Entity(tableName = "task_record")
data class TaskRecordEntity(
    // 主键，自增ID（确保每条记录唯一）
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,

    // 学习/任务时长（分钟），比如专注计时的时长
    val durationMinutes: Int,

    // 记录创建时间戳（毫秒），方便后续排序/筛选
    val createTime: Long = System.currentTimeMillis(),

    // 可选：任务描述（比如“学习Android”）
    val taskDesc: String? = null,

    // 可选：记录类型（比如“focus”=专注，“break”=休息）
    val recordType: String = "focus"
)