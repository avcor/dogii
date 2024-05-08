package com.example.digii.ui.fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.digii.DeletePost
import com.example.digii.adapter.SavedPostAdapter
import com.example.digii.data.local.model.LocalPostDataEntity
import com.example.digii.databinding.FragmentSavePostBinding
import com.example.digii.viewmodel.MainActViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class SavePostFragment @Inject constructor(
    private val savedPostAdapter: SavedPostAdapter
): Fragment() {
    private val viewModel: MainActViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return FragmentSavePostBinding.inflate(inflater,container,false).apply {
            savePostRecycler.layoutManager = LinearLayoutManager(activity)
            savePostRecycler.adapter = savedPostAdapter
        }.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        lifecycleScope.launch {
            viewModel.savedPostStateFlow.collect{
                savedPostAdapter.updateDataSet(it)
            }
        }

        savedPostAdapter.setSaveFun { post ->
            lifecycleScope.launch {
                viewModel.deleteSavedPost(post)
            }
        }
    }
}