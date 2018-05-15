package com.pratamawijaya.androidnewsarch.data.repository

import com.pratamawijaya.androidnewsarch.data.NewsServices
import com.pratamawijaya.androidnewsarch.domain.model.Article
import io.reactivex.Single

class NewsRepositoryImpl(private val service: NewsServices) : NewsRepository {

    override fun getTopNews(country: String, category: String): Single<List<Article>> {

        return service.getTopHeadlines(country = country, category = category)
                .flattenAsObservable { it.articles }
                .map {
                    Article(
                            id = 0,
                            title = it.title,
                            author = it.author ?: "",
                            image = it.urlToImage ?: "",
                            publishedAt = it.publishedAt,
                            sourceId = it.source.id ?: "",
                            sourceName = it.source.name,
                            url = it.url
                    )
                }.toList()
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