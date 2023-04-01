package com.suein.myapplication.jpub.chapter.ch51

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.repeatOnLifecycle
import kotlinx.coroutines.flow.SharedFlow

/**
 * SharedFlow 를 이용한 리스트 노출
 * - bg 상에서는 수집하지 않는다.(다만 viewmodel 에서는 emit 한다.)
 */
class Chapter51SharedFlowDemoActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ScreenSetup()
        }
    }

    @Composable
    fun ScreenSetup(viewModel: Chapter51SharedFlowDemoViewModel = Chapter51SharedFlowDemoViewModel()) {
        MainScreen(sharedFlow = viewModel.sharedFlow)
    }

    @Composable
    fun MainScreen(sharedFlow: SharedFlow<Int>) {
        val messages = remember { mutableStateListOf<Int>() }
        val lifecycleOwner = LocalLifecycleOwner.current

        LaunchedEffect(key1 = Unit) {
            /**
             * 라이프사이클의 START 이상에서만 collect를 실행한다.
             * - bg 상에서 emit 은 계속 되고 만약 앱이 fg 로 이동했다면 그 때부터 수집된 정보부터 collect 를 한다.
             */
            lifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                sharedFlow.collect {
                    println("Collecting $it")
                    messages.add(it)
                }
            }
        }

        LazyColumn {
            items(messages) {
                Text(
                    "Collected Value = $it",
                    style = MaterialTheme.typography.h5,
                    modifier = Modifier.padding(5.dp)
                )
            }
        }
    }

    @Preview(showBackground = true)
    @Composable
    fun DefaultPreview() {
        ScreenSetup()
    }

}