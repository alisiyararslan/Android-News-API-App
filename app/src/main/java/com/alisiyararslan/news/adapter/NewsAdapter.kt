package com.alisiyararslan.news.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.alisiyararslan.news.databinding.RecyclerRowNewsBinding
import com.alisiyararslan.news.model.NewsItem
import com.squareup.picasso.Picasso

class NewsAdapter(var newsList: ArrayList<NewsItem>): RecyclerView.Adapter<NewsAdapter.NewsHolder>() {
    class NewsHolder(val binding: RecyclerRowNewsBinding): RecyclerView.ViewHolder(binding.root) {

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsHolder {
        val binding = RecyclerRowNewsBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return NewsHolder(binding)
    }

    override fun getItemCount(): Int {
        return newsList.size
    }

    override fun onBindViewHolder(holder: NewsHolder, position: Int) {
        //holder.binding.recyclerRowImageView.setImageURI(newsList.get)
        Picasso.get().load(newsList.get(position).urlToImage).into(holder.binding.recyclerRowImageView);
        holder.binding.recyclerRowDescriptionTextView.text = newsList.get(position).description
        holder.binding.recyclerRowSourceTextView.text = newsList.get(position).source.name
    }
}