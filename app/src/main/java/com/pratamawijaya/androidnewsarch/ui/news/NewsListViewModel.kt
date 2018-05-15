package com.pratamawijaya.androidnewsarch.ui.news

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import android.util.Log
import com.pratamawijaya.androidnewsarch.data.repository.NewsRepository
import com.pratamawijaya.androidnewsarch.domain.model.Article
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

private val TAG = NewsListViewModel::class.java.name

class NewsListViewModel @Inject constructor(private val repo: NewsRepository) : ViewModel() {

    val stateLiveData = MutableLiveData<NewsListState>()

    init {
        stateLiveData.value = DefaultState(emptyList())
    }

    fun updateNewsList() {
        getNewsList()
    }

    private fun getNewsList() {
        repo.getTopNews(country = "us", category = "technology")
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(this::onNewsReceived, this::onError)
    }

    private fun onError(error: Throwable) {
        Log.e(TAG, "error ${error.localizedMessage}")
        stateLiveData.value = ErrorState(error.localizedMessage, obtainCurrentData())
    }

    private fun onNewsReceived(news: List<Article>) {
        val currentNews = obtainCurrentData().toMutableList()
        currentNews.addAll(news)
        stateLiveData.value = DefaultState(obtainCurrentData())
    }


    private fun obtainCurrentData() = stateLiveData.value?.data ?: emptyList()
}
