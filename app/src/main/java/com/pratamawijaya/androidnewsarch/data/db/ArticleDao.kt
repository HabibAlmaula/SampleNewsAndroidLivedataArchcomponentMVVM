package com.pratamawijaya.androidnewsarch.data.db

import android.arch.lifecycle.LiveData
import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.OnConflictStrategy
import android.arch.persistence.room.Query
import com.pratamawijaya.androidnewsarch.domain.model.Article

@Dao
interface ArticleDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(article: Article)

    @Query("SELECT * FROM Article")
    fun getArticles(): LiveData<List<Article>>
}