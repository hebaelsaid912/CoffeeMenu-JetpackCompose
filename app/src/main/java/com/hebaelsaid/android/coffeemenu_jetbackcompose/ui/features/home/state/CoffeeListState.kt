package com.hebaelsaid.android.coffeemenu_jetbackcompose.ui.features.home.state

import com.hebaelsaid.android.coffeemenu_jetbackcompose.data.model.responsemodel.CoffeeResponseModel

data class CoffeeListState(
    val isLoading:Boolean=false,
    val modelItem: CoffeeResponseModel = CoffeeResponseModel(),
    val error:String = ""
)
