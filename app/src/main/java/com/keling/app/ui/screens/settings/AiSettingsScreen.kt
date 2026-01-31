package com.keling.app.ui.screens.settings

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.keling.app.data.model.AiConfig
import com.keling.app.data.model.AiProvider
import com.keling.app.ui.components.NeonButton
import com.keling.app.ui.components.NeonCard
import com.keling.app.ui.theme.DarkBackground
import com.keling.app.ui.theme.DarkBorder
import com.keling.app.ui.theme.TextPrimary
import com.keling.app.ui.theme.TextSecondary

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AiSettingsScreen(
    onBack: () -> Unit,
    viewModel: AiSettingsViewModel = hiltViewModel()
) {
    val uiState by viewModel.uiState.collectAsState()
    val snackbarHostState = remember { SnackbarHostState() }
    var apiKeyInput by rememberSaveable { mutableStateOf("") }
    var keyVisible by rememberSaveable { mutableStateOf(false) }

    LaunchedEffect(uiState.apiKey) {
        if (apiKeyInput.isBlank()) {
            apiKeyInput = uiState.apiKey
        }
    }

    LaunchedEffect(uiState.saveSuccess) {
        if (uiState.saveSuccess) {
            snackbarHostState.showSnackbar("API Key 已保存", withDismissAction = true)
            viewModel.clearSaveSuccess()
        }
    }

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
                        text = "AI 设置",
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
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                // 模型选择区域
                Text(
                    text = "模型配置",
                    style = MaterialTheme.typography.titleMedium,
                    color = TextPrimary
                )

                NeonCard(glowColor = DarkBorder) {
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(16.dp),
                        verticalArrangement = Arrangement.spacedBy(12.dp)
                    ) {
                        var providerExpanded by remember { mutableStateOf(false) }
                        var modelExpanded by remember { mutableStateOf(false) }

                        val currentProvider = remember(uiState.providerId) {
                            AiProvider.fromId(uiState.providerId) ?: AiProvider.QWEN
                        }
                        val availableModels = remember(currentProvider) {
                            AiConfig.getModelsForProvider(currentProvider)
                        }
                        val currentModel = remember(uiState.modelId, availableModels) {
                            availableModels.find { it.id == uiState.modelId } ?: availableModels.firstOrNull()
                        }

                        // 厂商选择
                        Text("选择 AI 厂商", color = TextSecondary, style = MaterialTheme.typography.bodyMedium)
                        ExposedDropdownMenuBox(
                            expanded = providerExpanded,
                            onExpandedChange = { providerExpanded = !providerExpanded }
                        ) {
                            OutlinedTextField(
                                value = currentProvider.displayName,
                                onValueChange = {},
                                readOnly = true,
                                trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = providerExpanded) },
                                modifier = Modifier.menuAnchor().fillMaxWidth(),
                                colors = ExposedDropdownMenuDefaults.outlinedTextFieldColors()
                            )
                            ExposedDropdownMenu(
                                expanded = providerExpanded,
                                onDismissRequest = { providerExpanded = false }
                            ) {
                                AiConfig.providers.forEach { provider ->
                                    DropdownMenuItem(
                                        text = { Text(provider.displayName) },
                                        onClick = {
                                            viewModel.updateConfig(provider.id, AiConfig.getModelsForProvider(provider).first().id)
                                            providerExpanded = false
                                        }
                                    )
                                }
                            }
                        }

                        // 模型选择
                        Text("选择或输入模型 ID", color = TextSecondary, style = MaterialTheme.typography.bodyMedium)
                        ExposedDropdownMenuBox(
                            expanded = modelExpanded,
                            onExpandedChange = { modelExpanded = !modelExpanded }
                        ) {
                            OutlinedTextField(
                                value = uiState.modelId, // 直接显示 ID，方便手动编辑
                                onValueChange = { newId ->
                                    viewModel.updateConfig(currentProvider.id, newId)
                                },
                                readOnly = false, // 允许手动输入
                                label = { Text("模型 ID (如 qwen-flash)") },
                                placeholder = { Text("输入模型 ID") },
                                trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = modelExpanded) },
                                modifier = Modifier.menuAnchor().fillMaxWidth(),
                                colors = ExposedDropdownMenuDefaults.outlinedTextFieldColors()
                            )
                            ExposedDropdownMenu(
                                expanded = modelExpanded,
                                onDismissRequest = { modelExpanded = false }
                            ) {
                                availableModels.forEach { model ->
                                    DropdownMenuItem(
                                        text = {
                                            Column {
                                                // 优化下拉显示：主标题显示 ID，副标题显示名称和描述
                                                Text(model.id, style = MaterialTheme.typography.bodyLarge)
                                                val subText = if (model.description.isNotEmpty()) {
                                                    "${model.displayName} · ${model.description}"
                                                } else {
                                                    model.displayName
                                                }
                                                Text(subText, style = MaterialTheme.typography.bodySmall, color = TextSecondary)
                                            }
                                        },
                                        onClick = {
                                            viewModel.updateConfig(currentProvider.id, model.id)
                                            modelExpanded = false
                                        }
                                    )
                                }
                            }
                        }
                    }
                }

                Text(
                    text = "自定义 API Key (可选)",
                    style = MaterialTheme.typography.titleMedium,
                    color = TextPrimary
                )

                NeonCard(glowColor = DarkBorder) {
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(16.dp),
                        verticalArrangement = Arrangement.spacedBy(12.dp)
                    ) {
                        OutlinedTextField(
                            value = apiKeyInput,
                            onValueChange = { apiKeyInput = it },
                            label = { Text("输入 Qwen API Key") },
                            placeholder = { Text("例如：sk-xxxx") },
                            visualTransformation = if (keyVisible) {
                                VisualTransformation.None
                            } else {
                                PasswordVisualTransformation()
                            },
                            trailingIcon = {
                                IconButton(onClick = { keyVisible = !keyVisible }) {
                                    Icon(
                                        imageVector = if (keyVisible) {
                                            Icons.Default.VisibilityOff
                                        } else {
                                            Icons.Default.Visibility
                                        },
                                        contentDescription = "显示或隐藏"
                                    )
                                }
                            },
                            modifier = Modifier.fillMaxWidth()
                        )

                        Text(
                            text = "提示：Key 会保存在本机，不会上传。",
                            style = MaterialTheme.typography.bodySmall,
                            color = TextSecondary
                        )

                        NeonButton(
                            text = "保存",
                            onClick = { viewModel.saveApiKey(apiKeyInput) },
                            modifier = Modifier.fillMaxWidth()
                        )
                    }
                }
            }
        }
    }
}
