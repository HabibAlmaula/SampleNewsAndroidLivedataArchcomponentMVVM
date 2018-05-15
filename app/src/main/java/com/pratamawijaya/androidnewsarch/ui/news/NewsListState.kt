package com.pratamawijaya.androidnewsarch.ui.news

import com.pratamawijaya.androidnewsarch.domain.model.Article

sealed class NewsListState {
    abstract val data: List<Article>
}

data class DefaultState(override val data: List<Article>) : NewsListState()
data class LoadingState(override val data: List<Article>) : NewsListState()
data class ErrorState(val errorMessage: String, override val data: List<Article>) : NewsListState()