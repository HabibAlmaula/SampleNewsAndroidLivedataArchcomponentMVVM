package com.pratamawijaya.androidnewsarch.di

import com.pratamawijaya.androidnewsarch.data.NewsServices
import com.pratamawijaya.androidnewsarch.data.db.ArticleDao
import com.pratamawijaya.androidnewsarch.data.repository.NewsRepository
import com.pratamawijaya.androidnewsarch.data.repository.NewsRepositoryImpl
import dagger.Module
import dagger.Provides

@Module
class RepositoryModule {

    @Provides
    fun provideNewsRepo(newsServices: NewsServices,
                        appExecutors: AppExecutors,
                        articleDao: ArticleDao): NewsRepository = NewsRepositoryImpl(service = newsServices,
            appExecutors = appExecutors, articleDao = articleDao)
}