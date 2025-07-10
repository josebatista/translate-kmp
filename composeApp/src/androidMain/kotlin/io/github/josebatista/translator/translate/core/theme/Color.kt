package io.github.josebatista.translator.translate.core.theme

import androidx.compose.material3.lightColorScheme
import androidx.compose.ui.graphics.Color
import io.github.josebatista.translator.core.presentation.Colors

val AccentViolet = Color(Colors.ACCENT_VIOLET)
val LightBlue = Color(Colors.LIGHT_BLUE)
val LightBlueGrey = Color(Colors.LIGHT_BLUE_GREY)
val TextBlack = Color(Colors.TEXT_BLACK)
val DarkGrey = Color(Colors.DARK_GREY)

val lightColors = lightColorScheme(
    primary = AccentViolet,
    background = LightBlueGrey,
    onPrimary = Color.White,
    onBackground = TextBlack,
    surface = Color.White,
    onSurface = TextBlack
)

val darkColors = lightColorScheme(
    primary = AccentViolet,
    background = DarkGrey,
    onPrimary = Color.White,
    onBackground = Color.White,
    surface = DarkGrey,
    onSurface = Color.White
)
