package com.keling.app.data.local

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.keling.app.data.model.*
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

object Converters {
    private val gson = Gson()
    private val formatter: DateTimeFormatter = DateTimeFormatter.ISO_LOCAL_DATE_TIME

    // ---- Enum / custom types (nullable-safe) ----
    @TypeConverter @JvmStatic
    fun fromUserRole(role: UserRole?): String? = role?.name

    @TypeConverter @JvmStatic
    fun toUserRole(value: String?): UserRole? = value?.let { UserRole.valueOf(it) }

    @TypeConverter @JvmStatic
    fun fromPrivacyLevel(level: PrivacyLevel?): String? = level?.name

    @TypeConverter @JvmStatic
    fun toPrivacyLevel(value: String?): PrivacyLevel? = value?.let { PrivacyLevel.valueOf(it) }

    @TypeConverter @JvmStatic
    fun fromCourseType(type: CourseType?): String? = type?.name

    @TypeConverter @JvmStatic
    fun toCourseType(value: String?): CourseType? = value?.let { CourseType.valueOf(it) }

    @TypeConverter @JvmStatic
    fun fromWeekType(type: WeekType?): String? = type?.name

    @TypeConverter @JvmStatic
    fun toWeekType(value: String?): WeekType? = value?.let { WeekType.valueOf(it) }

    @TypeConverter @JvmStatic
    fun fromMaterialType(type: MaterialType?): String? = type?.name

    @TypeConverter @JvmStatic
    fun toMaterialType(value: String?): MaterialType? = value?.let { MaterialType.valueOf(it) }

    @TypeConverter @JvmStatic
    fun fromTaskType(type: TaskType?): String? = type?.name

    @TypeConverter @JvmStatic
    fun toTaskType(value: String?): TaskType? = value?.let { TaskType.valueOf(it) }

    @TypeConverter @JvmStatic
    fun fromTaskDifficulty(difficulty: TaskDifficulty?): String? = difficulty?.name

    @TypeConverter @JvmStatic
    fun toTaskDifficulty(value: String?): TaskDifficulty? = value?.let { TaskDifficulty.valueOf(it) }

    @TypeConverter @JvmStatic
    fun fromTaskStatus(status: TaskStatus?): String? = status?.name

    @TypeConverter @JvmStatic
    fun toTaskStatus(value: String?): TaskStatus? = value?.let { TaskStatus.valueOf(it) }

    @TypeConverter @JvmStatic
    fun fromAchievementCategory(category: AchievementCategory?): String? = category?.name

    @TypeConverter @JvmStatic
    fun toAchievementCategory(value: String?): AchievementCategory? = value?.let { AchievementCategory.valueOf(it) }

    @TypeConverter @JvmStatic
    fun fromAchievementRarity(rarity: AchievementRarity?): String? = rarity?.name

    @TypeConverter @JvmStatic
    fun toAchievementRarity(value: String?): AchievementRarity? = value?.let { AchievementRarity.valueOf(it) }

    @TypeConverter @JvmStatic
    fun fromRelationType(type: RelationType?): String? = type?.name

    @TypeConverter @JvmStatic
    fun toRelationType(value: String?): RelationType? = value?.let { RelationType.valueOf(it) }

    // ---- List<String> ----
    @TypeConverter @JvmStatic
    fun fromStringList(list: List<String>?): String? = gson.toJson(list ?: emptyList<String>())

    @TypeConverter @JvmStatic
    fun toStringList(value: String?): List<String> {
        if (value.isNullOrEmpty()) return emptyList()
        val type = object : TypeToken<List<String>>() {}.type
        return gson.fromJson(value, type) ?: emptyList()
    }

    // ---- Map<String, Float> ----
    @TypeConverter @JvmStatic
    fun fromFloatMap(map: Map<String, Float>?): String? = gson.toJson(map ?: emptyMap<String, Float>())

    @TypeConverter @JvmStatic
    fun toFloatMap(value: String?): Map<String, Float> {
        if (value.isNullOrEmpty()) return emptyMap()
        val type = object : TypeToken<Map<String, Float>>() {}.type
        return gson.fromJson(value, type) ?: emptyMap()
    }

    // ---- LocalDateTime <-> String ----
    @TypeConverter @JvmStatic
    fun fromLocalDateTime(value: LocalDateTime?): String? = value?.format(formatter)

    @TypeConverter @JvmStatic
    fun toLocalDateTime(value: String?): LocalDateTime? =
        value?.let { LocalDateTime.parse(it, formatter) }
}