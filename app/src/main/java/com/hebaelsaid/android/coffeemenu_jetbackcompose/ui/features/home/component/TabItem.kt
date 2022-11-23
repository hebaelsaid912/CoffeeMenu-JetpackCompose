package com.hebaelsaid.android.coffeemenu_jetbackcompose.ui.features.home.component

import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.navigation.compose.rememberNavController


typealias composableFun = @Composable () -> Unit
sealed class TabItem(val title:String, val icon:ImageVector?, val screen: composableFun){

    object HotCoffee: TabItem("Hot",null,{ HotCoffeeScreen(rememberNavController())})
    object IcedCoffee: TabItem("Iced",null,{ IcedCoffeeScreen(rememberNavController())})

}