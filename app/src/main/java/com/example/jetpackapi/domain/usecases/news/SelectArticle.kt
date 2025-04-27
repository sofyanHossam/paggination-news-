package com.example.jetpackapi.domain.usecases.news

import com.example.jetpackapi.data.local.dao.NewsDao
import com.example.jetpackapi.domain.model.Article

class SelectArticle(
    private val newsDao: NewsDao
) {
    suspend operator fun invoke(url: String):Article?{
       return newsDao.getArticle(url)

    }
}