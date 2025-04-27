package com.example.jetpackapi.presention.navgraph

sealed class Rout(
    val rout:String
){
    object OnBoardingScreen:Rout(rout = "onBoardingScreen")
    object HomeScreen:Rout(rout = "homeScreen")
    object SearchScreen:Rout(rout = "SearchScreen")
    object BookMarkScreen:Rout(rout = "BookMarkScreen")
    object DetailsScreen:Rout(rout = "DetailsScreen")

    object AppStartNavigation:Rout(rout ="appStartNavigation" )
    object NewsNavigation:Rout(rout ="newsNavigation" )
    object NewsNavigatorScreen:Rout(rout ="newsNavigator" )
}
