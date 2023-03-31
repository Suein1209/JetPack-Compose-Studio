package com.suein.myapplication.jpub.chapter.ch48

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview

class Chapter48GestureActivity : ComponentActivity(), ClickDemoScreens, TapPressDemoScreens, ScrollableModifierScreens {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
//            ClickDemoScreen() // box에 터치 이벤트 감지 넣기
//            TabPressDemo() // 탭의 종류 파악
//            ScrollableModifierScreen() // 스크롤의 이벤트 포지션을 받아서 Box 드래그 시키기
            VerticalOrHorizontalScrollModifierScreen() // modifier에 가로 스크롤 remember를 추가해 canvas 로 따라 움직이도록 그리기
        }
    }

    @Preview(showBackground = true)
    @Composable
    fun DefaultPreview() {
//        ClickDemoScreen()
        TabPressDemo()
    }
}