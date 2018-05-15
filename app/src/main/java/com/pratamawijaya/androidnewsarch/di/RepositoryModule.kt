package com.pratamawijaya.androidnewsarch.di

import com.pratamawijaya.androidnewsarch.data.repository.NewsRepository
import com.pratamawijaya.androidnewsarch.data.repository.NewsRepositoryImpl
import dagger.Module
import dagger.Provides

@Module
class RepositoryModule {

    @Provides
    fun provideNewsRepo(newsRepositoryImpl: NewsRepositoryImpl): NewsRepository = newsRepositoryImpl
}