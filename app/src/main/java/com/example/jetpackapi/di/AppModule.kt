package com.example.jetpackapi.di

import android.app.Application
import androidx.room.Room
import com.example.jetpackapi.data.local.NewsDatabase
import com.example.jetpackapi.data.local.NewsTypeConvertor
import com.example.jetpackapi.data.local.dao.NewsDao
import com.example.jetpackapi.data.manager.LocalUserManagerImpl
import com.example.jetpackapi.data.remote.NewsApi
import com.example.jetpackapi.data.repository.NewsRepositoryImpl
import com.example.jetpackapi.domain.manger.LocalUserManger
import com.example.jetpackapi.domain.repository.NewsRepository
import com.example.jetpackapi.domain.usecases.app_entry.AppEntryUseCases
import com.example.jetpackapi.domain.usecases.app_entry.ReadAppEntry
import com.example.jetpackapi.domain.usecases.app_entry.SaveAppEntry
import com.example.jetpackapi.domain.usecases.news.*
import com.example.jetpackapi.util.Constants.BASE_URL
import com.example.jetpackapi.util.Constants.RoomDatabaseName
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun providesLocalUserManager(
        application: Application
    ):LocalUserManger=LocalUserManagerImpl(context = application)

//mine
//    @Provides
//    @Singleton
//    fun provideAppEntryUseCases(
//        localUserManger: LocalUserManger)
//    = AppEntryUseCases(
//        readAppEntry = ReadAppEntry(localUserManger)
//        , saveAppEntry = SaveAppEntry(localUserManger)
//
//    )

    //github
    @Provides
    @Singleton
    fun provideAppEntryUseCases(
        localUserManger: LocalUserManger
    ): AppEntryUseCases = AppEntryUseCases(
        readAppEntry = ReadAppEntry(localUserManger),
        saveAppEntry = SaveAppEntry(localUserManger)
    )


    @Provides
    @Singleton
    fun  provideNewsApi(): NewsApi {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(NewsApi::class.java)

    }


    @Provides
    @Singleton
    fun provideNewsRepository(
        newsApi: NewsApi
    ):NewsRepository{
        return NewsRepositoryImpl(newsApi)
    }

    @Provides
    @Singleton
    fun provideNewsUseCases(
        newsRepository: NewsRepository,
        newsDao: NewsDao
    ):NewsUseCase{
        return NewsUseCase(
            getNews = GetNews(newsRepository),
            searchNews = SearchNews(newsRepository),
            upsertArticle = UpsertArticle(newsDao),
            deleteArticle = DeleteArticle(newsDao),
            selectArticles = SelectArticles(newsDao),
            selectArticle = SelectArticle(newsDao)
        )
    }

    @Provides
    @Singleton
    fun provideNewsDatabase(
        application: Application
    ): NewsDatabase {
        return Room.databaseBuilder(
            context = application,
            klass = NewsDatabase::class.java,
            name = RoomDatabaseName
        ).addTypeConverter(NewsTypeConvertor())
            .fallbackToDestructiveMigration()
            .build()
    }

    @Provides
    @Singleton
    fun provideNewsDao(
        newsDatabase: NewsDatabase
    ):NewsDao=newsDatabase.newsDao






}