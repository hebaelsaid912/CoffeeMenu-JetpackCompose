package com.hebaelsaid.android.coffeemenu_jetpackcompose.ui.features.home.component

import androidx.compose.ui.graphics.vector.ImageVector


sealed class TabItem(val title:String, val icon:ImageVector?/*, val screen: composableFun*/){

    object HotCoffee: TabItem("Hot",null,/*{ HotCoffeeScreen(navController = navController)}*/)
    object IcedCoffee: TabItem("Iced",null,/*{ IcedCoffeeScreen(rememberNavController())}*/)

}