package com.pratamawijaya.androidnewsarch.ui.news

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProvider
import android.arch.lifecycle.ViewModelProviders
import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.widget.SwipeRefreshLayout
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import butterknife.BindView
import butterknife.ButterKnife
import com.github.ajalt.timberkt.e
import com.pratamawijaya.androidnewsarch.R
import com.pratamawijaya.androidnewsarch.data.Resource
import com.pratamawijaya.androidnewsarch.domain.model.Article
import com.pratamawijaya.androidnewsarch.ui.news.rvitem.NewsItem
import com.pratamawijaya.androidnewsarch.ui.news.rvitem.NewsListener
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.Section
import com.xwray.groupie.ViewHolder
import dagger.android.support.AndroidSupportInjection
import javax.inject.Inject

private val TAG = NewsListFragment::class.java.name

class NewsListFragment : Fragment(), NewsListener, SwipeRefreshLayout.OnRefreshListener {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    @BindView(R.id.rvNews)
    lateinit var rvNews: RecyclerView
    @BindView(R.id.loading)
    lateinit var swipeRefresh: SwipeRefreshLayout

    private lateinit var viewModel: NewsListViewModel
    private val groupAdapter = GroupAdapter<ViewHolder>()
    private var isLoading = false

    companion object {
        fun newInstance() = NewsListFragment()
    }

    // state observer
    private val stateObserver = Observer<Resource<List<Article>>> { state ->
        when (state) {
            is Resource.Loading -> {
                isLoading = true
                swipeRefresh.isRefreshing = true
            }

            is Resource.Success -> {
                isLoading = false
                swipeRefresh.isRefreshing = false

                state.data.let {
                    it?.map {
                        Log.d(TAG, "data -> ${it.title} ${it.sourceName}")
                    }

                    it?.map {
                        Section().apply {
                            add(NewsItem(it, this@NewsListFragment))
                            groupAdapter.add(this)
                        }
                    }
                }
            }

            is Resource.Error -> {
                isLoading = false
                swipeRefresh.isRefreshing = false
                e { "error ${state.errorMsg}" }
            }
        }

    }

    override fun onAttach(context: Context?) {
        AndroidSupportInjection.inject(this)
        super.onAttach(context)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.news_list_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        ButterKnife.bind(this, view)

        viewModel = ViewModelProviders.of(this, viewModelFactory).get(NewsListViewModel::class.java)
        viewModel.setQuery("us", "technology")

        observerViewModel()

        setupRv()
    }

    private fun setupRv() {
        rvNews.apply {
            layoutManager = LinearLayoutManager(activity)
            adapter = groupAdapter
        }
    }

    private fun observerViewModel() {
        viewModel.listArticle.observe(this, stateObserver)
    }

    override fun onDestroy() {
        super.onDestroy()
        viewModel.listArticle.removeObserver(stateObserver)
    }

    override fun onNewsClick(article: Article) {
        Toast.makeText(activity, article.title, Toast.LENGTH_SHORT).show()
    }

    override fun onRefresh() {
        groupAdapter.clear()
//        viewModel.refreshNewsList()
    }

}
