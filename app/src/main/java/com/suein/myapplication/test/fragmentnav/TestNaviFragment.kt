package com.suein.myapplication.test.fragmentnav

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.platform.ViewCompositionStrategy
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.fragment.app.Fragment

class TestNaviFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return ComposeView(requireContext()).apply {
            setViewCompositionStrategy(ViewCompositionStrategy.DisposeOnViewTreeLifecycleDestroyed)
            setContent {
                MainNaviView()
            }
        }
    }
}

@Composable
private fun MainNaviView() {
    Button(
        onClick = {},
        modifier = Modifier
            .fillMaxWidth()
            .height(30.dp)
    ) {
        Text(text = "테스트 버튼")
    }

}

@Preview
@Composable
private fun DefaultPreview() {
    MainNaviView()
}