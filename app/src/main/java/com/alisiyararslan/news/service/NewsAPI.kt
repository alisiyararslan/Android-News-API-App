package com.alisiyararslan.news.service

import com.alisiyararslan.news.model.NewsModel
import retrofit2.Response
import retrofit2.http.GET

interface NewsAPI {

    @GET("sources?apiKey=${APIKey.key}")// must be updated!!!, Get key from https://newsapi.org/
    suspend fun getData() : Response<NewsModel>

}