package com.example.rammandirquiz

import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

import retrofit2.http.GET
import retrofit2.http.Query

const val BASE_URL="https://newsapi.org/"
const val API_KEY ="710f79b2c7804eeaa70855444e584f61"

interface NewsApi {
    @GET("v2/everything?apiKey=$API_KEY")
    fun getHeadlines(
        @Query("q")
        q: String,
        @Query("page")
        page: Int,
        @Query("sortBy")
        sortBy: String



    ): Call<NewsResponse>




}

object NewsService{
    val newsInstance: NewsApi
    init {
        val retrofit=Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        newsInstance= retrofit.create(NewsApi::class.java)
    }
}