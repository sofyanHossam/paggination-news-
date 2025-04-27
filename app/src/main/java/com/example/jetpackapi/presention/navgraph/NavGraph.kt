package com.example.jetpackapi.presention.navgraph

import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navigation
import androidx.paging.compose.collectAsLazyPagingItems
import com.example.jetpackapi.presention.bookmark.BookmarkScreen
import com.example.jetpackapi.presention.bookmark.BookmarkViewModel
import com.example.jetpackapi.presention.home.HomeScreen
import com.example.jetpackapi.presention.home.HomeViewModel
import com.example.jetpackapi.presention.news_navigator.NewsNavigator
import com.example.jetpackapi.presention.onboarding.OnBoardingScreen
import com.example.jetpackapi.presention.onboarding.OnBoardingViewModel
import com.example.jetpackapi.presention.search.SearchScreen
import com.example.jetpackapi.presention.search.SearchViewModel

@Composable
fun NavGraph(
    startDestination: String
){
    val navController= rememberNavController()
    NavHost(navController = navController, startDestination = startDestination){
        navigation(
            route= Rout.AppStartNavigation.rout,
            startDestination=Rout.OnBoardingScreen.rout
        ){
            composable(
                route=Rout.OnBoardingScreen.rout

            ){
                val viewModel : OnBoardingViewModel = hiltViewModel()
                OnBoardingScreen(
                    event = viewModel::onEvent
                )
            }
        }
        navigation(
            route=Rout.NewsNavigation.rout,
            startDestination=Rout.NewsNavigatorScreen.rout
        ){
            composable(
                route=Rout.NewsNavigatorScreen.rout
            ){

               NewsNavigator()
            }
        }
    }
}