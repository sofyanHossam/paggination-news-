package com.example.jetpackapi.presention.bookmark

import com.example.jetpackapi.domain.model.Article

data class BookmarkState(
    val article:List< Article> =emptyList()
)
