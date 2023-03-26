package com.example.shortnews

import com.example.shortnews.models.News
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface NewsApi {
    @GET("v2/everything")
    fun getNews(@Query("q") q:String,@Query("apiKey") apiKey:String): Call<News>
}