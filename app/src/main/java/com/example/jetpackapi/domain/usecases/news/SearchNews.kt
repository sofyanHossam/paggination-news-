package com.example.jetpackapi.domain.usecases.news

import androidx.paging.PagingData
import com.example.jetpackapi.domain.model.Article
import com.example.jetpackapi.domain.repository.NewsRepository
import kotlinx.coroutines.flow.Flow

class SearchNews(
    private val newsRepository:NewsRepository
) {
    operator fun invoke(searchQuery:String,sources:List<String>): Flow<PagingData<Article>> {

        return newsRepository.searchNews(searchQuery =searchQuery,  sources)
    }

}