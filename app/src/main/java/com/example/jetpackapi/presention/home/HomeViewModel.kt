package com.example.jetpackapi.presention.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.example.jetpackapi.domain.usecases.news.NewsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val newsUseCase: NewsUseCase

):ViewModel() {
    val news =newsUseCase.getNews(
        sources = listOf("bbc-news","abc-news","al-jazeera-english")
    ).cachedIn(viewModelScope)
}