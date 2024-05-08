package com.example.digii.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.digii.adapter.FeedPostRecyclerAdapter
import com.example.digii.data.PostApiResponseType
import com.example.digii.databinding.FragmentFeedBinding
import com.example.digii.viewmodel.MainActViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class FeedFragment @Inject constructor(
    private val postRecyclerAdapter: FeedPostRecyclerAdapter
) : Fragment() {

    private val viewModel: MainActViewModel by viewModels()
    lateinit var progressC: ProgressBar
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return FragmentFeedBinding.inflate(inflater, container, false).also {
            it.feedRecycler.layoutManager = LinearLayoutManager(activity)
            it.feedRecycler.adapter = postRecyclerAdapter
            progressC = it.progress
        }.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        lifecycleScope.launch {
            viewModel.postResponseStateFlow.collect {
                when (it) {
                   is PostApiResponseType.Failure -> {
                        Toast.makeText(activity, "Api Failed", Toast.LENGTH_SHORT).show()
                        progressC.visibility = View.GONE
                    }

                    is PostApiResponseType.Loading -> {
                        progressC.visibility = View.VISIBLE
                    }

                    is PostApiResponseType.NoData -> {
                        Toast.makeText(activity, "No Data", Toast.LENGTH_SHORT).show()
                        progressC.visibility = View.GONE
                    }

                    is PostApiResponseType.Success -> {
                        postRecyclerAdapter.updateDataSet(it.responseData.data)
                        progressC.visibility = View.GONE
                    }
                }
            }
        }

        postRecyclerAdapter.setSaveFun { post ->
            lifecycleScope.launch {
                viewModel.savePost(post)
            }
        }
    }
}