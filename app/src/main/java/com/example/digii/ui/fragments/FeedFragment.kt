package com.example.digii.ui.fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.viewModels
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.digii.ui.fragments.viewmodel.FeedViewModel
import com.example.digii.R
import com.example.digii.adapter.PostRecyclerAdapter
import com.example.digii.data.PostApiResponseType
import com.example.digii.databinding.FragmentFeedBinding
import com.example.digii.viewmodel.MainActViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class FeedFragment @Inject constructor(): Fragment() {

    @Inject
    lateinit var postRecyclerAdapter: PostRecyclerAdapter
    private val viewModel: MainActViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
       return FragmentFeedBinding.inflate(inflater,container,false).also {
           it.feedRecycler.layoutManager = LinearLayoutManager(activity)
           it.feedRecycler.adapter = postRecyclerAdapter
       }.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        lifecycleScope.launch {
            viewModel.postResponseStateFlow.collect{
                when(it){
                    PostApiResponseType.Failure -> Toast.makeText(activity, "Api Failed", Toast.LENGTH_SHORT).show()
                    PostApiResponseType.Loading -> {}
                    PostApiResponseType.NoData -> Toast.makeText(activity, "No Data", Toast.LENGTH_SHORT).show()
                    is PostApiResponseType.Success -> postRecyclerAdapter.updateDataSet(it.responseData.data)
                }
            }
        }
    }
}