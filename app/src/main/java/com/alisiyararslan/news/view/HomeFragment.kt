package com.alisiyararslan.news.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.alisiyararslan.news.R
import com.alisiyararslan.news.adapter.NewsAdapter
import com.alisiyararslan.news.databinding.FragmentHomeBinding
import com.alisiyararslan.news.model.NewsItem
import com.alisiyararslan.news.service.NewsAPI
import kotlinx.android.synthetic.main.fragment_home.*
import kotlinx.coroutines.*
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null

    private val binding get() = _binding!!

    private val BASE_URL = "https://newsapi.org/v2/"
    private var newsItems: ArrayList<NewsItem>? = null
    private var job : Job? = null

    val exceptionHandler = CoroutineExceptionHandler { coroutineContext, throwable ->
        println("Error: ${throwable.localizedMessage}")
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)








    }

    private fun loadData() {

        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(NewsAPI::class.java)


//+ exceptionHandler
        job = CoroutineScope(Dispatchers.IO ).launch {
            val response = retrofit.getData()

            withContext(Dispatchers.Main){
                if (response.isSuccessful){
                    response.body()?.let {
                        newsItems = ArrayList(it.articles)

                        binding.homeFragementRecylerView.layoutManager = LinearLayoutManager(requireContext())
                        val adapter = NewsAdapter(ArrayList(newsItems))
                        binding.homeFragementRecylerView.adapter = adapter

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

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        val view = binding.root
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        if (requireArguments().isEmpty){
            loadData()
        }else{
            arguments?.let {
                var newsList = HomeFragmentArgs.fromBundle(it).newsList
                homeFragmentHeading.text = HomeFragmentArgs.fromBundle(it).newsCategory!!.toUpperCase()

                binding.homeFragementRecylerView.layoutManager = LinearLayoutManager(requireContext())
                val adapter = NewsAdapter(ArrayList(newsList!!.toList()))
                binding.homeFragementRecylerView.adapter = adapter
            }
        }





    }


}