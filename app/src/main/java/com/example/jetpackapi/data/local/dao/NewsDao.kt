package com.example.jetpackapi.data.local.dao

import androidx.room.*
import com.example.jetpackapi.domain.model.Article
import kotlinx.coroutines.flow.Flow


@Dao
interface NewsDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun upsert(article: Article)


    @Delete
    suspend fun delete(article: Article)

    @Query("SELECT * FROM Article")
    fun getArticles(): Flow<List<Article>>

    @Query("SELECT * FROM Article WHERE url =:url")
    suspend fun getArticle(url:String):Article?
}