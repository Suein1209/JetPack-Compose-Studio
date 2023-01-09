package com.suein.myapplication

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.suein.myapplication.toto.TotoStudyActivity

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
//            MyApplicationTheme {
            // A surface container using the 'background' color from the theme
            Greeting("Android")
            StartTotoActivity()
//                Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background) {
//                    Greeting("Android")
//                }
//            }
        }
    }

    @Composable
    fun Greeting(name: String) {
        Text(text = "Hello $name!")
    }

    @Composable
    fun StartTotoActivity() {
        Button(onClick = {
            startActivity(Intent(this, TotoStudyActivity::class.java))
        }) {
            Text(text = "투덜이 Activity 시작")
        }
    }

    @Preview(showBackground = true)
    @Composable
    fun DefaultPreview() {
        Greeting("Android")
    }
}