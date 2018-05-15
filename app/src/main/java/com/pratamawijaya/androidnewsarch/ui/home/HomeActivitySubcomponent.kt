package com.pratamawijaya.androidnewsarch.ui.home

import com.pratamawijaya.androidnewsarch.ui.news.NewsListFragmentModule
import dagger.Subcomponent
import dagger.android.AndroidInjector

@Subcomponent(modules = arrayOf(NewsListFragmentModule::class))
interface HomeActivitySubcomponent : AndroidInjector<HomeActivity> {
    @Subcomponent.Builder
    abstract class Builder : AndroidInjector.Builder<HomeActivity>()
}