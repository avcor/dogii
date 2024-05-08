package com.example.digii.ui.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.compose.runtime.collectAsState
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.lifecycleScope
import com.example.digii.R
import com.example.digii.adapter.ViewPagerAdapter
import com.example.digii.annotation.FeednSaveFragAdapter
import com.example.digii.databinding.ActivityMainBinding
import com.example.digii.viewmodel.MainActViewModel
import com.google.android.material.tabs.TabLayoutMediator
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding

    @FeednSaveFragAdapter
    @Inject
    lateinit var adapter: ViewPagerAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        binding.apply {
            viewPager.adapter = adapter
            TabLayoutMediator(tabLayout, viewPager) { tab, position ->
                tab.text = when(position){
                    0 -> {"Feed"}
                    1 -> {"Saved"}
                    else -> {"Tab"}
                }
            }.attach()
        }

    }
}