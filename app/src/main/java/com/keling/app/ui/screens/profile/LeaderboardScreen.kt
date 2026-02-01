package com.keling.app.ui.screens.profile

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.keling.app.ui.components.NeonCard
import com.keling.app.ui.theme.*

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LeaderboardScreen(
    onBack: () -> Unit,
    viewModel: LeaderboardViewModel = hiltViewModel()
) {
    val uiState by viewModel.uiState.collectAsState()
    var selectedUser by remember { mutableStateOf<LeaderboardUser?>(null) }
    val list = if (uiState.selectedTab == 0) uiState.weekly else uiState.total
    val tabTitles = listOf("本周排行", "总榜")

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("排行榜", color = TextPrimary) },
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
            TabRow(
                selectedTabIndex = uiState.selectedTab,
                containerColor = DarkBackground,
                contentColor = NeonBlue
            ) {
                tabTitles.forEachIndexed { index, title ->
                    Tab(
                        selected = uiState.selectedTab == index,
                        onClick = { viewModel.setSelectedTab(index) },
                        text = {
                            Text(
                                title,
                                color = if (uiState.selectedTab == index) NeonBlue else TextSecondary
                            )
                        }
                    )
                }
            }

            if (uiState.isLoading) {
                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    CircularProgressIndicator(color = NeonGold)
                }
            } else {
                LazyColumn(
                    modifier = Modifier.fillMaxSize(),
                    contentPadding = PaddingValues(16.dp),
                    verticalArrangement = Arrangement.spacedBy(10.dp)
                ) {
                    items(list, key = { it.id }) { user ->
                        LeaderboardItem(
                            user = user,
                            onClick = { selectedUser = user }
                        )
                    }
                }
            }
        }
    }

    selectedUser?.let { user ->
        LeaderboardUserDetailSheet(
            user = user,
            onDismiss = { selectedUser = null }
        )
    }
}

@Composable
private fun LeaderboardItem(
    user: LeaderboardUser,
    onClick: () -> Unit
) {
    val rankColor = when (user.rank) {
        1 -> NeonGold
        2 -> ExpSilver
        3 -> ExpBronze
        else -> TextSecondary
    }
    val rankIcon: ImageVector? = when (user.rank) {
        1 -> Icons.Default.EmojiEvents
        2 -> Icons.Default.MilitaryTech
        3 -> Icons.Default.Star
        else -> null
    }

    NeonCard(
        glowColor = if (user.isCurrentUser) NeonBlue.copy(alpha = 0.4f) else NeonGold.copy(alpha = 0.2f),
        onClick = onClick
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Box(
                modifier = Modifier.size(44.dp),
                contentAlignment = Alignment.Center
            ) {
                if (rankIcon != null) {
                    Icon(
                        rankIcon,
                        contentDescription = null,
                        tint = rankColor,
                        modifier = Modifier.size(28.dp)
                    )
                } else {
                    Text(
                        "${user.rank}",
                        style = MaterialTheme.typography.titleLarge,
                        fontWeight = FontWeight.Bold,
                        color = rankColor
                    )
                }
            }
            Spacer(Modifier.width(12.dp))
            Box(
                modifier = Modifier
                    .size(44.dp)
                    .clip(CircleShape)
                    .background(
                        brush = Brush.linearGradient(
                            colors = listOf(NeonBlue, NeonPurple)
                        )
                    ),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    user.avatarLetter,
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.Bold,
                    color = DarkBackground
                )
            }
            Spacer(Modifier.width(16.dp))
            Column(modifier = Modifier.weight(1f)) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Text(
                        user.name,
                        style = MaterialTheme.typography.titleMedium,
                        fontWeight = FontWeight.Bold,
                        color = TextPrimary
                    )
                    if (user.isCurrentUser) {
                        Spacer(Modifier.width(6.dp))
                        Text(
                            "我",
                            style = MaterialTheme.typography.labelSmall,
                            color = NeonBlue,
                            modifier = Modifier
                                .background(NeonBlue.copy(alpha = 0.2f), RoundedCornerShape(4.dp))
                                .padding(horizontal = 6.dp, vertical = 2.dp)
                        )
                    }
                }
                Text(
                    "Lv.${user.level} · ${user.exp} XP · ${user.studyHours}h 学习",
                    style = MaterialTheme.typography.bodySmall,
                    color = TextSecondary
                )
            }
            Icon(Icons.Default.ChevronRight, contentDescription = null, tint = TextTertiary)
        }
    }
}

@Composable
private fun LeaderboardUserDetailSheet(
    user: LeaderboardUser,
    onDismiss: () -> Unit
) {
    ModalBottomSheet(
        onDismissRequest = onDismiss,
        containerColor = DarkSurface,
        contentColor = TextPrimary
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(24.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center
            ) {
                val rankColor = when (user.rank) {
                    1 -> NeonGold
                    2 -> ExpSilver
                    3 -> ExpBronze
                    else -> TextSecondary
                }
                Text(
                    "第 ${user.rank} 名",
                    style = MaterialTheme.typography.titleLarge,
                    fontWeight = FontWeight.Bold,
                    color = rankColor
                )
                Spacer(Modifier.width(16.dp))
                Box(
                    modifier = Modifier
                        .size(64.dp)
                        .clip(CircleShape)
                        .background(
                            brush = Brush.linearGradient(
                                colors = listOf(NeonBlue, NeonPurple)
                            )
                        ),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        user.avatarLetter,
                        style = MaterialTheme.typography.headlineMedium,
                        fontWeight = FontWeight.Bold,
                        color = DarkBackground
                    )
                }
                Spacer(Modifier.width(16.dp))
                Text(
                    user.name,
                    style = MaterialTheme.typography.headlineSmall,
                    fontWeight = FontWeight.Bold,
                    color = TextPrimary
                )
            }
            Spacer(Modifier.height(24.dp))
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    Text("${user.level}", style = MaterialTheme.typography.headlineSmall, color = NeonGold)
                    Text("等级", style = MaterialTheme.typography.bodySmall, color = TextSecondary)
                }
                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    Text("${user.exp}", style = MaterialTheme.typography.headlineSmall, color = NeonBlue)
                    Text("经验值", style = MaterialTheme.typography.bodySmall, color = TextSecondary)
                }
                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    Text("${user.studyHours}", style = MaterialTheme.typography.headlineSmall, color = NeonGreen)
                    Text("学习(h)", style = MaterialTheme.typography.bodySmall, color = TextSecondary)
                }
                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    Text("${user.completedTasks}", style = MaterialTheme.typography.headlineSmall, color = NeonPurple)
                    Text("完成任务", style = MaterialTheme.typography.bodySmall, color = TextSecondary)
                }
            }
            if (user.isCurrentUser) {
                Spacer(Modifier.height(16.dp))
                Text("继续加油，保持领先！", style = MaterialTheme.typography.bodyMedium, color = NeonGold)
            }
            Spacer(Modifier.height(24.dp))
        }
    }
}
