package com.alisiyararslan.news.service

import com.alisiyararslan.news.model.NewsModel
import retrofit2.Response
import retrofit2.http.GET

interface NewsAPI {



    @GET("top-headlines?country=us&apiKey=${APIKey.key}")// must be updated!!!, Get key from https://newsapi.org/
    suspend fun getData() : Response<NewsModel>

    @GET("top-headlines?country=us&category=business&apiKey=${APIKey.key}")// must be updated!!!, Get key from https://newsapi.org/
    suspend fun getBusinessData() : Response<NewsModel>

    @GET("top-headlines?country=us&category=entertainment&apiKey=${APIKey.key}")// must be updated!!!, Get key from https://newsapi.org/
    suspend fun getEntertainmentData() : Response<NewsModel>

    @GET("top-headlines?country=us&category=general&apiKey=${APIKey.key}")// must be updated!!!, Get key from https://newsapi.org/
    suspend fun getGeneralData() : Response<NewsModel>

    @GET("top-headlines?country=us&category=health&apiKey=${APIKey.key}")// must be updated!!!, Get key from https://newsapi.org/
    suspend fun getHealthData() : Response<NewsModel>

    @GET("top-headlines?country=us&category=science&apiKey=${APIKey.key}")// must be updated!!!, Get key from https://newsapi.org/
    suspend fun getScienceData() : Response<NewsModel>

    @GET("top-headlines?country=us&category=technology&apiKey=${APIKey.key}")// must be updated!!!, Get key from https://newsapi.org/
    suspend fun getTechnologyData() : Response<NewsModel>

    @GET("top-headlines?country=us&category=sports&apiKey=${APIKey.key}")// must be updated!!!, Get key from https://newsapi.org/
    suspend fun getSportsData() : Response<NewsModel>

}