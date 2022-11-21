package com.hebaelsaid.android.coffeemenu_jetbackcompose.domain.usecase

import com.hebaelsaid.android.coffeemenu_jetbackcompose.data.model.CoffeeResponseModel
import com.hebaelsaid.android.coffeemenu_jetbackcompose.domain.repository.CoffeeRepoImpl

class GetCoffeeUseCase(private val repoImpl: CoffeeRepoImpl) : CoffeeRepoImpl{
    override suspend fun getHotCoffee(): CoffeeResponseModel {
        return repoImpl.getHotCoffee()
    }

    override suspend fun getIcedCoffee(): CoffeeResponseModel {
        return repoImpl.getIcedCoffee()
    }
}