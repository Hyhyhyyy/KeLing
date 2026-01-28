package com.keling.app.ui.screens.home

import androidx.compose.animation.core.*
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.keling.app.ui.components.*
import com.keling.app.ui.theme.*

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    viewModel: HomeViewModel = hiltViewModel(),
    onNavigateToTask: (String) -> Unit,
    onNavigateToCourses: () -> Unit,
    onNavigateToCourseDetail: (String) -> Unit,
    onNavigateToFocus: () -> Unit,
    onNavigateToAIAssistant: () -> Unit,
    onNavigateToCampusPlanet: () -> Unit
) {
    val uiState by viewModel.uiState.collectAsState()
    
    Box(modifier = Modifier.fillMaxSize()) {
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .background(DarkBackground),
            contentPadding = PaddingValues(bottom = 80.dp)
        ) {
            // 顶部问候区域
            item {
                HomeHeader(
                    userName = uiState.userName,
                    level = uiState.level,
                    experience = uiState.experience,
                    maxExperience = uiState.maxExperience,
                    streak = uiState.streak
                )
            }
            
            // 今日概览
            item {
                TodayOverview(
                    todayTasks = uiState.todayTaskCount,
                    completedTasks = uiState.completedTaskCount,
                    studyMinutes = uiState.todayStudyMinutes
                )
            }
            
            // 快速开始
            item {
                QuickStartSection(
                    onStartTask = onNavigateToFocus,
                    onOpenSchedule = onNavigateToCourses,
                    onOpenAI = onNavigateToAIAssistant
                )
            }

            // 云端校园星球入口
            item {
                CampusPlanetEntry(
                    onClick = onNavigateToCampusPlanet,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp, vertical = 12.dp)
                        .height(160.dp)
                )
            }
            
            // 今日任务
            item {
                SectionHeader(title = "今日任务", actionText = "查看全部")
            }
            
            items(uiState.todayTasks) { task ->
                TaskCard(
                    title = task.title,
                    description = task.description,
                    progress = task.progress,
                    difficulty = task.difficulty.name,
                    expReward = task.experienceReward,
                    onClick = { onNavigateToTask(task.id) },
                    modifier = Modifier.padding(horizontal = 16.dp, vertical = 6.dp)
                )
            }
            
            // 最近课程
            item {
                SectionHeader(title = "最近课程", actionText = "课程表")
                Spacer(modifier = Modifier.height(8.dp))
            }
            
            item {
                LazyRow(
                    contentPadding = PaddingValues(horizontal = 16.dp),
                    horizontalArrangement = Arrangement.spacedBy(12.dp)
                ) {
                    items(uiState.recentCourses) { course ->
                        CourseCard(
                            name = course.name,
                            teacherName = course.teacherName,
                            progress = course.progress,
                            credits = course.credits,
                            onClick = { onNavigateToCourseDetail(course.id) },
                            modifier = Modifier.width(280.dp)
                        )
                    }
                }
            }
            
            // 能力成长
            item {
                Spacer(modifier = Modifier.height(24.dp))
                SectionHeader(title = "能力成长")
                Spacer(modifier = Modifier.height(12.dp))
                
                NeonCard(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp),
                    glowColor = NeonPurple
                ) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceEvenly
                    ) {
                        RadarChart(
                            data = uiState.skillGrowth,
                            modifier = Modifier.size(180.dp),
                            color = NeonPurple
                        )
                    }
                    
                    Spacer(modifier = Modifier.height(12.dp))
                    
                    // 技能标签
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceEvenly
                    ) {
                        uiState.skillGrowth.forEach { (skill, value) ->
                            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                                Text(
                                    text = skill,
                                    style = MaterialTheme.typography.labelSmall,
                                    color = TextSecondary
                                )
                                Text(
                                    text = "${(value * 100).toInt()}%",
                                    style = MaterialTheme.typography.labelMedium,
                                    color = NeonPurple
                                )
                            }
                        }
                    }
                }
            }
            
            item { Spacer(modifier = Modifier.height(24.dp)) }
        }
        
        // AI助手浮动按钮
        AIFloatingButton(
            onClick = onNavigateToAIAssistant,
            modifier = Modifier
                .align(Alignment.BottomEnd)
                .padding(16.dp)
        )
    }
}

