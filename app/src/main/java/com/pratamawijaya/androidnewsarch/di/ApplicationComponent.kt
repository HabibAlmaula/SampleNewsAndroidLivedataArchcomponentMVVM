package com.pratamawijaya.androidnewsarch.di

import android.app.Application
import com.pratamawijaya.androidnewsarch.AndroidNewsApp
import com.pratamawijaya.androidnewsarch.ui.home.HomeActivityModule
import dagger.BindsInstance
import dagger.Component
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton

@Singleton
@Component(modules = arrayOf(
        AndroidSupportInjectionModule::class,
        AppModule::class,
        ViewModelFactoryModule::class,
        NetworkModule::class,
        RepositoryModule::class,
        ViewModelModule::class,
        HomeActivityModule::class
))
interface ApplicationComponent {
    @Component.Builder
    interface Builder {
        @BindsInstance
        fun application(application: Application): Builder

        fun build(): ApplicationComponent
    }

    fun inject(app: AndroidNewsApp)
}