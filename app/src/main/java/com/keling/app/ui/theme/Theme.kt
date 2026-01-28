package com.keling.app.ui.theme

import android.app.Activity
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.Density
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalView
import androidx.core.view.WindowCompat
import androidx.hilt.navigation.compose.hiltViewModel
import com.keling.app.ui.screens.settings.AccessibilityViewModel

/**
 * 课灵应用主题 - 科幻赛博朋克风格
 * 始终使用深色主题以保持沉浸感
 */

private val KelingColorScheme = darkColorScheme(
    // 主色
    primary = NeonBlue,
    onPrimary = DarkBackground,
    primaryContainer = NeonBlueDark,
    onPrimaryContainer = TextPrimary,

    // 次要色
    secondary = NeonPurple,
    onSecondary = DarkBackground,
    secondaryContainer = NeonPurpleDark,
    onSecondaryContainer = TextPrimary,

    // 第三色
    tertiary = NeonPink,
    onTertiary = DarkBackground,
    tertiaryContainer = NeonPinkDark,
    onTertiaryContainer = TextPrimary,

    // 背景
    background = DarkBackground,
    onBackground = TextPrimary,

    // 表面
    surface = DarkSurface,
    onSurface = TextPrimary,
    surfaceVariant = DarkSurfaceVariant,
    onSurfaceVariant = TextSecondary,

    // 轮廓
    outline = DarkBorder,
    outlineVariant = DarkBorder,

    // 错误
    error = NeonRed,
    onError = DarkBackground,
    errorContainer = NeonRedDark,
    onErrorContainer = TextPrimary,

    // 反转
    inverseSurface = TextPrimary,
    inverseOnSurface = DarkBackground,
    inversePrimary = NeonBlueDark,

    // 其他
    scrim = Color.Black.copy(alpha = 0.6f),
    surfaceTint = NeonBlue
)

/** 高对比度配色：更亮的文字与轮廓，便于视障用户 */
private val HighContrastColorScheme = darkColorScheme(
    primary = NeonBlue,
    onPrimary = DarkBackground,
    primaryContainer = NeonBlueDark,
    onPrimaryContainer = Color.White,
    secondary = NeonPurple,
    onSecondary = DarkBackground,
    secondaryContainer = NeonPurpleDark,
    onSecondaryContainer = Color.White,
    tertiary = NeonPink,
    onTertiary = DarkBackground,
    tertiaryContainer = NeonPinkDark,
    onTertiaryContainer = Color.White,
    background = DarkBackground,
    onBackground = Color.White,
    surface = DarkSurface,
    onSurface = Color.White,
    surfaceVariant = DarkSurfaceVariant,
    onSurfaceVariant = Color(0xFFE0E4EC),
    outline = NeonBlue.copy(alpha = 0.8f),
    outlineVariant = NeonBlue.copy(alpha = 0.5f),
    error = NeonRed,
    onError = DarkBackground,
    errorContainer = NeonRedDark,
    onErrorContainer = Color.White,
    inverseSurface = Color.White,
    inverseOnSurface = DarkBackground,
    inversePrimary = NeonBlueDark,
    scrim = Color.Black.copy(alpha = 0.7f),
    surfaceTint = NeonBlue
)

@Composable
fun KelingTheme(
    content: @Composable () -> Unit
) {
    val colorScheme = KelingColorScheme
    val view = LocalView.current

    if (!view.isInEditMode) {
        SideEffect {
            val window = (view.context as Activity).window
            window.statusBarColor = Color.Transparent.toArgb()
            window.navigationBarColor = Color.Transparent.toArgb()
            WindowCompat.getInsetsController(window, view).apply {
                isAppearanceLightStatusBars = false
                isAppearanceLightNavigationBars = false
            }
        }
    }

    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography,
        content = {
            AccessibilityProvider(content = content)
        }
    )
}

@Composable
private fun AccessibilityProvider(
    content: @Composable () -> Unit
) {
    val vm: AccessibilityViewModel = hiltViewModel()
    val state by vm.uiState.collectAsState()
    val density = LocalDensity.current
    val customDensity = Density(density.density, fontScale = state.fontScaleMultiplier())

    CompositionLocalProvider(
        LocalReduceMotion provides state.reduceMotion
    ) {
        CompositionLocalProvider(
            androidx.compose.ui.platform.LocalDensity provides customDensity
        ) {
            if (state.highContrastMode) {
                MaterialTheme(
                    colorScheme = HighContrastColorScheme,
                    typography = Typography,
                    content = content
                )
            } else {
                content()
            }
        }
    }
}
