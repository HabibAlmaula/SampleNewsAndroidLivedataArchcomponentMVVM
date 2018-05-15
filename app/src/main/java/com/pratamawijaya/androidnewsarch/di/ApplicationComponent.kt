package com.pratamawijaya.androidnewsarch.di

import com.pratamawijaya.androidnewsarch.AndroidNewsApp
import com.pratamawijaya.androidnewsarch.ui.home.HomeActivityModule
import dagger.Component
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton

@Singleton
@Component(modules = arrayOf(
        AppModule::class,
        AndroidSupportInjectionModule::class,
        ViewModelFactoryModule::class,
        NetworkModule::class,
        RepositoryModule::class,
        ViewModelModule::class,
        HomeActivityModule::class
))
interface ApplicationComponent {
    fun inject(app: AndroidNewsApp)
}