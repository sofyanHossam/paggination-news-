package com.example.jetpackapi.presention.search

sealed class SearchEvent {
    data class UpdateSearchQuery(val searchQuery :String):SearchEvent()

    object SearchNews : SearchEvent()
}