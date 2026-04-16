package com.pradiph31.learnjapanese.ui.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

private val JapaneseColorScheme = lightColorScheme(
    primary = Red,
    onPrimary = Color.White,
    secondary = Gold,
    onSecondary = Color.White,
    tertiary = KatakanaColor,
    background = Paper,
    onBackground = Ink,
    surface = CardBg,
    onSurface = Ink,
    surfaceVariant = CardBg,
    onSurfaceVariant = Muted,
    outline = Color(0x261A1208),
)

@Composable
fun LearnJapaneseTheme(
    content: @Composable () -> Unit
) {
    MaterialTheme(
        colorScheme = JapaneseColorScheme,
        typography = Typography,
        content = content
    )
}