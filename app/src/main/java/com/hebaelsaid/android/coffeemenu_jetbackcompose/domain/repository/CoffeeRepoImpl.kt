package com.hebaelsaid.android.coffeemenu_jetbackcompose.domain.repository

import com.hebaelsaid.android.coffeemenu_jetbackcompose.data.model.CoffeeResponseModel

interface CoffeeRepoImpl {
    suspend fun getHotCoffee(): CoffeeResponseModel
    suspend fun getIcedCoffee(): CoffeeResponseModel
}