package com.example.digii.ui.activity

import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.databinding.DataBindingUtil
import com.bumptech.glide.Glide
import com.example.digii.DETAILS_DATA
import com.example.digii.R
import com.example.digii.data.remote.model.PostDataModel
import com.example.digii.databinding.ActivityDetailBinding

class DetailActivity : AppCompatActivity() {
    lateinit var binding: ActivityDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_detail)

        val userData = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            intent.getParcelableExtra(DETAILS_DATA, PostDataModel::class.java)
        } else {
            intent.getParcelableExtra(DETAILS_DATA)
        }

        binding.apply {
            data = userData
            Glide.with(this@DetailActivity).load(userData?.image).into(imageView)
        }



    }
}