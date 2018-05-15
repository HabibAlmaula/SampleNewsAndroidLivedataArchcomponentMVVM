package com.pratamawijaya.androidnewsarch.di

import android.content.Context
import com.pratamawijaya.androidnewsarch.AndroidNewsApp
import com.pratamawijaya.androidnewsarch.ui.home.HomeActivityModule
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class AppModule {

    @Provides
    @Singleton
    fun provideContext(app: AndroidNewsApp): Context = app.applicationContext

}