package com.suein.myapplication.jpub.chapter

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.animateColor
import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.*
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

/**
 * 상태 함수로 애니메이션 동작
 */
class Chapter38AnimationActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ComponentList()
        }
    }

    @Composable
    fun ComponentList() {
        val scrollState = rememberScrollState()
        Column(Modifier.verticalScroll(scrollState)) {
            TransitionDemo()
            MotionsDemo()
            ColorChangDemo()
            RotateDemo()
        }
    }

    /**
     * 상자가 이동하면서 색도 같이 변하는 동시에 두개의 애니메이션이 시작한다.
     * [updateTransition] - 함수를 이용하면 하나의 대상 상태를 기반으로 여러 애니메이션을 병렬로 실행 할 수 있다.
     */
    @Composable
    fun TransitionDemo() {
        var boxState by remember { mutableStateOf(BoxPosition.Start) }
        val transition = updateTransition(targetState = boxState, label = "My Transition")
        var screenWidth = LocalConfiguration.current.screenWidthDp.dp
        val animatedColor: Color by transition.animateColor(
//            transitionSpec = tween(durationMillis = 4000), //이거 안된다
            label = "animatedColor"
        ) { state ->
            when (state) { // 클릭을 통해 상태가 변경되면 애니메이션으로 색을 변경한다.
                BoxPosition.Start -> Color.Red
                BoxPosition.End -> Color.Magenta
            }
        }

        val animatedOffset: Dp by transition.animateDp(
//            transitionSpec = tween(durationMillis = 4000), //이거 안된다
            label = "animatedOffset"
        ) { state ->
            when (state) { //클릭을 통해 상태가 변경된다면 포지션을 옮긴다.
                BoxPosition.Start -> 0.dp
                BoxPosition.End -> screenWidth - 70.dp
            }
        }

        Column(modifier = Modifier.fillMaxWidth()) {
            Box(
                modifier = Modifier
                    .offset(x = animatedOffset, y = 20.dp)
                    .size(70.dp)
                    .background(animatedColor)
            )

            Spacer(modifier = Modifier.height(50.dp))

            Button(
                onClick = {
                    boxState = when (boxState) {
                        BoxPosition.Start -> BoxPosition.End
                        BoxPosition.End -> BoxPosition.Start
                    }
                },
                modifier = Modifier
                    .padding(20.dp)
                    .align(Alignment.CenterHorizontally)
            ) {
                Text(text = "Start Animation")
            }
        }
    }


    enum class BoxPosition {
        Start, End
    }

    /**
     * animateDpAsState를 이용한 움직임 애니메이션 처리
     * - 추가로 [keyframes] 도 추가되었다.
     */
    @Composable
    fun MotionsDemo() {
        var boxState by remember { mutableStateOf(BoxPosition.Start) }
        var screenWidth = (LocalConfiguration.current.screenWidthDp.dp)
        val boxSideLength = 70.dp
        val animatedOffset: Dp by animateDpAsState(
            targetValue = when (boxState) {
                BoxPosition.Start -> 0.dp
                BoxPosition.End -> screenWidth - boxSideLength
            },
            animationSpec = keyframes {
                durationMillis = 1000 // 전체 애니메이션 시간
                100.dp.at(10).with(LinearEasing) // 전체 1000 시간중 10에 100dp 만큼 이동
                110.dp.at(500).with(FastOutSlowInEasing) // 전체 1000 시간중 500에 110dp 만큼 이동
                200.dp.at(700).with(LinearOutSlowInEasing) // 전체 1000 시간중 700에 200dp 만큼 이동
                //그러니깐 빠르게(10) 100까지 이동했다가 500까지 느리게 갔다가 700까지 200dp를 이동한다.
            }
//            animationSpec = tween(500)
        )
        Column() {
            Box(
                modifier = Modifier
                    .offset(x = animatedOffset, y = 20.dp)
                    .size(boxSideLength)
                    .background(Color.Red)
            )
        }

        Spacer(modifier = Modifier.height(50.dp))

        Button(
            onClick = {
                boxState = when (boxState) {
                    BoxPosition.End -> BoxPosition.Start
                    BoxPosition.Start -> BoxPosition.End
                }
            },
            modifier = Modifier.padding(20.dp)
        ) {
            Text(text = "Move Box")
        }
    }


    /**
     * animateColorAsSate를 이용한 색상변경 애니메이션 처리
     */
    @Composable
    fun ColorChangDemo() {
        var colorState by remember { mutableStateOf(BoxColor.Red) }
        val animatedColor: Color by animateColorAsState(
            targetValue = when (colorState) {
                BoxColor.Red -> Color.Magenta
                BoxColor.Magenta -> Color.Red
            },
            animationSpec = tween(4500)

        )

        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.fillMaxWidth()
        ) {
            Box(
                modifier = Modifier
                    .padding(20.dp)
                    .size(200.dp)
                    .background(animatedColor)
            )

            Button(
                onClick = {
                    colorState = when (colorState) {
                        BoxColor.Red -> BoxColor.Magenta
                        BoxColor.Magenta -> BoxColor.Red
                    }
                }) {
                Text(text = "Change Color")
            }
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

    @Preview(showBackground = true)
    @Composable
    fun DefaultPreview() {
//        MainScreen()
//        RotateDemo() //클릭시 로테이트 애니메이션 구현
//        ColorChangDemo() // 클릭시 색 변경하게
        ComponentList()
    }
}