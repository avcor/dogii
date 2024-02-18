package com.example.digii.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.digii.R
import com.example.digii.ui.fragments.viewmodel.SavePostViewModel
import javax.inject.Inject

class SavePostFragment @Inject constructor(): Fragment() {

    companion object {
        fun newInstance() = SavePostFragment()
    }

    private lateinit var viewModel: SavePostViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_save_post, container, false)
    }

}