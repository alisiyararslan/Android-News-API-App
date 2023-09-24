package com.alisiyararslan.news.service

import com.alisiyararslan.news.model.NewsModel
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface NewsAPI {



    @GET("top-headlines?country=us&apiKey=${APIKey.key}")// must be updated!!!, Get key from https://newsapi.org/
    suspend fun getData() : Response<NewsModel>

    @GET("top-headlines?country=us&apiKey=${APIKey.key}")// must be updated!!!, Get key from https://newsapi.org/
    suspend fun getDataByCategory(@Query("category") category : String) : Response<NewsModel>

    @GET("top-headlines?country=us&apiKey=${APIKey.key}")// must be updated!!!, Get key from https://newsapi.org/
    suspend fun getDataBySearch(@Query("q") q : String) : Response<NewsModel>

}