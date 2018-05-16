package com.pratamawijaya.androidnewsarch.ui.news

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.Transformations
import android.arch.lifecycle.ViewModel
import com.pratamawijaya.androidnewsarch.data.Resource
import com.pratamawijaya.androidnewsarch.data.repository.NewsRepository
import com.pratamawijaya.androidnewsarch.domain.model.Article
import com.pratamawijaya.androidnewsarch.utils.AbsentLiveData
import javax.inject.Inject

private val TAG = NewsListViewModel::class.java.name

class NewsListViewModel @Inject constructor(private val repo: NewsRepository) : ViewModel() {

    private val _query: MutableLiveData<QueryNewsList> = MutableLiveData()

    val queryNews: LiveData<QueryNewsList>
        get() = _query

    val listArticle: LiveData<Resource<List<Article>>> = Transformations
            .switchMap(_query) { input ->
                input.ifExists { country, category ->
                    repo.getTopNews(country, category)
                }
            }

    fun setQuery(country: String, category: String) {
        val update = QueryNewsList(country, category)
        if (_query.value == update) {
            return
        }
        _query.value = update
    }

    data class QueryNewsList(val country: String,
                             val category: String) {
        fun <T> ifExists(f: (String, String) -> LiveData<T>): LiveData<T> {
            return if (country.isNullOrBlank() || category.isNullOrBlank()) {
                AbsentLiveData.create()
            } else {
                f(country!!, category!!)
            }
        }
    }
}
