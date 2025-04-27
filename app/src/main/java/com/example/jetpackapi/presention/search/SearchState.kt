package com.example.jetpackapi.presention.search

import androidx.paging.PagingData
import com.example.jetpackapi.domain.model.Article
import kotlinx.coroutines.flow.Flow

data class SearchState(
    val searchQuery:String="",
    val article: Flow<PagingData<Article>>? = null
) {
}