package com.suein.myapplication.test.fragmentnav

import android.os.Bundle
import androidx.activity.ComponentActivity
import com.suein.myapplication.databinding.NaviFragmnetActivityBinding

/**
 * Fragment 내의 Compose Navigation 구동을 테스트 한다.
 */
class TestNaviFragmentActivity : ComponentActivity() {

    private lateinit var binding : NaviFragmnetActivityBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = NaviFragmnetActivityBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

}