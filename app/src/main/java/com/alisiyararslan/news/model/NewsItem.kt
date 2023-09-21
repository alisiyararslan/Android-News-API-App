package com.alisiyararslan.news.model


data class NewsItem(
    val id : String,
    val name : String,
    val description : String,
    val url : String,
    val category : String,
    val language : String,
    val country : String
)