package com.keling.app.data.repository

import com.keling.app.data.local.dao.UserDao
import com.keling.app.data.model.DashboardData
import com.keling.app.data.model.User
import com.keling.app.data.model.UserProfile
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

interface UserRepository {
    fun getCurrentUser(): Flow<User?>
    suspend fun getUserById(userId: String): User?
    suspend fun login(username: String, password: String): Result<User>
    suspend fun logout()
    suspend fun updateUser(user: User)
    suspend fun getDashboardData(userId: String): DashboardData
    fun getUserProfile(userId: String): Flow<UserProfile?>
}

class UserRepositoryImpl @Inject constructor(
    private val userDao: UserDao
) : UserRepository {

    private var currentUserId: String? = null

    override fun getCurrentUser(): Flow<User?> {
        return currentUserId?.let { userDao.getUserByIdFlow(it) }
            ?: kotlinx.coroutines.flow.flowOf(null)
    }

    override suspend fun getUserById(userId: String): User? {
        return userDao.getUserById(userId)
    }

    override suspend fun login(username: String, password: String): Result<User> {
        return try {
            // 模拟登录逻辑，实际应调用远程API
            val user = userDao.getUserByUsername(username)
            if (user != null) {
                currentUserId = user.id
                userDao.updateLastLogin(user.id, System.currentTimeMillis())
                Result.success(user)
            } else {
                // 创建演示用户
                val demoUser = createDemoUser(username)
                userDao.insertUser(demoUser)
                currentUserId = demoUser.id
                Result.success(demoUser)
            }
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    override suspend fun logout() {
        currentUserId = null
    }

    override suspend fun updateUser(user: User) {
        userDao.updateUser(user)
    }

    override suspend fun getDashboardData(userId: String): DashboardData {
        // 返回模拟数据，实际应从数据库计算
        return DashboardData(
            learningProgress = 0.68f,
            taskCompletion = 15,
            skillGrowth = mapOf(
                "数学" to 0.75f,
                "编程" to 0.82f,
                "英语" to 0.60f,
                "物理" to 0.55f
            ),
            achievements = emptyList(),
            todayStudyMinutes = 45,
            weeklyProgress = listOf(0.8f, 0.6f, 0.9f, 0.7f, 0.85f, 0.5f, 0.0f)
        )
    }

    override fun getUserProfile(userId: String): Flow<UserProfile?> {
        return userDao.getUserByIdFlow(userId).map { user ->
            user?.let {
                UserProfile(
                    user = it,
                    level = 12,
                    experience = 2450,
                    totalExperience = 15000,
                    streak = 7,
                    totalStudyMinutes = 1250,
                    taskCompletedCount = 45,
                    achievementCount = 12,
                    rankInClass = 5,
                    rankInSchool = 128
                )
            }
        }
    }

    private fun createDemoUser(username: String): User {
        return User(
            id = "user_${System.currentTimeMillis()}",
            username = username,
            realName = "演示用户",
            role = com.keling.app.data.model.UserRole.STUDENT,
            schoolId = "DUT",
            classId = "CS2024",
            grade = "2024级"
        )
    }
}
