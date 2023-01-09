package com.suein.myapplication.toto

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import kotlinx.coroutines.CancellationException
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class TotoStudyActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyScreen()
        }
    }

    @Composable
    fun MyScreen(
        scaffoldState: ScaffoldState = rememberScaffoldState(),
        mainViewModel: TotoViewModel = viewModel()
    ) {
        Scaffold(scaffoldState = scaffoldState) { padding ->
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(10.dp)
            ) {

                val onTimeout by mainViewModel.timeoutText.collectAsState()

                HelloScreen()
                Box {
                    Column(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.spacedBy(4.dp)
                    ) {
//                        ShowSnackBarButton(scaffoldState)
                        StartTimeoutButton()
                        ShowLazySnackBar(scaffoldState, onTimeout)
                    }
                }
            }
        }
    }

    @Composable
    fun HelloScreen(mainViewModel: TotoViewModel = viewModel()) {
        val name: String by mainViewModel.name.collectAsState(initial = "")
        HelloContent(name = name, onNameChange = { mainViewModel.onNameChange(it) })
    }

    @Composable
    fun HelloContent(name: String, onNameChange: (String) -> Unit) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(
                text = "Hello, $name",
                modifier = Modifier.padding(bottom = 8.dp),
                style = MaterialTheme.typography.h5
            )
            OutlinedTextField(
                value = name,
                onValueChange = onNameChange,
                label = { Text("Name") }
            )
        }
    }

    @Composable
    fun ShowSnackBarButton(scaffoldState: ScaffoldState = rememberScaffoldState()) {
        // ShowSnackBarButton's lifecycle과 함꼐하는 CoroutineScope을 생성
        val scope = rememberCoroutineScope()

        Button(
            onClick = {
                scope.launch {
                    scaffoldState.snackbarHostState.showSnackbar("Something happened!")
                }
            }
        ) {
            Text("Show SnackBar")
        }
    }

    // 문구 변경을 위한 버튼
    @Composable
    fun StartTimeoutButton(mainViewModel: TotoViewModel = viewModel()) {
        val scope = rememberCoroutineScope()
        Button(onClick = {
            scope.launch {
                mainViewModel.changeTimeoutText("Timeout changed by button")
            }
        }) {
            Text("Change Timeout Text!!")
        }
    }

    @Composable
    fun ShowLazySnackBar(
        scaffoldState: ScaffoldState = rememberScaffoldState(),
        timeoutText: String
    ) {

        val currentTimeoutText by rememberUpdatedState(newValue = timeoutText)

        LaunchedEffect(true) {
            Log.i("composeTest", "ShowLazySnackBarButton() - timeout started!")
            try {
                delay(3000L)
          scaffoldState.snackbarHostState.showSnackbar(currentTimeoutText)
//                scaffoldState.snackbarHostState.showSnackbar(timeoutText)
            } catch (ce: CancellationException) {
                Log.d("composeTest", "ShowLazySnackBarButton() - canceled!")
            }
        }
    }
}