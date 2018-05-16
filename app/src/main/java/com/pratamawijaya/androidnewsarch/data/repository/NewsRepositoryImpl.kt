package com.pratamawijaya.androidnewsarch.data.repository

import android.arch.lifecycle.LiveData
import com.github.ajalt.timberkt.d
import com.pratamawijaya.androidnewsarch.data.*
import com.pratamawijaya.androidnewsarch.data.db.ArticleDao
import com.pratamawijaya.androidnewsarch.data.model.response.GetNewsResponse
import com.pratamawijaya.androidnewsarch.di.AppExecutors
import com.pratamawijaya.androidnewsarch.domain.model.Article

class NewsRepositoryImpl(private val service: NewsServices,
                         private val appExecutors: AppExecutors,
                         private val articleDao: ArticleDao) : NewsRepository {

    override fun getTopNews(country: String, category: String): LiveData<Resource<List<Article>>> {

        return object : NetworkBoundResource<List<Article>, GetNewsResponse>(appExecutors) {

            override fun saveCallResult(item: GetNewsResponse) {
                item.articles.map {
                    val article = Article(
                            id = 0,
                            title = it.title,
                            author = it.author ?: "",
                            image = it.urlToImage ?: "",
                            publishedAt = it.publishedAt,
                            sourceId = it.source.id ?: "",
                            sourceName = it.source.name,
                            url = it.url
                    )
                    d { "instert ${article.title}" }
                    articleDao.insert(article)
                }
            }

            override fun shouldFetch(data: List<Article>?): Boolean {
                return true
            }

            override fun loadFromDb(): LiveData<List<Article>> {
                d { "load from db" }
                return articleDao.getArticles()
            }

            override fun createCall(): LiveData<ApiResponse<GetNewsResponse>> {
                return service.getTopHeadlines(country, category)
            }

            override fun processResponse(response: ApiSuccessResponse<GetNewsResponse>): GetNewsResponse {
                val body = response.body
                return body
            }

        }.asLiveData()
    }
}