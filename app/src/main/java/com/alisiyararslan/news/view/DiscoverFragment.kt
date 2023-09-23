package com.alisiyararslan.news.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.NavHostFragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.alisiyararslan.news.R
import com.alisiyararslan.news.adapter.NewsAdapter
import com.alisiyararslan.news.databinding.FragmentDiscoverBinding
import com.alisiyararslan.news.databinding.FragmentHomeBinding
import com.alisiyararslan.news.model.NewsItem
import com.alisiyararslan.news.model.NewsModel
import com.alisiyararslan.news.service.NewsAPI
import kotlinx.android.synthetic.main.fragment_discover.*
import kotlinx.android.synthetic.main.fragment_home.*
import kotlinx.android.synthetic.main.fragment_home.view.*
import kotlinx.coroutines.*
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


class DiscoverFragment : Fragment() {

    private var _binding: FragmentDiscoverBinding? = null

    private val binding get() = _binding!!

    private val BASE_URL = "https://newsapi.org/v2/"
    private var newsItems: ArrayList<NewsItem>? = null
    private var job : Job? = null
//    private  var response : Response<NewsModel>

    val exceptionHandler = CoroutineExceptionHandler { coroutineContext, throwable ->
        println("Error: ${throwable.localizedMessage}")
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentDiscoverBinding.inflate(inflater, container, false)
        val view = binding.root
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        discoverFragmentBusinessImage.setOnClickListener {
            loadData("business")
        }

        discoverFragmentEntertainmentImage.setOnClickListener {
            loadData("entertainment")
        }

        discoverFragmentGeneralImage.setOnClickListener {
            loadData("general")
        }

        discoverFragmentHealthImage.setOnClickListener {
            loadData("health")
        }

        discoverFragmentScienceImage.setOnClickListener {
            loadData("science")
        }

        discoverFragmentTechnologyImage.setOnClickListener {
            loadData("technology")
        }

        discoverFragmentSportsImage.setOnClickListener {
            loadData("sports")
        }


    }

    private fun loadData(category : String) {

        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(NewsAPI::class.java)


//+ exceptionHandler
        job = CoroutineScope(Dispatchers.IO ).launch {
            var response : Response<NewsModel>? = null
            if (category.equals("business")){
                response = retrofit.getBusinessData()
            }else if(category.equals("sports")){
                response = retrofit.getSportsData()
            }else if(category.equals("entertainment")){
                response = retrofit.getEntertainmentData()
            }else if(category.equals("general")){
                response = retrofit.getGeneralData()
            }else if(category.equals("health")){
                response = retrofit.getHealthData()
            }else if(category.equals("science")){
                response = retrofit.getScienceData()
            }else if(category.equals("technology")){
                response = retrofit.getTechnologyData()
            }


            withContext(Dispatchers.Main){
                response?.let {
                    if (response.isSuccessful){
                        response.body()?.let {
                            newsItems = ArrayList(it.articles)
                            var navController = NavHostFragment.findNavController(this@DiscoverFragment)
                            val action=DiscoverFragmentDirections.actionDiscoverFragmentToHomeFragment(
                                newsList=newsItems!!.toTypedArray(), newsCategory = category)//newsItems!!.toTypedArray()
                            navController.navigate(action)

//                            newsItems?.let {
//                                newsItems!!.forEach {
//                                    println(it)
//                                }
//                            }
                        }
                    }
                }

            }
        }


    }


}