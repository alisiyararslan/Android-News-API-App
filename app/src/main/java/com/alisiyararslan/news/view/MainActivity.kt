package com.alisiyararslan.news.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.alisiyararslan.news.databinding.ActivityMainBinding
import com.alisiyararslan.news.model.NewsItem
import com.alisiyararslan.news.model.NewsModel
import com.alisiyararslan.news.service.APIKey
import com.alisiyararslan.news.service.NewsAPI
import kotlinx.coroutines.*
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity() {


    private lateinit var binding: ActivityMainBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)


    }


}