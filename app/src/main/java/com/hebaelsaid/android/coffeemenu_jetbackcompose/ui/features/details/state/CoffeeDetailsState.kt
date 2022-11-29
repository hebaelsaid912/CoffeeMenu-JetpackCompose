package com.hebaelsaid.android.coffeemenu_jetbackcompose.ui.features.details.state

import com.hebaelsaid.android.coffeemenu_jetbackcompose.data.model.responsemodel.CoffeeResponseModel

data class CoffeeDetailsState(
    val isLoading:Boolean=false,
    val modelItem: CoffeeResponseModel.CoffeeResponseModelItem?=null,
    val error:String = ""
)
