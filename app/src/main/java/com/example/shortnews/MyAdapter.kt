package com.example.shortnews

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.shortnews.databinding.ListItemBinding
import com.example.shortnews.models.Article

class MyAdapter(val context: Context, val newsList: List<Article>):

    RecyclerView.Adapter<MyAdapter.MyViewHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val view= LayoutInflater.from(parent.context)
        val binding:ListItemBinding= DataBindingUtil.inflate(view,R.layout.list_item,parent,false)
        return  MyViewHolder(binding)
    }
    @SuppressLint("SuspiciousIndentation")
    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val currentItem=newsList[position]
        with(holder.binding){
          heading.text=currentItem.title
            Glide.with(context)
                .load(currentItem.urlToImage)
                .centerCrop()
                .placeholder(R.drawable.ic_launcher_background)
                .into(mainimage)
            contentmain.text=currentItem.content
        }
    }
    override fun getItemCount(): Int {
        return newsList.size
    }
    class MyViewHolder(val binding: ListItemBinding):RecyclerView.ViewHolder(binding.root){

    }


}