package com.hebaelsaid.android.coffeemenu_jetpackcompose.domain.repository

import com.hebaelsaid.android.coffeemenu_jetpackcompose.data.model.responsemodel.CoffeeResponseModel

interface CoffeeDetailsRemoImpl {
    suspend fun getCoffeeDetails(model: CoffeeResponseModel.CoffeeResponseModelItem):CoffeeResponseModel.CoffeeResponseModelItem
}