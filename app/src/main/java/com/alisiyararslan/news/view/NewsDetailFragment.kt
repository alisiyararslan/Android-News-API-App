package com.alisiyararslan.news.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.addCallback
import androidx.navigation.Navigation
import com.alisiyararslan.news.R
import com.alisiyararslan.news.databinding.FragmentNewsDetailBinding
import com.alisiyararslan.news.model.NewsItem
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.fragment_news_detail.*


class NewsDetailFragment : Fragment() {

    private var _binding: FragmentNewsDetailBinding? = null

    private val binding get() = _binding!!



    private lateinit var news :NewsItem

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        // For the back button to work properly
        val callback = requireActivity().onBackPressedDispatcher.addCallback(this){
            val action=NewsDetailFragmentDirections.actionNewsDetailFragmentToHomeFragment()
            Navigation.findNavController(requireView()).navigate(action)
        }

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentNewsDetailBinding.inflate(inflater, container, false)
        val view = binding.root
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        arguments?.let {
            news = NewsDetailFragmentArgs.fromBundle(it).news

            Picasso.get().load(news.urlToImage).into(news_detail_image_view)
            news_detail_title_text_view.text= news.title
            news.source?.let{
                news_detail_source_text_view.text =news.source!!.name
            }

            news_detail_date_text_view.text=news.publishedAt
            news_detail_description_text_view.text=news.description
            news_detail_content_text_view.text=news.content



        }
    }


}