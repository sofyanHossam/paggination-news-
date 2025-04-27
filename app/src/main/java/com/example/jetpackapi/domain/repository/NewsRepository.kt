package com.example.jetpackapi.domain.repository

import androidx.paging.PagingData
import com.example.jetpackapi.domain.model.Article
import kotlinx.coroutines.flow.Flow


interface NewsRepository {

    fun getNews(source: List<String>): Flow<PagingData<Article>>
    fun searchNews(searchQuery:String ,source: List<String>): Flow<PagingData<Article>>
}