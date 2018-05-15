package com.pratamawijaya.androidnewsarch.data.model.response

import com.pratamawijaya.androidnewsarch.data.model.ArticleModel

data class GetNewsResponse(
        val status: String,
        val totalResults: Int,
        val articles: List<ArticleModel>
)