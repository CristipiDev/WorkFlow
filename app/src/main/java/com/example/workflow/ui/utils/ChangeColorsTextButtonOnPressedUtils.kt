package com.example.workflow.ui.utils

import androidx.compose.ui.graphics.Color

fun ChangeColorsTextButtonOnPressedUtils(isPressed: Boolean, backgroundColor: Color): Pair<Color, Color> {
    val colors: Pair<Color, Color> = Pair(
        Color(0xFF000000),
        Color(0xFFFFFFFF)
    )
    if(backgroundColor == Color(0xFFFFFFFF)) {
        if (isPressed) {
            return Pair(Color(0xFFFFFFFF), Color(0xFF000000))
        }
    } else {
        if (!isPressed) {
            return Pair(Color(0xFFFFFFFF), Color(0xFF000000))
        }
    }
    return colors
}