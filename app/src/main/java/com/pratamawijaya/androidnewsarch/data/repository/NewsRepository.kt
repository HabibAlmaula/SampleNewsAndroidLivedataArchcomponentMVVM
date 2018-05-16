package com.pratamawijaya.androidnewsarch.data.repository

import android.arch.lifecycle.LiveData
import com.pratamawijaya.androidnewsarch.data.Resource
import com.pratamawijaya.androidnewsarch.domain.model.Article

interface NewsRepository {
    fun getTopNews(country: String, category: String): LiveData<Resource<List<Article>>>
}