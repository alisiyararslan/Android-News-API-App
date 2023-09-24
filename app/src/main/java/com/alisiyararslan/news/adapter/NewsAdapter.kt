package com.alisiyararslan.news.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.alisiyararslan.news.R
import com.alisiyararslan.news.databinding.RecyclerRowNewsBinding
import com.alisiyararslan.news.model.NewsItem
import com.alisiyararslan.news.view.HomeFragmentDirections
import com.alisiyararslan.news.view.SearchFragmentDirections
import com.squareup.picasso.Picasso

class NewsAdapter(var newsList: ArrayList<NewsItem>, var pageName : String): RecyclerView.Adapter<NewsAdapter.NewsHolder>() {
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

        Picasso.get().load(newsList.get(position).urlToImage).into(holder.binding.recyclerRowImageView)
        holder.binding.recyclerRowTitleTextView.text = newsList.get(position).description

        newsList.get(position).source?.let{
            holder.binding.recyclerRowSourceTextView.text = newsList.get(position).source!!.name
        }

        holder.binding.recyclerRowLayout.setOnClickListener {
            if (pageName.equals("home")){
                val action=HomeFragmentDirections.actionHomeFragmentToNewsDetailFragment(newsList.get(position),"home_fragment")
                Navigation.findNavController(it).navigate(action)
            }else if(pageName.equals("search")){
                val action=SearchFragmentDirections.actionSearchFragmentToNewsDetailFragment(newsList.get(position),"search_fragment")
                Navigation.findNavController(it).navigate(action)
            }

        }

    }

}