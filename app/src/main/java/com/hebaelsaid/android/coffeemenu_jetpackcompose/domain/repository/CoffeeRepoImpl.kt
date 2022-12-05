package com.hebaelsaid.android.coffeemenu_jetpackcompose.domain.repository

import com.hebaelsaid.android.coffeemenu_jetpackcompose.data.model.responsemodel.CoffeeResponseModel

interface CoffeeRepoImpl {
    suspend fun getHotCoffee(): CoffeeResponseModel
    suspend fun getIcedCoffee(): CoffeeResponseModel
}