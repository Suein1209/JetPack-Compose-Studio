package com.suein.myapplication.jpub.chapter.ch49

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlin.math.roundToInt

class Chapter49SwipeActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MainScreen()
        }
    }


    @OptIn(ExperimentalMaterialApi::class)
    @Composable
    fun MainScreen() {
        val parentBoxWidth = 320.dp
        val childBoxSides = 30.dp
        val swipeAbleState = rememberSwipeableState(initialValue = "L")
        val widthPx = with(LocalDensity.current) {
            (parentBoxWidth - childBoxSides).toPx()
        }
        val anchors = mapOf(0f to "L", widthPx / 2 to "C", widthPx to "R")

        Box(
            modifier = Modifier
                .padding(20.dp)
                .width(parentBoxWidth)
                .height(childBoxSides)
                .swipeable(
                    state = swipeAbleState, // 여기서 터치의 스와이프를의 값을 설정한다.
                    anchors = anchors, // 위에 스와이프 값은 아래 앵커 맵에 따라 전달하게 된다.
                    thresholds = { _, _ -> FractionalThreshold(0.5f) }, // 스크롤로 옮기다가 놨을때 좌나 우로 이동하는 정도 -> 0.5 는 50% 이다.
                    orientation = Orientation.Horizontal
                )
        ) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(5.dp)
                    .background(Color.DarkGray)
                    .align(Alignment.CenterStart)
            )
            Box(
                modifier = Modifier
                    .size(10.dp)
                    .background(Color.DarkGray, shape = CircleShape)
                    .align(Alignment.CenterStart)
            )
            Box(
                modifier = Modifier
                    .size(10.dp)
                    .background(Color.DarkGray, shape = CircleShape)
                    .align(Alignment.Center)
            )
            Box(
                modifier = Modifier
                    .size(10.dp)
                    .background(Color.DarkGray, shape = CircleShape)
                    .align(Alignment.CenterEnd)
            )

            /* 여기까지가 배경 그린 부분 */

            Box(modifier = Modifier
                .offset { IntOffset(swipeAbleState.offset.value.roundToInt(), 0) } // offset 이동에 따라 스와이핑? 박스를 스크롤 한다.
                .size(childBoxSides)
                .background(Color.Blue),
                contentAlignment = Alignment.Center
            ) {
                Text(text = swipeAbleState.currentValue, color = Color.White, fontSize = 22.sp)
            }
        }
    }

    @Preview(showBackground = true)
    @Composable
    fun DefaultPreview() {
        MainScreen()
    }
}