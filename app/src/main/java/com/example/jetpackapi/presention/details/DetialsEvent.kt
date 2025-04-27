package com.example.jetpackapi.presention.details

import com.example.jetpackapi.domain.model.Article

sealed class DetailsEvent {

    data class UpsertDeleteArticle(
        val article: Article
    ) : DetailsEvent()

    object RemoveSideEffect : DetailsEvent()

}