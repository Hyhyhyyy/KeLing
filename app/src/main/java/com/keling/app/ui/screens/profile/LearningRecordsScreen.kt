package com.keling.app.ui.screens.profile

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.keling.app.data.model.TaskRecord
import com.keling.app.ui.components.NeonCard
import com.keling.app.ui.theme.*
import java.time.format.DateTimeFormatter

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LearningRecordsScreen(
    onBack: () -> Unit,
    viewModel: ProfileViewModel = hiltViewModel()
) {
    val records by viewModel.getAllTaskRecords().collectAsState(initial = emptyList())
    var selectedRecord by remember { mutableStateOf<TaskRecord?>(null) }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("学习记录", color = TextPrimary) },
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
        ) {
            if (records.isEmpty()) {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .weight(1f),
                    contentAlignment = Alignment.Center
                ) {
                    Column(horizontalAlignment = Alignment.CenterHorizontally) {
                        Icon(
                            Icons.Default.History,
                            contentDescription = null,
                            tint = TextTertiary,
                            modifier = Modifier.size(64.dp)
                        )
                        Spacer(Modifier.height(16.dp))
                        Text("暂无学习记录", style = MaterialTheme.typography.bodyLarge, color = TextSecondary)
                        Text("完成任务后记录会出现在这里", style = MaterialTheme.typography.bodySmall, color = TextTertiary)
                    }
                }
            } else {
                Text(
                    "共 ${records.size} 条记录，点击查看详情",
                    style = MaterialTheme.typography.bodySmall,
                    color = TextSecondary,
                    modifier = Modifier.padding(16.dp)
                )
                LazyColumn(
                    modifier = Modifier.fillMaxSize(),
                    contentPadding = PaddingValues(16.dp),
                    verticalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    items(records, key = { it.id }) { record ->
                        LearningRecordItem(
                            record = record,
                            onClick = { selectedRecord = record }
                        )
                    }
                }
            }
        }
    }

    selectedRecord?.let { record ->
        AlertDialog(
            onDismissRequest = { selectedRecord = null },
            confirmButton = {
                TextButton(onClick = { selectedRecord = null }) {
                    Text("关闭", color = NeonBlue)
                }
            },
            title = {
                Text(record.taskTitle, color = TextPrimary)
            },
            text = {
                Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
                    Text("任务 ID：${record.taskId}", style = MaterialTheme.typography.bodyMedium, color = TextSecondary)
                    Text(
                        "完成时间：${record.completeTime.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"))}",
                        style = MaterialTheme.typography.bodyMedium,
                        color = TextSecondary
                    )
                    Text(
                        "学习时长：${record.duration ?: 0} 分钟",
                        style = MaterialTheme.typography.bodyMedium,
                        color = TextSecondary
                    )
                    record.taskType?.let { type ->
                        Text("任务类型：$type", style = MaterialTheme.typography.bodyMedium, color = TextSecondary)
                    }
                }
            },
            containerColor = DarkSurface,
            titleContentColor = TextPrimary,
            textContentColor = TextSecondary
        )
    }
}

@Composable
private fun LearningRecordItem(
    record: TaskRecord,
    onClick: () -> Unit
) {
    NeonCard(
        glowColor = NeonGreen.copy(alpha = 0.3f),
        onClick = onClick
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                Icons.Default.CheckCircle,
                contentDescription = null,
                tint = NeonGreen,
                modifier = Modifier.size(28.dp)
            )
            Spacer(Modifier.width(16.dp))
            Column(modifier = Modifier.weight(1f)) {
                Text(
                    record.taskTitle,
                    style = MaterialTheme.typography.bodyLarge,
                    fontWeight = FontWeight.Medium,
                    color = TextPrimary
                )
                Text(
                    "${record.completeTime.format(DateTimeFormatter.ofPattern("MM-dd HH:mm"))} · ${record.duration ?: 0} 分钟",
                    style = MaterialTheme.typography.bodySmall,
                    color = TextSecondary
                )
            }
            Icon(Icons.Default.ChevronRight, contentDescription = null, tint = TextTertiary)
        }
    }
}
