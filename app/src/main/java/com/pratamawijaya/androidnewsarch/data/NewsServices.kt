package com.pratamawijaya.androidnewsarch.data

import android.arch.lifecycle.LiveData
import com.pratamawijaya.androidnewsarch.data.model.response.GetNewsResponse
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * newsapi key 4b4df2ea3a154950852b6fda536cfb7f
 */
interface NewsServices {

    @GET("top-headlines")
    fun getTopHeadlines(@Query("country") country: String,
                        @Query("category") category: String): LiveData<ApiResponse<GetNewsResponse>>

    @GET("everything")
    fun getEverything(@Query("q") query: String,
                      @Query("sortBy") sortBy: String): Single<GetNewsResponse>
}