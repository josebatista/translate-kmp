package io.github.josebatista.translator.translate.core.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import io.github.josebatista.translator.R

val SfProText = FontFamily(
    Font(resId = R.font.sf_pro_text_regular, weight = FontWeight.Normal),
    Font(resId = R.font.sf_pro_text_medium, weight = FontWeight.Medium),
    Font(resId = R.font.sf_pro_text_bold, weight = FontWeight.Bold),
)

val Typography = Typography(
    headlineLarge = TextStyle(
        fontFamily = SfProText,
        fontWeight = FontWeight.Bold,
        fontSize = 30.sp,
    ),
    headlineMedium = TextStyle(
        fontFamily = SfProText,
        fontWeight = FontWeight.Bold,
        fontSize = 24.sp,
    ),
    headlineSmall = TextStyle(
        fontFamily = SfProText,
        fontWeight = FontWeight.Medium,
        fontSize = 18.sp,
    ),
    bodyMedium = TextStyle(
        fontFamily = SfProText,
        fontWeight = FontWeight.Normal,
        fontSize = 14.sp,
    ),
    bodySmall = TextStyle(
        fontFamily = SfProText,
        fontWeight = FontWeight.Normal,
        fontSize = 12.sp,
    )
)
