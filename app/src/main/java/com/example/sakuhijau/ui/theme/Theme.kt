package com.example.sakuhijau.ui.theme

import android.app.Activity
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalView
import androidx.core.view.WindowCompat

private val LightColorScheme = lightColorScheme(
    primary = PrimaryGreen,
    secondary = LightGreen,
    tertiary = DarkGreen,
    background = WhiteBackground,
    surface = WhiteBackground,
    onPrimary = OnPrimaryGreen,
    onSecondary = OnLightGreen,
    onTertiary = OnPrimaryGreen,
    onBackground = Color(0xFF1A1C19),
    onSurface = Color(0xFF1A1C19),
)

private val DarkColorScheme = darkColorScheme(
    primary = PrimaryGreen,
    secondary = LightGreen,
    tertiary = DarkGreen,
    background = Color(0xFF1A1C19),
    surface = Color(0xFF1A1C19),
    onPrimary = OnPrimaryGreen,
    onSecondary = OnLightGreen,
    onTertiary = OnPrimaryGreen,
    onBackground = WhiteBackground,
    onSurface = WhiteBackground,
)

@Composable
fun SakuHijauTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    val colorScheme = if (darkTheme) DarkColorScheme else LightColorScheme
    val view = LocalView.current
    
    if (!view.isInEditMode) {
        SideEffect {
            val window = (view.context as Activity).window
            window.statusBarColor = colorScheme.primary.toArgb()
            WindowCompat.getInsetsController(window, view).isAppearanceLightStatusBars = !darkTheme
        }
    }

    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography,
        content = content
    )
}
