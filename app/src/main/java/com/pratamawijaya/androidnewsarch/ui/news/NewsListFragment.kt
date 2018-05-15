package com.pratamawijaya.androidnewsarch.ui.news

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProvider
import android.arch.lifecycle.ViewModelProviders
import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.pratamawijaya.androidnewsarch.R
import dagger.android.support.AndroidSupportInjection
import javax.inject.Inject

private val TAG = NewsListFragment::class.java.name

class NewsListFragment : Fragment() {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private lateinit var viewModel: NewsListViewModel

    companion object {
        fun newInstance() = NewsListFragment()
    }

    // state observer
    private val stateObserver = Observer<NewsListState> { state ->
        state?.let {
            when (state) {
                is DefaultState -> {
                    it.data.map {
                        Log.d(TAG, "data -> ${it.title} ${it.sourceName}")
                    }
                }
                is LoadingState -> {
                    Log.d(TAG, "loading state")
                }

                is ErrorState -> {
                    Log.e(TAG, "error state")
                }
            }
        }
    }

    override fun onAttach(context: Context?) {
        super.onAttach(context)
        AndroidSupportInjection.inject(this)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.news_list_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProviders.of(this, viewModelFactory).get(NewsListViewModel::class.java)

        observerViewModel()
        viewModel.updateNewsList()
    }

    private fun observerViewModel() {
        viewModel.stateLiveData.observe(this, stateObserver)
    }

    override fun onDestroy() {
        super.onDestroy()
        viewModel.stateLiveData.removeObserver(stateObserver)
    }

}
