package com.alisiyararslan.news.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class ArticleSource(
    val id : String,
    val name : String,
): Parcelable