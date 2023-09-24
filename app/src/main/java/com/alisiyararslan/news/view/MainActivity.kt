package com.alisiyararslan.news.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.navigation.Navigation
import com.alisiyararslan.news.R
import com.alisiyararslan.news.databinding.ActivityMainBinding
import com.alisiyararslan.news.model.NewsItem
import com.alisiyararslan.news.model.NewsModel
import com.alisiyararslan.news.service.APIKey
import com.alisiyararslan.news.service.NewsAPI
import kotlinx.android.synthetic.main.activity_main.*
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


    fun homeButtonclicked(view : View){

        homeButton.setImageResource(R.drawable.home_filled)
        discoverButton.setImageResource(R.drawable.compass_empty)
        searchButton.setImageResource(R.drawable.search_empty)

        var navController = Navigation.findNavController(this, R.id.fragmentContainerView)

        navController.navigate(R.id.homeFragment)
    }

    fun discoverButtonClicked(view : View){

        homeButton.setImageResource(R.drawable.home_empty)
        discoverButton.setImageResource(R.drawable.compass_filled)
        searchButton.setImageResource(R.drawable.search_empty)

        var navController = Navigation.findNavController(this, R.id.fragmentContainerView)

        navController.navigate(R.id.discoverFragment)
    }

    fun searchButtonClicked(view : View){

        homeButton.setImageResource(R.drawable.home_empty)
        discoverButton.setImageResource(R.drawable.compass_empty)
        searchButton.setImageResource(R.drawable.search_filled)

        var navController = Navigation.findNavController(this, R.id.fragmentContainerView)

        navController.navigate(R.id.searchFragment)
    }


}