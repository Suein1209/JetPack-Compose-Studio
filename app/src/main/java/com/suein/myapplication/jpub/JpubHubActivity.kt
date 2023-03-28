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
        }
    }

    @Preview(showBackground = true)
    @Composable
    fun DefaultPreview() {
        ChapterListView()
    }
}