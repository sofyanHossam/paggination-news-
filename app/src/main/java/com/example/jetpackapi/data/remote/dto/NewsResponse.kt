package com.example.jetpackapi.data.remote.dto

import com.example.jetpackapi.domain.model.Article

data class NewsResponse(
    val articles: List<Article>,
    val status: String,
    val totalResults: Int
)