package com.suein.myapplication.test.fragmentnav

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.platform.ViewCompositionStrategy
import androidx.compose.ui.tooling.preview.Preview
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.suein.myapplication.common.textDp
import com.suein.myapplication.databinding.NaviFragmentBinding


sealed class TestNavRoute(val route: String) {
    object Test1 : TestNavRoute("First")
    object Test2 : TestNavRoute("Second")
}

class TestNaviFragment : Fragment() {


    private var _binding: NaviFragmentBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
//        _binding = NaviFragmentBinding.inflate(inflater, container, false)
//        val view = binding.root
//        binding.composeView.apply {
//            // Dispose of the Composition when the view's LifecycleOwner
//            // is destroyed
//            setViewCompositionStrategy(ViewCompositionStrategy.DisposeOnViewTreeLifecycleDestroyed)
//            setContent {
//                MainNaviView()
//                // In Compose world
////                    Text("Hello Compose!")
//            }
//        }
//        return view

        return ComposeView(requireContext()).apply {
            setViewCompositionStrategy(ViewCompositionStrategy.DisposeOnViewTreeLifecycleDestroyed)
            setContent {
                MainNaviView()
            }
        }
    }
}

@Composable
fun MainNaviView() {
    Log.e("suein", "TestFirst: ")
    Box(
        modifier = Modifier
            .background(Color.Blue)
            .fillMaxSize()
    ) {
        Text(text = "First View", fontSize = 20.textDp)
    }

//    val navController = rememberNavController()
//    NavHost(navController = navController, startDestination = TestNavRoute.Test1.route) {
//        composable(TestNavRoute.Test1.route) {
//            Log.e("suein", "MainNaviView: 1")
//            TestFirst(navController = navController)
//        }
//
//        composable(TestNavRoute.Test2.route + "/{fragmentId}") { backStackEntry ->
//            Log.e("suein", "MainNaviView: 2")
//            val fragmentId = backStackEntry.arguments?.getString("fragmentId")
//            TestSecond(navController = navController, fragmentId = fragmentId)
//        }
//    }
}

@Composable
private fun TestFirst(navController: NavController) {
    Log.e("suein", "TestFirst: ")
    Box(
        modifier = Modifier
            .background(Color.Blue)
            .fillMaxSize()
    ) {
        Text(text = "First View", fontSize = 20.textDp)
    }
}

@Preview
@Composable
private fun PreviewTestFirst() {
    TestFirst(rememberNavController())
}

@Composable
private fun TestSecond(navController: NavController, fragmentId: String?) {
    Log.e("suein", "TestSecond: 2")
    Column() {
        Text(text = "First View : Id = $fragmentId", fontSize = 20.textDp)
        Button(onClick = {
            navController.navigate(TestNavRoute.Test2.route + "/${((fragmentId?.toInt() ?: 0) + 1)}") //이렇게 파라미터를 넣을 수 있다.
        }) { Text(text = "Start Second2") }
    }
}

@Preview
@Composable
private fun DefaultPreview() {
    MainNaviView()
}