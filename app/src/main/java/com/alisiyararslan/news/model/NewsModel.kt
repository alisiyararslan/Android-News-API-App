package com.alisiyararslan.news.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize


data class NewsModel(
    val status : String?,
    val totalResults : Int?,
    val articles : List<NewsItem>?,
)