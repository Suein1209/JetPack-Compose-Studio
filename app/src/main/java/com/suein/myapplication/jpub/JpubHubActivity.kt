package com.suein.myapplication.jpub

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.suein.myapplication.jpub.chapter.Chapter38AnimationActivity
import com.suein.myapplication.jpub.chapter.ch41.Chapter41TestViewModelActivity
import com.suein.myapplication.jpub.chapter.ch46.Chapter46NavigationActivity
import com.suein.myapplication.jpub.chapter.ch47.Chapter47BottomNavigationMainActivity
import com.suein.myapplication.jpub.chapter.ch48.Chapter48GestureActivity
import com.suein.myapplication.jpub.chapter.ch49.Chapter49SwipeActivity
import com.suein.myapplication.jpub.chapter.ch51.Chapter51SharedFlowDemoActivity

@Suppress("SpellCheckingInspection")
class JpubHubActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ChapterListView()
        }
    }

    @Composable
    fun ChapterListView() {
        Column() {
            Button(modifier = Modifier.padding(10.dp), onClick = {
                startActivity(Intent(this@JpubHubActivity, Chapter38AnimationActivity::class.java))
            }) {
                Text(text = "Chapter 38 상태주도 애니메이션")
            }

            Button(modifier = Modifier.padding(10.dp), onClick = {
                startActivity(Intent(this@JpubHubActivity, Chapter41TestViewModelActivity::class.java))
            }) {
                Text(text = "Chapter 41 온도 입력 컴포저블 구현하기")
            }

            Button(modifier = Modifier.padding(10.dp), onClick = {
                startActivity(Intent(this@JpubHubActivity, Chapter46NavigationActivity::class.java))
            }) {
                Text(text = "Chapter 46 컴포즈 내비게이션 튜토리얼")
            }

            Button(modifier = Modifier.padding(10.dp), onClick = {
                startActivity(Intent(this@JpubHubActivity, Chapter47BottomNavigationMainActivity::class.java))
            }) {
                Text(text = "Chapter 47 하단 내비게이션 바 튜토리얼")
            }

            Button(modifier = Modifier.padding(10.dp), onClick = {
                startActivity(Intent(this@JpubHubActivity, Chapter48GestureActivity::class.java))
            }) {
                Text(text = "Chapter 48 제스쳐 감지하기")
            }

            Button(modifier = Modifier.padding(10.dp), onClick = {
                startActivity(Intent(this@JpubHubActivity, Chapter49SwipeActivity::class.java))
            }) {
                Text(text = "Chapter 49 스와이프 제스처 감지하기")
            }

            Button(modifier = Modifier.padding(10.dp), onClick = {
                startActivity(Intent(this@JpubHubActivity, Chapter51SharedFlowDemoActivity::class.java))
            }) {
                Text(text = "Chapter 51 젯팩 컴포즈 셰어드플로 튜토리얼")
            }
        }
    }

    @Preview(showBackground = true)
    @Composable
    fun DefaultPreview() {
        ChapterListView()
    }
}