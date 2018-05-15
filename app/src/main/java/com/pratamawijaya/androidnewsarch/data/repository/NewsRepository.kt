package com.pratamawijaya.androidnewsarch.data.repository

import com.pratamawijaya.androidnewsarch.domain.model.Article
import io.reactivex.Single

interface NewsRepository {
    fun getTopNews(country: String, category: String): Single<List<Article>>
}