package com.pratamawijaya.androidnewsarch.di

import android.arch.lifecycle.ViewModel
import com.pratamawijaya.androidnewsarch.ui.news.NewsListViewModel
import dagger.Binds
import dagger.MapKey
import dagger.Module
import dagger.multibindings.IntoMap
import kotlin.reflect.KClass

@Target(AnnotationTarget.FUNCTION)
@Retention(AnnotationRetention.RUNTIME)
@MapKey
internal annotation class ViewModelKey(val value: KClass<out ViewModel>)

@Module
abstract class ViewModelModule {

    @Binds
    @IntoMap
    @ViewModelKey(NewsListViewModel::class)
    abstract fun bindCryptoListViewModel(viewModel: NewsListViewModel) : ViewModel
}