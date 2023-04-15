package com.suein.myapplication

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.suein.myapplication.jpub.JpubHubActivity
import com.suein.myapplication.jpub.chapter.ch41.Chapter41TestViewModelActivity
import com.suein.myapplication.jpub.chapter.ch46.Chapter46NavigationActivity
import com.suein.myapplication.jpub.chapter.ch47.Chapter47BottomNavigationMainActivity
import com.suein.myapplication.jpub.chapter.ch48.Chapter48GestureActivity
import com.suein.myapplication.jpub.chapter.ch49.Chapter49SwipeActivity
import com.suein.myapplication.jpub.chapter.ch51.Chapter51SharedFlowDemoActivity
import com.suein.myapplication.test.fragmentnav.TestNaviFragmentActivity
import com.suein.myapplication.toto.TotoStudyActivity

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        startActivity(Intent(this, TestNaviFragmentActivity::class.java))
//        setContent {
//            StudyBooksTitleList()
//        }
    }

    @Composable
    fun StudyBooksTitleList() {
        Column(
            Modifier.fillMaxWidth(), horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Button(modifier = Modifier.padding(10.dp), onClick = {
                startActivity(Intent(this@MainActivity, TotoStudyActivity::class.java))
            }) {
                Text(text = "투덜이 Activity 시작")
            }
            Button(modifier = Modifier.padding(10.dp), onClick = {
                startActivity(Intent(this@MainActivity, JpubHubActivity::class.java))
            }) {
                Text(text = "핵심만 골라 배우는 젯팩 컴포즈(Jpub)")
            }
        }
    }

    @Preview(showBackground = true)
    @Composable
    fun DefaultPreview() {
        StudyBooksTitleList()
    }
}