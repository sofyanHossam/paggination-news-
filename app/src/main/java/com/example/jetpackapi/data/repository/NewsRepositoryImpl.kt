package com.example.jetpackapi.data.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.example.jetpackapi.data.remote.NewsApi
import com.example.jetpackapi.data.remote.NewsPagingSource
import com.example.jetpackapi.data.remote.SearchNewsPagingSource
import com.example.jetpackapi.domain.model.Article
import com.example.jetpackapi.domain.repository.NewsRepository
import kotlinx.coroutines.flow.Flow

class NewsRepositoryImpl(
    private val newsApi: NewsApi
) :NewsRepository{
    override fun getNews(source: List<String>): Flow<PagingData<Article>> {
       return Pager (
           config = PagingConfig(pageSize = 10),
           pagingSourceFactory = {
               NewsPagingSource(
                   newsApi=newsApi,
                   sources = source.joinToString(separator = ",")
               )
           }

       ).flow
    }

    override fun searchNews(searchQuery: String, source: List<String>): Flow<PagingData<Article>> {
        return Pager (

            config = PagingConfig(pageSize = 10),
            pagingSourceFactory = {
                SearchNewsPagingSource(
                    newsApi=newsApi,
                    searchQuery=searchQuery,
                    sources = source.joinToString(separator = ",")
                )
            }

        ).flow
    }

}