@Composable
private fun HomeHeader(
    userName: String,
    level: Int,
    experience: Int,
    maxExperience: Int,
    streak: Int
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(
                brush = Brush.verticalGradient(
                    colors = listOf(
                        NeonBlue.copy(alpha = 0.1f),
                        DarkBackground
                    )
                )
            )
            .statusBarsPadding()
            .padding(16.dp)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column {
                Text(
                    text = "你好，$userName",
                    style = MaterialTheme.typography.headlineSmall,
                    fontWeight = FontWeight.Bold,
                    color = TextPrimary
                )
                Spacer(modifier = Modifier.height(4.dp))
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Icon(
                        imageVector = Icons.Default.LocalFireDepartment,
                        contentDescription = null,
                        tint = NeonOrange,
                        modifier = Modifier.size(16.dp)
                    )
                    Spacer(modifier = Modifier.width(4.dp))
                    Text(
                        text = "连续学习 $streak 天",
                        style = MaterialTheme.typography.bodySmall,
                        color = NeonOrange
                    )
                }
            }
            
            // 通知按钮
            IconButton(onClick = { }) {
                Icon(
                    imageVector = Icons.Default.Notifications,
                    contentDescription = "通知",
                    tint = TextPrimary
                )
            }
        }
        
        Spacer(modifier = Modifier.height(16.dp))
        
        // 经验值条
        ExperienceBar(
            currentExp = experience,
            maxExp = maxExperience,
            level = level,
            modifier = Modifier.fillMaxWidth()
        )
    }
}

@Composable
private fun TodayOverview(
    todayTasks: Int,
    completedTasks: Int,
    studyMinutes: Int
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        horizontalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        StatCard(
            title = "今日任务",
            value = "$completedTasks/$todayTasks",
            icon = Icons.Default.Assignment,
            color = NeonBlue,
            modifier = Modifier.weight(1f)
        )
        StatCard(
            title = "学习时长",
            value = "${studyMinutes}分钟",
            icon = Icons.Default.Timer,
            color = NeonGreen,
            modifier = Modifier.weight(1f)
        )
    }
}

@Composable
private fun StatCard(
    title: String,
    value: String,
    icon: androidx.compose.ui.graphics.vector.ImageVector,
    color: androidx.compose.ui.graphics.Color,
    modifier: Modifier = Modifier
) {
    NeonCard(
        modifier = modifier,
        glowColor = color
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                imageVector = icon,
                contentDescription = null,
                tint = color,
                modifier = Modifier.size(24.dp)
            )
            Spacer(modifier = Modifier.width(12.dp))
            Column {
                Text(
                    text = title,
                    style = MaterialTheme.typography.labelSmall,
                    color = TextSecondary
                )
                Text(
                    text = value,
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.Bold,
                    color = color
                )
            }
        }
    }
}

@Composable
private fun QuickStartSection(
    onStartTask: () -> Unit,
    onOpenSchedule: () -> Unit,
    onOpenAI: () -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp),
        horizontalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        QuickActionButton(
            icon = Icons.Default.PlayArrow,
            label = "开始学习",
            color = NeonGreen,
            onClick = onStartTask,
            modifier = Modifier.weight(1f)
        )
        QuickActionButton(
            icon = Icons.Default.CalendarToday,
            label = "今日课表",
            color = NeonBlue,
            onClick = onOpenSchedule,
            modifier = Modifier.weight(1f)
        )
        QuickActionButton(
            icon = Icons.Default.SmartToy,
            label = "AI助手",
            color = NeonPurple,
            onClick = onOpenAI,
            modifier = Modifier.weight(1f)
        )
    }
}

@Composable
private fun QuickActionButton(
    icon: androidx.compose.ui.graphics.vector.ImageVector,
    label: String,
    color: androidx.compose.ui.graphics.Color,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    GradientCard(
        modifier = modifier,
        gradientColors = listOf(color, color.copy(alpha = 0.5f)),
        onClick = onClick
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.fillMaxWidth()
        ) {
            Icon(
                imageVector = icon,
                contentDescription = null,
                tint = color,
                modifier = Modifier.size(28.dp)
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = label,
                style = MaterialTheme.typography.labelMedium,
                color = TextPrimary
            )
        }
    }
}

@Composable
private fun CampusPlanetEntry(
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    NeonCard(
        modifier = modifier,
        glowColor = NeonBlue,
        onClick = onClick
    ) {
        Box(
            modifier = Modifier.fillMaxSize()
        ) {
            // 星空与流光背景，营造宇宙粒子感
            StarryBackground(
                modifier = Modifier
                    .matchParentSize()
            )
            StreamingLightEffect(
                modifier = Modifier
                    .matchParentSize(),
                color = NeonPurple
            )

            Column(
                modifier = Modifier
                    .fillMaxSize(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = "云端校园星球",
                    style = MaterialTheme.typography.titleLarge,
                    fontWeight = FontWeight.Bold,
                    color = NeonBlue
                )
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    text = "探索论坛星球 · 实践星球，解锁校园任务与社交",
                    style = MaterialTheme.typography.bodySmall,
                    color = TextSecondary
                )
            }
        }
    }
}

@Composable
private fun SectionHeader(
    title: String,
    actionText: String? = null,
    onAction: () -> Unit = {}
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 12.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = title,
            style = MaterialTheme.typography.titleMedium,
            fontWeight = FontWeight.Bold,
            color = TextPrimary
        )
        if (actionText != null) {
            TextButton(onClick = onAction) {
                Text(
                    text = actionText,
                    style = MaterialTheme.typography.labelMedium,
                    color = NeonBlue
                )
            }
        }
    }
}
