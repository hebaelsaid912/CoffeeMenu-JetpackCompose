package com.hebaelsaid.android.coffeemenu_jetpackcompose.data.repository

import com.hebaelsaid.android.coffeemenu_jetpackcompose.data.model.responsemodel.CoffeeResponseModel
import com.hebaelsaid.android.coffeemenu_jetpackcompose.data.remote.CoffeeApiInterface
import com.hebaelsaid.android.coffeemenu_jetpackcompose.domain.repository.CoffeeApiRepoImpl
import javax.inject.Inject

class CoffeeApiRepositoryImpl @Inject constructor(private val api: CoffeeApiInterface): CoffeeApiRepoImpl {
    override suspend fun getHotCoffee(): CoffeeResponseModel {
        return api.getHotCoffee()
    }

    override suspend fun getIcedCoffee(): CoffeeResponseModel {
        return api.getIcedCoffee()
    }
}