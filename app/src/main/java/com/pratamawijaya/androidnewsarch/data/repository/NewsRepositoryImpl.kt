package com.pratamawijaya.androidnewsarch.data.repository

import com.pratamawijaya.androidnewsarch.domain.model.Article
import io.reactivex.Single
import javax.inject.Inject

class NewsRepositoryImpl @Inject constructor(val repo: NewsRepository) : NewsRepository {

    override fun getTopNews(): Single<List<Article>> {
        return repo.getTopNews()
                .map { news -> news.map(articleDomainModelMapper) }
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