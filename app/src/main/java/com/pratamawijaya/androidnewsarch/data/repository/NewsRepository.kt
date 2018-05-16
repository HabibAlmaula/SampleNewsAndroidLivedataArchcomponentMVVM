package com.pratamawijaya.androidnewsarch.data.repository

import com.pratamawijaya.androidnewsarch.data.Resource
import com.pratamawijaya.androidnewsarch.domain.model.Article
import io.reactivex.Flowable
import io.reactivex.Observable
import io.reactivex.Single

interface NewsRepository {
    fun getTopNews(country: String, category: String): Flowable<Resource<List<Article>>>
}