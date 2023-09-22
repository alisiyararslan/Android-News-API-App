package com.alisiyararslan.news.model

data class NewsModel(
    val status : String,
    val totalResults : Int,
    val articles : List<NewsItem>,
)