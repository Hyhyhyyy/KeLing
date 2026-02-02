package com.keling.app.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "study_sessions")
data class StudySession(
    @PrimaryKey val id: String,      // 使用 String，与 UUID.randomUUID().toString() 匹配
    val dayKey: String,              // 例如 "2026-01-31"
    val source: String,
    val taskId: String? = null,      // 允许为空（recordManualStudy 使用 null）
    val durationMinutes: Int = 0
)