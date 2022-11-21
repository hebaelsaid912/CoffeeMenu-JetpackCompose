package com.hebaelsaid.android.coffeemenu_jetbackcompose.ui

import com.hebaelsaid.android.coffeemenu_jetbackcompose.utils.Constant.COFFEE_DETAILS_SCREEN_ROUTE
import com.hebaelsaid.android.coffeemenu_jetbackcompose.utils.Constant.COFFEE_LIST_SCREEN_ROUTE

sealed class Screen(val route:String){
    object CoffeeListScreen:Screen(COFFEE_LIST_SCREEN_ROUTE)
    object CoffeeDetailsScreen:Screen(COFFEE_DETAILS_SCREEN_ROUTE)
}
