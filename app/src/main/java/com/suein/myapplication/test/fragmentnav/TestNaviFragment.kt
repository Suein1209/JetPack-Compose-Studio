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
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.suein.myapplication.common.textDp
import com.suein.myapplication.databinding.NaviFragmentBinding


sealed class TestNavRoute(val route: String) {
    object Test1 : TestNavRoute("First")
    object Test2 : TestNavRoute("Second")
    object Test3 : TestNavRoute("Third")
}

class TestNaviFragment : Fragment() {


    private var _binding: NaviFragmentBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        //binding 의 별도 영역에 넣기
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

        //compose view를 통으로 넘기는 방법
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
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = TestNavRoute.Test1.route) {
        composable(TestNavRoute.Test1.route) {
            TestFirst(navController = navController)
        }

        composable(TestNavRoute.Test2.route + "/{fragmentId}") { backStackEntry ->
            val fragmentId = backStackEntry.arguments?.getString("fragmentId")
            TestSecond(navController = navController, fragmentId = fragmentId)
        }

        composable(
            //다중 인자를 받을때
            TestNavRoute.Test3.route + "?fragmentId={fragmentId}&secondParam={secondParam}",
            arguments = listOf(
                navArgument("fragmentId") {
                    defaultValue = "9000"
                    type = NavType.StringType
                },
                navArgument("secondParam") {
                    defaultValue = "7000"
                    nullable = true
                    type = NavType.StringType
                }
            )
        ) { backStackEntry ->
            val fragmentId = backStackEntry.arguments?.getString("fragmentId")
            val secondParam = backStackEntry.arguments?.getString("secondParam")
            TestThird(navController = navController, fragmentId = fragmentId, secondParam = secondParam)
        }
    }
}

@Composable
private fun TestSecond(navController: NavController, fragmentId: String?) {
    Log.e("suein", "TestSecond: 2 ::: $fragmentId")
    Column() {
        Text(text = "First View : Id = $fragmentId", fontSize = 20.textDp)
        Button(onClick = {
            navController.navigate(TestNavRoute.Test2.route + "/${((fragmentId?.toInt() ?: 0) + 1)}") //이렇게 파라미터를 넣을 수 있다.
        }) { Text(text = "Start Second2") }
        Button(onClick = {
            //다중 인자를 넘길때
            navController.navigate(TestNavRoute.Test3.route + "?fragmentId=${3000}&secondParam=${4000}") //이렇게 다중 파라미터를 넣을 수 있다.
        }) { Text(text = "Start Second3") }
    }
}

@Composable
private fun TestFirst(navController: NavController) {
    Log.e("suein", "TestFirst: ")
    Box(
        modifier = Modifier
            .background(Color.Blue)
            .fillMaxSize()
    ) {
        Column() {
            Text(text = "First View", fontSize = 20.textDp)
            Button(onClick = {
                navController.navigate(TestNavRoute.Test2.route + "/0") //이렇게 파라미터를 넣을 수 있다.
            }) {
                Text(text = "두번째 화면으로 갑니다.")
            }
        }
    }
}

@Preview
@Composable
private fun PreviewTestFirst() {
    TestFirst(rememberNavController())
}

@Composable
private fun TestThird(navController: NavController, fragmentId: String?, secondParam: String?) {
    Log.e("suein", "TestThird: 3 ::: $fragmentId, secondParam = $secondParam")
    Column() {
        Text(text = "Third View : Id = $fragmentId", fontSize = 20.textDp)
        Button(onClick = {
            navController.navigate(TestNavRoute.Test3.route + "/${((fragmentId?.toInt() ?: 0) + 1)}&secondParam=${((fragmentId?.toInt() ?: 0) + 1) + 100}") //이렇게 다중 파라미터를 넣을 수 있다.
        }) { Text(text = "Start TestThird") }
    }
}

@Preview
@Composable
private fun DefaultPreview() {
    MainNaviView()
}