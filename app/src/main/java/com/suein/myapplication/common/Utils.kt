package com.suein.myapplication.common

import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.Density
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp

class Utils {
}
private fun Int.textDp(density: Density): TextUnit = with(density) {
    this@textDp.dp.toSp()
}

/**
 * Compose에서는 대부분 Number.dp를 사용하나 텍스트의 사이즈 경우는 TextUnit으로 설정하기때문에
 * 별도 처리가 필요
 */
val Int.textDp: TextUnit
    @Composable get() =  this.textDp(density = LocalDensity.current)