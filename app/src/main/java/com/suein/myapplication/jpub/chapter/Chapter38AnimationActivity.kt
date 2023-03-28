package com.suein.myapplication.jpub.chapter

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

/**
 * 상태 함수로 애니메이션 동작
 */
class Chapter38AnimationActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
//            MainScreen()
            RotateDemo()
        }
    }


    @Composable
    fun MainScreen() {
        var temperature by remember { mutableStateOf(80) }
        val animatedColor: Color by animateColorAsState(
            targetValue = if (temperature > 92) Color.Red else Color.Green,
            animationSpec = tween(4500)
        )
        AnimateView(animatedColor = animatedColor)
    }

    @Composable
    fun AnimateView(animatedColor: Color) {
        Box(
            modifier = Modifier
                .size(width = 20.dp, height = 200.dp)
                .background(animatedColor)
        ) {

        }
    }

    // 클릭시 로테이트 애니메이션 구현
    @Composable
    fun RotateDemo() {
        var rotated by remember { mutableStateOf(false) }

        //애니메이션 state 를 사용하면 tween 시간 만큼 애니메이션이 들어간다.
        val angle by animateFloatAsState(targetValue = if (rotated) 360f else 0f, animationSpec = tween(durationMillis = 2500, easing = LinearEasing))

        Column(horizontalAlignment = Alignment.CenterHorizontally, modifier = Modifier.fillMaxWidth()) {
            Image(
                painter = painterResource(id = com.suein.myapplication.R.drawable.propeller),
                contentDescription = "프로펠러",
                modifier = Modifier
                    .rotate(angle)
                    .padding(10.dp)
                    .size(300.dp)
            )

            Button(onClick = { rotated = !rotated }, modifier = Modifier.padding(10.dp)) {
                Text(text = "Rotate Propeller")
            }
        }
    }


    enum class BoxColor {
        Red, Magenta
    }

    @Composable
    fun ColorChangDemo() {
        var colorState by remember { mutableStateOf(BoxColor.Red) }
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.fillMaxWidth()
        ) {
            Box(
                modifier = Modifier
                    .padding(20.dp)
                    .size(200.dp)
                    .background(Color.Red)
            )

            Button(onClick = { /*TODO*/ }) {
                
            }
        }
    }


    @Preview(showBackground = true)
    @Composable
    fun DefaultPreview() {
//        MainScreen()
//        RotateDemo() //클릭시 로테이트 애니메이션 구현
        ColorChangDemo()
    }
}