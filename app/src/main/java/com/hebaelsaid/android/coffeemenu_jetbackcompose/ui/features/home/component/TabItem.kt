package com.hebaelsaid.android.coffeemenu_jetbackcompose.ui.features.home.component

import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.vector.ImageVector



typealias composableFun = @Composable () -> Unit

sealed class TabItem(val title:String, val icon:ImageVector?, val screen: composableFun){

    object HotCoffee: TabItem("Hot",null,{ HotCoffeeScreen()})
    object IcedCoffee: TabItem("Iced",null,{ IcedCoffeeScreen()})

}