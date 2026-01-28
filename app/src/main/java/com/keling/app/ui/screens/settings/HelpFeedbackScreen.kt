package com.keling.app.ui.screens.settings

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import kotlinx.coroutines.launch
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.keling.app.ui.components.NeonButton
import com.keling.app.ui.components.NeonCard
import com.keling.app.ui.theme.*

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HelpFeedbackScreen(
    onBack: () -> Unit
) {
    var feedbackText by remember { mutableStateOf("") }
    val snackbarHostState = remember { SnackbarHostState() }
    val scope = rememberCoroutineScope()

    val faqItems = remember {
        listOf(
            "如何添加课表？" to "在「课程」页点击右上角进入编辑模式，可新增或删除课表条目。",
            "任务从哪里来？" to "任务由 AI 根据你的课程自动生成，也可在课程详情中查看与完成任务。",
            "学习时长如何统计？" to "在「开始学习」专注页计时会记录时长，完成任务也会计入当日学习时长。",
            "如何修改个人资料？" to "在「我的」→「设置」→「个人资料」中可编辑昵称、邮箱、学校等信息。"
        )
    }
    var expandedIndex by remember { mutableStateOf(-1) }

    Scaffold(
        snackbarHost = { SnackbarHost(snackbarHostState) },
        containerColor = DarkBackground
    ) { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .background(DarkBackground)
        ) {
            TopAppBar(
                title = {
                    Text(
                        text = "帮助与反馈",
                        style = MaterialTheme.typography.titleLarge,
                        color = TextPrimary
                    )
                },
                navigationIcon = {
                    IconButton(onClick = onBack) {
                        Icon(
                            imageVector = Icons.Default.ArrowBack,
                            contentDescription = "返回",
                            tint = TextPrimary
                        )
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = DarkBackground
                )
            )

            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .verticalScroll(rememberScrollState())
                    .padding(16.dp),
                verticalArrangement = Arrangement.spacedBy(20.dp)
            ) {
                Text(
                    text = "常见问题",
                    style = MaterialTheme.typography.titleMedium,
                    color = TextPrimary
                )
                NeonCard(glowColor = DarkBorder) {
                    Column(verticalArrangement = Arrangement.spacedBy(4.dp)) {
                        faqItems.forEachIndexed { index, (question, answer) ->
                            val expanded = expandedIndex == index
                            Surface(
                                onClick = {
                                    expandedIndex = if (expandedIndex == index) -1 else index
                                },
                                color = androidx.compose.ui.graphics.Color.Transparent,
                                modifier = Modifier.fillMaxWidth()
                            ) {
                                Column(
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .padding(vertical = 12.dp)
                                ) {
                                    Row(
                                        modifier = Modifier.fillMaxWidth(),
                                        verticalAlignment = Alignment.CenterVertically,
                                        horizontalArrangement = Arrangement.SpaceBetween
                                    ) {
                                        Text(
                                            text = question,
                                            style = MaterialTheme.typography.bodyLarge,
                                            color = TextPrimary,
                                            modifier = Modifier.weight(1f)
                                        )
                                        Icon(
                                            imageVector = if (expandedIndex == index) Icons.Default.ExpandLess else Icons.Default.ExpandMore,
                                            contentDescription = null,
                                            tint = TextSecondary
                                        )
                                    }
                                    if (expandedIndex == index) {
                                        Spacer(modifier = Modifier.height(8.dp))
                                        Text(
                                            text = answer,
                                            style = MaterialTheme.typography.bodySmall,
                                            color = TextSecondary
                                        )
                                    }
                                }
                            }
                        }
                    }
                }

                Text(
                    text = "意见反馈",
                    style = MaterialTheme.typography.titleMedium,
                    color = TextPrimary
                )
                OutlinedTextField(
                    value = feedbackText,
                    onValueChange = { feedbackText = it },
                    label = { Text("请输入反馈内容", color = TextSecondary) },
                    placeholder = { Text("描述你的问题或建议…", color = TextTertiary) },
                    minLines = 4,
                    maxLines = 6,
                    shape = RoundedCornerShape(12.dp),
                    colors = OutlinedTextFieldDefaults.colors(
                        focusedBorderColor = NeonBlue,
                        unfocusedBorderColor = DarkBorder,
                        focusedLabelColor = NeonBlue,
                        unfocusedLabelColor = TextSecondary,
                        cursorColor = NeonBlue,
                        focusedTextColor = TextPrimary,
                        unfocusedTextColor = TextPrimary,
                        focusedContainerColor = DarkSurface,
                        unfocusedContainerColor = DarkSurface
                    ),
                    modifier = Modifier.fillMaxWidth()
                )
                NeonButton(
                    text = "提交反馈",
                    onClick = {
                        scope.launch {
                            snackbarHostState.showSnackbar(
                                if (feedbackText.isNotBlank()) "感谢你的反馈，我们会尽快处理。" else "请先输入反馈内容。",
                                withDismissAction = true
                            )
                            if (feedbackText.isNotBlank()) feedbackText = ""
                        }
                    },
                    modifier = Modifier.fillMaxWidth()
                )

                Spacer(modifier = Modifier.height(32.dp))
            }
        }
    }
}
