package com.suein.myapplication.jpub.chapter.ch48

import androidx.compose.foundation.*
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.gestures.rememberScrollableState
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.res.imageResource
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import kotlin.math.roundToInt


/**
 * 스크롤의 이벤트 포지션을 받아서 Box 드래그 시키기
 */
interface ScrollableModifierScreens {

    @Composable
    fun ScrollableModifierScreen() {
        var offset by remember { mutableStateOf(0f) }

        Box(modifier = Modifier
            .fillMaxSize()
            .scrollable(
                orientation = Orientation.Vertical,
                state = rememberScrollableState { distance ->
                    offset += distance
                    distance
                }
            ))
        Box(modifier = Modifier
            .size(90.dp)
            .offset { IntOffset(0, offset.roundToInt()) }
            .background(Color.Red))
    }


    /**
     * 박스 영역안에서 스크롤시 Canvas 요소를 움직인다.
     */
    @Composable
    fun VerticalOrHorizontalScrollModifierScreen() {
        val image = ImageBitmap.imageResource(id = com.suein.myapplication.R.drawable.vacation)
        Box(
            modifier = Modifier
                .size(150.dp)
                .verticalScroll(rememberScrollState())
                .horizontalScroll(rememberScrollState())
        ) {
            Canvas(modifier = Modifier.size(360.dp, 270.dp)) {
                drawImage(image = image, topLeft = Offset(x = 0f, y = 0f))
            }
        }
    }
}