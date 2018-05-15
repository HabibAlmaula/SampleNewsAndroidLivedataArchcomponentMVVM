package com.pratamawijaya.androidnewsarch.di

import android.content.Context
import com.pratamawijaya.androidnewsarch.AndroidNewsApp
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module(includes = arrayOf(
        NetworkModule::class,
        RepositoryModule::class,
        ViewModelModule::class
))
class AppModule {

    @Provides
    @Singleton
    fun provideContext(app: AndroidNewsApp): Context = app.applicationContext

}