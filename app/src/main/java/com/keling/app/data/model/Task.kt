package com.keling.app.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "tasks")
data class Task(
    @PrimaryKey val id: String,
    val title: String,
    val description: String? = null,

    val courseId: String? = null,
    val chapterId: String? = null,
    val order: Int? = null,

    val type: TaskType? = null,
    val difficulty: TaskDifficulty? = null,
    val status: TaskStatus = TaskStatus.PENDING,

    val experienceReward: Int? = null,
    val coinReward: Int? = null,
    val estimatedMinutes: Int? = null,
    val estimatedDuration: Int? = null,
    val progress: Float = 0f,

    val actionType: String? = null,
    val actionPayload: String? = null,

    val targetGrade: String? = null,
    val source: String? = null,
    val createdAt: Long? = null,
    val deadline: Long? = null,
    val completedAt: Long? = null,
    val isCompleted: Boolean = false
) {
    val effectiveEstimatedMinutes: Int
        get() = estimatedDuration ?: estimatedMinutes ?: 0
}
