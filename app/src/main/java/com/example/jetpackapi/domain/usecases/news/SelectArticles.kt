package com.example.jetpackapi.domain.usecases.news

import com.example.jetpackapi.data.local.dao.NewsDao
import com.example.jetpackapi.domain.model.Article
import kotlinx.coroutines.flow.Flow

class SelectArticles(
    private val newsDao: NewsDao
) {
     operator fun invoke():Flow<List<Article>>{
        return newsDao.getArticles()

    }
}