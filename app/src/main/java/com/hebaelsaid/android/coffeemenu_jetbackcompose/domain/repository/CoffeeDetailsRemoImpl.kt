package com.hebaelsaid.android.coffeemenu_jetbackcompose.domain.repository

import com.hebaelsaid.android.coffeemenu_jetbackcompose.data.model.responsemodel.CoffeeResponseModel

interface CoffeeDetailsRemoImpl {
    suspend fun getCoffeeDetails(model: CoffeeResponseModel.CoffeeResponseModelItem):CoffeeResponseModel.CoffeeResponseModelItem
}