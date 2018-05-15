package com.pratamawijaya.androidnewsarch.di

import com.pratamawijaya.androidnewsarch.data.NewsServices
import com.pratamawijaya.androidnewsarch.data.repository.NewsRepository
import com.pratamawijaya.androidnewsarch.data.repository.NewsRepositoryImpl
import dagger.Module
import dagger.Provides

@Module
class RepositoryModule {

    @Provides
    fun provideNewsRepo(newsServices: NewsServices): NewsRepository = NewsRepositoryImpl(newsServices)
}