package com.pratamawijaya.androidnewsarch.ui.news.rvitem

import android.util.Log
import com.pratamawijaya.androidnewsarch.R
import com.pratamawijaya.androidnewsarch.di.GlideApp
import com.pratamawijaya.androidnewsarch.domain.model.Article
import com.xwray.groupie.kotlinandroidextensions.Item
import com.xwray.groupie.kotlinandroidextensions.ViewHolder
import kotlinx.android.synthetic.main.item_news.view.*

interface NewsListener {
    fun onNewsClick(article: Article)
}

class NewsItem(private val article: Article,
               private val listener: NewsListener) : Item() {

    override fun bind(viewHolder: ViewHolder, position: Int) {
        viewHolder.itemView.tvTitle.text = article.title
        viewHolder.itemView.tvSource.text = article.sourceName

        Log.d("tag", "url gambar -> ${article.image}")


        if (article.image.isNotEmpty()) {
            GlideApp.with(viewHolder.itemView.context)
                    .load(article.image)
                    .into(viewHolder.itemView.imgNews)
        }

        viewHolder.itemView.setOnClickListener {
            listener.onNewsClick(article)
        }
    }

    override fun getLayout(): Int = R.layout.item_news
}