package com.pratamawijaya.androidnewsarch.di

import com.pratamawijaya.androidnewsarch.AndroidNewsApp
import dagger.Component
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton

@Singleton
@Component(modules = arrayOf(
        AppModule::class,
        AndroidSupportInjectionModule::class
))
interface ApplicationComponent {
    fun inject(app: AndroidNewsApp)
}