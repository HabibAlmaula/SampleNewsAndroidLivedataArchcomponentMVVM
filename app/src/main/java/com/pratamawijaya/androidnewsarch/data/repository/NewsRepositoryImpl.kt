package com.pratamawijaya.androidnewsarch.data.repository

import android.content.Context
import com.pratamawijaya.androidnewsarch.data.NetworkBoundResource
import com.pratamawijaya.androidnewsarch.data.NetworkResponse
import com.pratamawijaya.androidnewsarch.data.NewsServices
import com.pratamawijaya.androidnewsarch.data.Resource
import com.pratamawijaya.androidnewsarch.domain.model.Article
import io.reactivex.Flowable
import io.reactivex.Observable
import retrofit2.Response

class NewsRepositoryImpl(private val service: NewsServices,
                         private val context: Context) : NewsRepository {

    override fun getTopNews(country: String, category: String): Flowable<Resource<List<Article>>> {

        return object : NetworkBoundResource<List<Article>, NetworkResponse>(context) {
            override fun saveCallResult(request: NetworkResponse) {
//                contentDao.insertContents(request.data)
            }

            override fun loadFromDb(): Flowable<List<Article>> {
//                return service.getTopHeadlines(country, category)
                return Flowable.fromArray()
            }

            override fun createCall(): Flowable<Response<NetworkResponse>> {
//                return contentService.getContents()
                return service.getTopHeadlines(country, category)
            }

        }.asFlowable()

    }

    //    override fun getTopNews(country: String, category: String): Observable<List<Article>> {
//        return service.getTopHeadlines(country, category)
//                .flatMapIterable { it.articles }
//                .map {
//                    Article(id = 0,
//                            title = it.title,
//                            author = it.author ?: "",
//                            image = it.urlToImage ?: "",
//                            publishedAt = it.publishedAt,
//                            sourceId = it.source.id ?: "",
//                            sourceName = it.source.name,
//                            url = it.url
//                    )
//                }.toList().toObservable()
//    }

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