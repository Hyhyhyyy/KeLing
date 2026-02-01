package com.keling.app.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "tasks")
data class Task(
    @PrimaryKey val id: String,
    val title: String,
    val description: String? = null,

    // 课程/章节/排序
    val courseId: String? = null,
    val chapterId: String? = null,
    val order: Int? = null,

    // 分类与难度/状态
    val type: TaskType? = null,                 // e.g. PRACTICE / CHALLENGE / DAILY / TEAM
    val difficulty: TaskDifficulty? = null,     // e.g. EXPERT
    val status: TaskStatus = TaskStatus.PENDING,

    // 奖励 / 预估时间 / 进度（注意 progress 用 Float）
    val experienceReward: Int? = null,
    val coinReward: Int? = null,
    val estimatedMinutes: Int? = null,      // 代码中有这个名字
    val estimatedDuration: Int? = null,     // 也有这个名字，保留兼容
    val progress: Float = 0f,               // 使用 Float，默认 0f

    // 执行动作（实体内用 String 存 actionType 名称，payload 存 JSON）
    val actionType: String? = null,
    val actionPayload: String? = null,

    // meta
    val targetGrade: String? = null,
    val source: String? = null,
    val createdAt: Long? = null,    // epoch millis
    val deadline: Long? = null,
    val completedAt: Long? = null,
    val isCompleted: Boolean = false
) {
    // 便捷取值：优先 estimatedDuration，否则 estimatedMinutes，最后 0
    val effectiveEstimatedMinutes: Int
        get() = estimatedDuration ?: estimatedMinutes ?: 0
}