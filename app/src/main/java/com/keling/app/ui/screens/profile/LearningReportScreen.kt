package com.keling.app.ui.screens.profile

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.keling.app.data.model.KnowledgePoint
import com.keling.app.data.model.LearningReport
import com.keling.app.ui.components.NeonCard
import com.keling.app.ui.theme.*

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LearningReportScreen(
    onBack: () -> Unit,
    viewModel: LearningReportViewModel = hiltViewModel()
) {
    val uiState by viewModel.uiState.collectAsState()

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("学情报告", color = TextPrimary) },
                navigationIcon = {
                    IconButton(onClick = onBack) {
                        Icon(Icons.Default.ArrowBack, contentDescription = "返回", tint = TextPrimary)
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(containerColor = DarkBackground)
            )
        },
        containerColor = DarkBackground
    ) { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .verticalScroll(rememberScrollState())
        ) {
            if (uiState.courses.isNotEmpty()) {
                Text(
                    "选择课程",
                    style = MaterialTheme.typography.titleSmall,
                    color = TextSecondary,
                    modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp)
                )
                LazyRow(
                    modifier = Modifier.fillMaxWidth(),
                    contentPadding = PaddingValues(horizontal = 16.dp, vertical = 8.dp),
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    items(uiState.courses) { course ->
                        val selected = course.id == uiState.selectedCourseId
                        FilterChip(
                            selected = selected,
                            onClick = { viewModel.selectCourse(course.id) },
                            label = { Text(course.name, color = if (selected) DarkBackground else TextPrimary) },
                            colors = FilterChipDefaults.filterChipColors(
                                selectedContainerColor = NeonPurple,
                                selectedLabelColor = DarkBackground
                            )
                        )
                    }
                }
            }

            when {
                uiState.isLoading && uiState.report == null -> {
                    Box(
                        modifier = Modifier.fillMaxWidth().padding(32.dp),
                        contentAlignment = Alignment.Center
                    ) {
                        CircularProgressIndicator(color = NeonBlue)
                    }
                }
                uiState.report != null -> {
                    ReportContent(report = uiState.report!!)
                }
                uiState.courses.isEmpty() && uiState.report != null -> {
                    ReportContent(report = uiState.report!!)
                }
                uiState.courses.isEmpty() -> {
                    Box(
                        modifier = Modifier.fillMaxWidth().padding(32.dp),
                        contentAlignment = Alignment.Center
                    ) {
                        Text("暂无课程数据，请先添加课程", color = TextSecondary)
                    }
                }
            }
        }
    }
}

@Composable
private fun ReportContent(report: LearningReport) {
    Column(
        modifier = Modifier.padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            NeonCard(modifier = Modifier.weight(1f), glowColor = NeonGreen) {
                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    Icon(Icons.Default.TrendingUp, null, tint = NeonGreen, modifier = Modifier.size(28.dp))
                    Spacer(Modifier.height(8.dp))
                    Text(
                        "${(report.accuracy * 100).toInt()}%",
                        style = MaterialTheme.typography.headlineSmall,
                        fontWeight = FontWeight.Bold,
                        color = NeonGreen
                    )
                    Text("正确率", style = MaterialTheme.typography.bodySmall, color = TextSecondary)
                }
            }
            NeonCard(modifier = Modifier.weight(1f), glowColor = NeonBlue) {
                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    Icon(Icons.Default.Speed, null, tint = NeonBlue, modifier = Modifier.size(28.dp))
                    Spacer(Modifier.height(8.dp))
                    Text(
                        "${report.speed}",
                        style = MaterialTheme.typography.headlineSmall,
                        fontWeight = FontWeight.Bold,
                        color = NeonBlue
                    )
                    Text("题/分钟", style = MaterialTheme.typography.bodySmall, color = TextSecondary)
                }
            }
        }

        SectionTitle("薄弱知识点", Icons.Default.Warning, NeonOrange)
        report.knowledgeGaps.forEach { point ->
            KnowledgePointCard(point, isWeak = true)
        }

        SectionTitle("掌握较好", Icons.Default.CheckCircle, NeonGreen)
        report.strongPoints.forEach { point ->
            KnowledgePointCard(point, isWeak = false)
        }

        SectionTitle("学习建议", Icons.Default.Lightbulb, NeonGold)
        report.suggestions.forEachIndexed { index, text ->
            NeonCard(glowColor = NeonGold.copy(alpha = 0.3f)) {
                Row(
                    verticalAlignment = Alignment.Top,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text(
                        "${index + 1}.",
                        style = MaterialTheme.typography.bodyMedium,
                        color = NeonGold,
                        modifier = Modifier.padding(end = 8.dp)
                    )
                    Text(text, style = MaterialTheme.typography.bodyMedium, color = TextPrimary)
                }
            }
        }
    }
}

@Composable
private fun SectionTitle(title: String, icon: androidx.compose.ui.graphics.vector.ImageVector, color: androidx.compose.ui.graphics.Color) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier.padding(vertical = 4.dp)
    ) {
        Icon(icon, null, tint = color, modifier = Modifier.size(20.dp))
        Spacer(Modifier.width(8.dp))
        Text(
            title,
            style = MaterialTheme.typography.titleMedium,
            fontWeight = FontWeight.Bold,
            color = TextPrimary
        )
    }
}

@Composable
private fun KnowledgePointCard(point: KnowledgePoint, isWeak: Boolean) {
    val color = if (isWeak) NeonOrange else NeonGreen
    NeonCard(glowColor = color.copy(alpha = 0.3f)) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.fillMaxWidth()
        ) {
            Icon(
                if (isWeak) Icons.Default.Warning else Icons.Default.CheckCircle,
                contentDescription = null,
                tint = color,
                modifier = Modifier.size(22.dp)
            )
            Spacer(Modifier.width(12.dp))
            Column(modifier = Modifier.weight(1f)) {
                Text(
                    point.name,
                    style = MaterialTheme.typography.bodyLarge,
                    fontWeight = FontWeight.Medium,
                    color = TextPrimary
                )
                point.description?.let { desc ->
                    Text(desc, style = MaterialTheme.typography.bodySmall, color = TextSecondary)
                }
                Text(
                    "掌握度 ${(point.masteryLevel * 100).toInt()}%",
                    style = MaterialTheme.typography.labelSmall,
                    color = TextTertiary
                )
            }
        }
    }
}
