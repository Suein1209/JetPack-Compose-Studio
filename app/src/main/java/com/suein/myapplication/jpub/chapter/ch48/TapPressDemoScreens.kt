package com.suein.myapplication.jpub.chapter.ch48

import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.unit.dp

/**
 * 탭 종류 별로 캐치 하기
 */
interface TapPressDemoScreens {

    @Composable
    fun TabPressDemo() {
        var textState by remember { mutableStateOf("Waiting ....") }
        val tabHandler = { status: String ->
            textState = status
        }

        Column(horizontalAlignment = Alignment.CenterHorizontally, modifier = Modifier.fillMaxSize()) {
            Box(
                modifier = Modifier
                    .padding(10.dp)
                    .size(100.dp)
                    .background(Color.Blue)
                    .pointerInput(Unit) {
                        detectTapGestures(
                            onPress = { tabHandler("onPress Detected") },
                            onDoubleTap = { tabHandler("onDoubleTap Detected") },
                            onLongPress = { tabHandler("onLongPress Detected") },
                            onTap = { tabHandler("onTap Detected") }
                        )
                    }
            )

            Spacer(modifier = Modifier.height(10.dp))

            Text(text = textState)
        }
    }
}