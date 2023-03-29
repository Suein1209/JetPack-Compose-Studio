package com.suein.myapplication.jpub.chapter.ch41

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.animation.Crossfade
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.suein.myapplication.jpub.theme.ViewModelDemoTheme
import com.suein.myapplication.R

/**
 * ViewModel을 이용해 화씨 섭시 input 구현
 */
class Chapter41TestViewModelActivity : ComponentActivity() {
    
    val viewModel : Chapter41TestViewModel by viewModels()
    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Log.e("suein","onCreate : 1")
            ViewModelDemoTheme {
                Log.e("suein","onCreate : 2")
                Surface(modifier = Modifier.fillMaxWidth(), color = MaterialTheme.colors.background) {
                    Log.e("suein","onCreate : 3")
                    ScreenSetup(viewModel)
                }
            }
        }
    }

    /**
     * - [Chapter41TestViewModel.isFahrenheit] 섭시 화씨가 변경되면 현재 화면이 트리거된다.
     * - [Chapter41TestViewModel.result] 만약 버튼을 누르게 되면 여기 글자가 바뀌게 되고 트리거가 된다.
     */
    @Composable
    fun ScreenSetup(viewModel: Chapter41TestViewModel = Chapter41TestViewModel()) {
        Log.e("suein","ScreenSetup :::::::::: $viewModel")
        MainScreen(
            isFahrenheit = viewModel.isFahrenheit,
            result = viewModel.result,
            convertTemp = {
                Log.e("suein", "ScreenSetup : convertTemp : $it")
                viewModel.convertTemp(it)
            },
            switchChange = {
                Log.e("suein", "ScreenSetup : switchChange : ")
                viewModel.switchChange()
            })
    }

    @Composable
    fun MainScreen(
        isFahrenheit: Boolean, result: String, convertTemp: (String) -> Unit, switchChange: () -> Unit
    ) {
        Log.e("suein", "MainScreen : isFahrenheit = $isFahrenheit, result = $result")
        Column(horizontalAlignment = Alignment.CenterHorizontally, modifier = Modifier.fillMaxWidth()) {
            var textState by remember { mutableStateOf("") }
            val onTextChange = { text: String -> textState = text }
            Text(text = "Temperature Converter", modifier = Modifier.padding(20.dp), style = MaterialTheme.typography.h4)

            InputRow(isFahrenheit = isFahrenheit, textState = textState, switchChange = switchChange, onTextChange = onTextChange)

            Text(text = result, modifier = Modifier.padding(20.dp), style = MaterialTheme.typography.h3)

            Button(onClick = { convertTemp(textState) }) {
                Text(text = "Convert Temperature")
            }
        }
    }

    /**
     * [Icon]이란 컴포넌트를 처음 사용함
     */
    @Composable
    fun InputRow(
        isFahrenheit: Boolean, textState: String, switchChange: () -> Unit, onTextChange: (String) -> Unit
    ) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Log.e("suein", "InputRow : Switch = $isFahrenheit")
            Switch(checked = isFahrenheit, onCheckedChange = {
                Log.e("suein", "InputRow : onCheckedChange = $it")
                switchChange()
            })
            OutlinedTextField(
                value = textState,
                onValueChange = {
                    onTextChange(it)
                },
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Number
                ),
                singleLine = true,
                label = { Text(text = "Enter temperature") },
                modifier = Modifier.padding(10.dp),
                textStyle = TextStyle(fontWeight = FontWeight.Bold, fontSize = 30.sp),
                trailingIcon = {
                    Icon(
                        painter = painterResource(id = R.drawable.baseline_ac_unit_24),
                        contentDescription = "frost",
                        modifier = Modifier.size(40.dp)
                    )
                }
            )
            Crossfade(targetState = isFahrenheit, animationSpec = tween(1000)) { visible ->
                when (visible) {
                    true -> Text(text = "\u2109", style = MaterialTheme.typography.h4)
                    false -> Text(text = "\u2103", style = MaterialTheme.typography.h4)
                }
            }
        }
    }

    @Preview(showBackground = true)
    @Composable
    fun DefaultPreviewScreen() {
        ScreenSetup()
    }
}