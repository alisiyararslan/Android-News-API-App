package com.alisiyararslan.news.view

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.addTextChangedListener
import androidx.navigation.fragment.NavHostFragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.alisiyararslan.news.R
import com.alisiyararslan.news.adapter.NewsAdapter
import com.alisiyararslan.news.databinding.FragmentDiscoverBinding
import com.alisiyararslan.news.databinding.FragmentSearchBinding
import com.alisiyararslan.news.model.NewsItem
import com.alisiyararslan.news.service.NewsAPI
import kotlinx.android.synthetic.main.fragment_search.*
import kotlinx.coroutines.*
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


class SearchFragment : Fragment() {

    private var _binding: FragmentSearchBinding? = null

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

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentSearchBinding.inflate(inflater, container, false)
        val view = binding.root
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        seacrh_fragment_edit_text.addTextChangedListener(object : TextWatcher{
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                loadData(s.toString())
            }

            override fun afterTextChanged(s: Editable?) {

            }

        })
    }

    private fun loadData(searchKey : String) {

        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(NewsAPI::class.java)

        job = CoroutineScope(Dispatchers.IO + exceptionHandler).launch {

            var response = retrofit.getDataBySearch(searchKey)

            withContext(Dispatchers.Main){
                response?.let {
                    if (response.isSuccessful){
                        response.body()?.let {
                            newsItems = ArrayList(it.articles)

                            binding.searchFragementRecylerView.layoutManager = LinearLayoutManager(requireContext())
                            val adapter = NewsAdapter(ArrayList(newsItems!!.toList()),"search")
                            binding.searchFragementRecylerView.adapter = adapter

                        }
                    }
                }

            }
        }
    }
}