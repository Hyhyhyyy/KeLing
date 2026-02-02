package com.keling.app.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "team_tasks")
data class TeamTask(
    @PrimaryKey val id: String,
    val teamId: String,
    val title: String,
    val description: String? = null
)