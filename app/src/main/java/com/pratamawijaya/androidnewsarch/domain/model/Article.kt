package com.pratamawijaya.androidnewsarch.domain.model

data class Article(
        val id: Int,
        val title: String,
        val author: String,
        val sourceId: String,
        val sourceName: String,
        val url: String,
        val image: String,
        val publishedAt: String
)