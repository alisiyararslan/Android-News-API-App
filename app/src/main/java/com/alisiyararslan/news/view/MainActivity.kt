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

    private val BASE_URL = "https://newsapi.org/v2/top-headlines/"
    private var newsItems: ArrayList<NewsItem>? = null
    private var job : Job? = null
    private lateinit var binding: ActivityMainBinding

    val exceptionHandler = CoroutineExceptionHandler { coroutineContext, throwable ->
        println("Error: ${throwable.localizedMessage}")
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        loadData()
    }

    private fun loadData() {

        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(NewsAPI::class.java)


        APIKey.key

        job = CoroutineScope(Dispatchers.IO+ exceptionHandler ).launch {
            val response = retrofit.getData()

            withContext(Dispatchers.Main){
                if (response.isSuccessful){
                    response.body()?.let {
                        newsItems = ArrayList(it.sources)

                        newsItems?.let {
                            newsItems!!.forEach {
                                println(it)
                            }
                        }
                    }
                }
            }
        }


    }
}