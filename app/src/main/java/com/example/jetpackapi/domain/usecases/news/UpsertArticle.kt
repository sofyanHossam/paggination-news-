package com.example.jetpackapi.domain.usecases.news

import com.example.jetpackapi.data.local.dao.NewsDao
import com.example.jetpackapi.domain.model.Article

class UpsertArticle(
    private val newsDao: NewsDao
) {
    suspend operator fun invoke(article: Article){
        newsDao.upsert(article)

    }
}