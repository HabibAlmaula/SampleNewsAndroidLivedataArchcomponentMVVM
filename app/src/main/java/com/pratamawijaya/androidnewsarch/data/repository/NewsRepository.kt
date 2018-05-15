package com.pratamawijaya.androidnewsarch.data.repository

import com.pratamawijaya.androidnewsarch.domain.model.Article
import io.reactivex.Single

interface NewsRepository {
    fun getTopNews(): Single<List<Article>>
}