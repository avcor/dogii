package com.example.digii.adapter

import android.content.Intent
import android.os.Build
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.appcompat.widget.PopupMenu
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.digii.DETAILS_DATA
import com.example.digii.DeletePost
import com.example.digii.R
import com.example.digii.capitalizeFirstWord
import com.example.digii.data.local.PostDataDao
import com.example.digii.data.local.model.LocalPostDataEntity
import com.example.digii.databinding.CardPostBinding
import com.example.digii.militaryToDayTime
import com.example.digii.ui.activity.DetailActivity
import com.example.digii.viewmodel.MainActViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

class SavedPostAdapter @Inject constructor(
): RecyclerView.Adapter<SavedPostAdapter.ViewHolder>()  {
    val TAG = "abcd"
    private var dataSet: List<LocalPostDataEntity> = listOf()
    private var fn: ((LocalPostDataEntity) -> Unit)? = null
    inner class ViewHolder(val binding: CardPostBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(CardPostBinding.inflate(LayoutInflater.from(parent.context),parent,false))
    }

    override fun getItemCount() = dataSet.size

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        with(holder.binding) {
            with(dataSet[position].convertToPostDataModel()) {
                titleTV.text = text?.capitalizeFirstWord()
                likesTV.text = "$likes likes"
                postedOnTV.text = "Posted on : ${publishDate.militaryToDayTime()}"
                authorTV.text = "Author : ${owner?.getAuthorFullName()}"
                Glide.with(holder.itemView).load(image).into(imageView)
            }
            parentCard.setOnClickListener {
                val i = Intent(holder.itemView.context, DetailActivity::class.java).apply {
                    putExtra(DETAILS_DATA, dataSet[position].convertToPostDataModel())
                }
                holder.itemView.context.startActivity(i)
            }
            menu.setOnClickListener {
                PopupMenu(holder.itemView.context, it).apply {
                    inflate(R.menu.card_post_unsave)
                    setOnMenuItemClickListener {
                        when(it.itemId){
                            R.id.unsaveItem -> {
                                fn?.let { invoke -> invoke(dataSet[position]) }
                                true
                            }
                            else -> { false}
                        }
                    }
                }.show()
            }
        }
    }

    fun updateDataSet(l:List<LocalPostDataEntity>) {
        dataSet = l
        notifyDataSetChanged()
    }
    fun setSaveFun(param: (LocalPostDataEntity) -> Unit) {
        fn = param
    }

}