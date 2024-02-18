package com.example.digii.adapter

import android.os.Build
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.digii.capitalizeFirstWord
import com.example.digii.data.model.PostDataModel
import com.example.digii.data.model.PostModel
import com.example.digii.databinding.CardPostBinding
import com.example.digii.militaryToDayTime
import java.time.Instant
import java.time.ZoneId
import java.time.format.DateTimeFormatter
import javax.inject.Inject

class PostRecyclerAdapter @Inject constructor() :
    RecyclerView.Adapter<PostRecyclerAdapter.ViewHolder>() {
    private var dataSet: List<PostDataModel> = listOf()

    inner class ViewHolder(val binding: CardPostBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = CardPostBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun getItemCount() = dataSet.size

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        with(holder.binding) {
            with(dataSet[position]) {
                titleTV.setText(text?.capitalizeFirstWord())
                likesTV.setText("${likes} likes")
                postedOnTV.setText("Posted on : ${publishDate.militaryToDayTime()}")
                authorTV.setText("Author : ${owner?.title?.capitalizeFirstWord()} ${owner?.firstName} ${owner?.lastName}")
                Glide.with(holder.itemView).load(image).into(imageView)
            }
        }

    }

    fun updateDataSet(l: List<PostDataModel>) {
        dataSet = l
        notifyDataSetChanged()
    }
}


