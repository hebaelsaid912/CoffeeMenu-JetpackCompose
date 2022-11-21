package com.hebaelsaid.android.coffeemenu_jetbackcompose.data.repository

import com.hebaelsaid.android.coffeemenu_jetbackcompose.data.model.CoffeeResponseModel
import com.hebaelsaid.android.coffeemenu_jetbackcompose.data.remote.CoffeeApiInterface

class CoffeeRepositoryImpl(private val api: CoffeeApiInterface): CoffeeApiInterface {
    override suspend fun getHotCoffee(): CoffeeResponseModel {
        return api.getHotCoffee()
    }

    override suspend fun getIcedCoffee(): CoffeeResponseModel {
        return api.getIcedCoffee()
    }
}