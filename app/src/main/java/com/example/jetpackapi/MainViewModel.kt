package com.example.jetpackapi

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.jetpackapi.domain.usecases.app_entry.AppEntryUseCases
import com.example.jetpackapi.presention.navgraph.Rout
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private  val appEntryUseCases: AppEntryUseCases
):ViewModel() {

     var splashCondition by mutableStateOf(true)
        private set

    var startDestination by mutableStateOf(Rout.AppStartNavigation.rout)
        private  set

    init {
        appEntryUseCases.readAppEntry().onEach { shouldStartFromHomeScreen ->
            if (shouldStartFromHomeScreen){
                startDestination=Rout.NewsNavigation.rout
            }else{
                startDestination=Rout.AppStartNavigation.rout
            }
            delay(300)
            splashCondition =false
        }.launchIn(viewModelScope)
    }
}