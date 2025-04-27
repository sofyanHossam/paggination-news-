package com.example.jetpackapi.data.remote

import com.example.jetpackapi.data.remote.dto.NewsResponse
import com.example.jetpackapi.util.Constants.APIKEY
import retrofit2.http.GET
import retrofit2.http.Query

interface NewsApi {
    @GET("everything")
    suspend fun getNews(
        @Query("sources") sources: String,
        @Query("page") page: Int,
        @Query("apiKey") apiKey: String = APIKEY
    ): NewsResponse

    @GET("everything")
    suspend fun  searchNews(
        @Query("q") searchQuery: String,
        @Query("sources") sources: String,
        @Query("page") page: Int,
        @Query("apiKey") apiKey: String = APIKEY

    ):NewsResponse
}