package com.example.jetpackapi.presention.news_navigator



import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.Navigation
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.paging.compose.collectAsLazyPagingItems
import com.example.jetpackapi.R
import com.example.jetpackapi.domain.model.Article
import com.example.jetpackapi.presention.bookmark.BookmarkScreen
import com.example.jetpackapi.presention.bookmark.BookmarkViewModel
import com.example.jetpackapi.presention.details.DetailsScreen
import com.example.jetpackapi.presention.details.DetailsViewModel
import com.example.jetpackapi.presention.home.HomeScreen
import com.example.jetpackapi.presention.home.HomeViewModel
import com.example.jetpackapi.presention.navgraph.Rout
import com.example.jetpackapi.presention.news_navigator.components.BottomNavigationItem
import com.example.jetpackapi.presention.news_navigator.components.NewsBottomNavigation
import com.example.jetpackapi.presention.search.SearchScreen
import com.example.jetpackapi.presention.search.SearchViewModel
import okhttp3.Route


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NewsNavigator() {

    val bottomNavigationItems = remember {
        listOf(
            BottomNavigationItem(icon = R.drawable.ic_home, text = "Home"),
            BottomNavigationItem(icon = R.drawable.ic_search, text = "Search"),
            BottomNavigationItem(icon = R.drawable.ic_bookmark, text = "Bookmark"),
        )
    }

    val navController = rememberNavController()
    val backStackState = navController.currentBackStackEntryAsState().value
    var selectedItem by rememberSaveable {
        mutableStateOf(0)
    }
    selectedItem = when (backStackState?.destination?.route) {
        Rout.HomeScreen.rout -> 0
        Rout.SearchScreen.rout -> 1
        Rout.BookMarkScreen.rout -> 2
        else -> 0
    }

    Scaffold(modifier = Modifier.fillMaxSize(), bottomBar = {
        NewsBottomNavigation(
            items = bottomNavigationItems,
            selectedItem = selectedItem,
            onItemClick = { index ->
                when (index) {
                    0 -> navigateToTab(
                        navController = navController,
                        route = Rout.HomeScreen.rout
                    )

                    1 -> navigateToTab(
                        navController = navController,
                        route = Rout.SearchScreen.rout
                    )

                    2 -> navigateToTab(
                        navController = navController,
                        route = Rout.BookMarkScreen.rout
                    )
                }
            }
        )
    }) {
        val bottomPadding = it.calculateBottomPadding()
        NavHost(
            navController = navController,
            startDestination = Rout.HomeScreen.rout,
            modifier = Modifier.padding(bottom = bottomPadding)
        ) {
            composable(route = Rout.HomeScreen.rout) {
                val viewModel: HomeViewModel = hiltViewModel()
                val articles = viewModel.news.collectAsLazyPagingItems()
                HomeScreen(
                    articles = articles,
                    navigateToSearch = {
                        navigateToTab(navController=navController,Rout.SearchScreen.rout)
                    },
                    navigateToDetails = {article->
                        navigateToDetails(
                            navController=navController,
                            article=article
                        )
                    }
                )
            }
            composable(route = Rout.SearchScreen.rout) {
                val viewModel: SearchViewModel = hiltViewModel()
                val state = viewModel.state.value
                OnBackClickStateSaver(navController = navController)
                SearchScreen(
                    state = state,
                    event = viewModel::onEvent,
                    navigateToDetails = { article ->
                        navigateToDetails(
                            navController = navController,
                            article = article
                        )
                    }
                )
            }
            composable(route = Rout.DetailsScreen.rout) {
                val viewModel: DetailsViewModel = hiltViewModel()
                navController.previousBackStackEntry?.savedStateHandle?.get<Article?>("article")
                    ?.let { article ->
                        DetailsScreen(
                            article = article,
                            event = viewModel::onEvent,
                            navigateUp = { navController.navigateUp() }

                        )
                    }

            }
            composable(route = Rout.BookMarkScreen.rout) {
                val viewModel: BookmarkViewModel = hiltViewModel()
                val state = viewModel.state.value
                OnBackClickStateSaver(navController = navController)
                BookmarkScreen(
                    state = state,
                    navigateToDetails = { article ->
                        navigateToDetails(
                            navController = navController,
                            article = article
                        )
                    }
                )
            }
        }
    }
}

private fun navigateToTab(navController: NavController, route: String) {
    navController.navigate(route) {
        navController.graph.startDestinationRoute?.let { screen_route ->
            popUpTo(screen_route) {
                saveState = true
            }
        }
        launchSingleTop = true
        restoreState = true
    }
}
@Composable
fun OnBackClickStateSaver(navController: NavController) {
    BackHandler(true) {
        navigateToTab(
            navController = navController,
            route = Rout.HomeScreen.rout
        )
    }
}

private fun navigateToDetails(navController: NavController,article:Article){
    navController.currentBackStackEntry?.savedStateHandle?.set("article",article)
    navController.navigate(
        route = Rout.DetailsScreen.rout
    )

}