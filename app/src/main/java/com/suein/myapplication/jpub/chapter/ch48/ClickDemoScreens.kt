package com.suein.myapplication.jpub.chapter.ch48

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

/**
 * 클릭 제스쳐 감지
 */
interface ClickDemoScreens {

    @Composable
    fun ClickDemoScreen() {
        var colorState by remember { mutableStateOf(true) }
        var bgColor by remember { mutableStateOf(Color.Blue) }
        val clickHandler = {
            colorState = !colorState
            bgColor = if (colorState) {
                Color.Blue
            } else {
                Color.DarkGray
            }
        }

        Box(
            modifier = Modifier
                .clickable { clickHandler() } //영역에 클릭을 건다.
                .background(bgColor)
                .size(100.dp)
        )
    }
}