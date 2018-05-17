package com.pratamawijaya.androidnewsarch.data.repository

import android.content.Context
import com.github.ajalt.timberkt.d
import com.pratamawijaya.androidnewsarch.data.NewsServices
import com.pratamawijaya.androidnewsarch.data.db.ArticleDao
import com.pratamawijaya.androidnewsarch.domain.model.Article
import com.pratamawijaya.androidnewsarch.utils.isNetworkStatusAvailable
import io.reactivex.Single

class NewsRepositoryImpl(private val service: NewsServices,
                         private val articleDao: ArticleDao,
                         private val context: Context) : NewsRepository {

    override fun getTopNews(country: String, category: String): Single<List<Article>> {
        if (context.isNetworkStatusAvailable()) {
            return service.getTopHeadlines(country = country, category = category)
                    .flattenAsObservable { it.articles }
                    .map {
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
                        d { "insert article ${article.title}" }
                        articleDao.insert(article)
                        // returt article
                        article
                    }.toList()
        } else {
            return articleDao.getArticles()
        }
    }

    override fun getLocalNews(): Single<List<Article>> {
        return articleDao.getArticles()
    }

    private val articleDomainModelMapper: (Article) -> Article = { article ->
        Article(
                id = article.id,
                title = article.title,
                author = article.author,
                image = article.image,
                publishedAt = article.publishedAt,
                sourceId = article.sourceId,
                sourceName = article.sourceName,
                url = article.url
        )
    }
}