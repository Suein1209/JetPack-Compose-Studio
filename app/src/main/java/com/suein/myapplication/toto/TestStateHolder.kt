package com.suein.myapplication.toto

import android.util.Log
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.Icon
import androidx.compose.material.LocalContentColor
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.Saver
import androidx.compose.runtime.saveable.listSaver
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.flow.filter

@Preview()
@Composable
fun TestPreview() {
    FavoriteFoodInput { tt ->
        Log.e("stein", "TestPreview : $tt")
    }
}

@Composable
fun FavoriteFoodInput(onFavoriteFoodInputChanged: (String) -> Unit) {
    val foodEditableInputState = rememberFoodEditableInputState(hint = "Write your favorite food?")
    Surface(color = Color.Gray) {
        Row(modifier = Modifier.fillMaxWidth()) {
            Icon(Icons.Filled.Favorite, contentDescription = "")
            Spacer(modifier = Modifier.padding(4.dp))
            FoodEditableInput(
                Modifier.align(Alignment.CenterVertically),
                foodEditableInputState
            )
        }
    }

    val currentFavoriteFoodInputChanged by rememberUpdatedState(onFavoriteFoodInputChanged)
    LaunchedEffect(foodEditableInputState) {
        snapshotFlow { foodEditableInputState.text }
            .filter { !foodEditableInputState.isHint }
            .collect {
                currentFavoriteFoodInputChanged(foodEditableInputState.text)
            }
    }
}

@Composable
fun rememberFoodEditableInputState(hint: String): FoodEditableInputState {
    return rememberSaveable(hint, saver = FoodEditableInputState.Saver) {
        FoodEditableInputState(hint, hint)
    }
}

class FoodEditableInputState(private val hint: String, initailText: String) {
    var text by mutableStateOf(initailText)
    val isHint: Boolean
        get() {
            Log.e("suein", "isHint : get() = $text")
            return text == hint
        }

    companion object {
        val Saver: Saver<FoodEditableInputState, *> = listSaver(
            save = { listOf(it.hint, it.text) },
            restore = {
                FoodEditableInputState(hint = it[0], initailText = it[1])
            }
        )
    }
}

@Composable
fun FoodEditableInput(
    modifier: Modifier = Modifier,
    state: FoodEditableInputState = rememberFoodEditableInputState("")
) {
    BasicTextField(
        modifier = modifier,
        value = state.text,
        onValueChange = {
            state.text = it
        },
        textStyle = if (state.isHint) {
            MaterialTheme.typography.caption.copy(color = Color.LightGray)
        } else {
            MaterialTheme.typography.body1.copy(color = LocalContentColor.current)
        },
        cursorBrush = SolidColor(LocalContentColor.current)
    )
}