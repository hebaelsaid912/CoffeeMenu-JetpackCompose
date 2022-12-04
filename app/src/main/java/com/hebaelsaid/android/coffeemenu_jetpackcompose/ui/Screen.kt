package com.hebaelsaid.android.coffeemenu_jetpackcompose.ui

import com.hebaelsaid.android.coffeemenu_jetpackcompose.utils.Constant.COFFEE_DETAILS_SCREEN_ROUTE
import com.hebaelsaid.android.coffeemenu_jetpackcompose.utils.Constant.COFFEE_LIST_SCREEN_ROUTE
import com.hebaelsaid.android.coffeemenu_jetpackcompose.utils.Constant.HOME_SCREEN_ROUTE
import com.hebaelsaid.android.coffeemenu_jetpackcompose.utils.Constant.ON_BOARDING_SCREEN_ROUTE

sealed class Screen(val route:String){
    object OnBoardingScreen:Screen(ON_BOARDING_SCREEN_ROUTE)
    object HomeScreen:Screen(HOME_SCREEN_ROUTE)
    object CoffeeListScreen:Screen(COFFEE_LIST_SCREEN_ROUTE)
    object CoffeeDetailsScreen:Screen(COFFEE_DETAILS_SCREEN_ROUTE)
}
