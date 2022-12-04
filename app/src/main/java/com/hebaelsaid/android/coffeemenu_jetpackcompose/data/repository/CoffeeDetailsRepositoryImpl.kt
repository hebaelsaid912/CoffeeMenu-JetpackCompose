package com.hebaelsaid.android.coffeemenu_jetpackcompose.data.repository

import com.hebaelsaid.android.coffeemenu_jetpackcompose.data.model.responsemodel.CoffeeResponseModel
import com.hebaelsaid.android.coffeemenu_jetpackcompose.domain.repository.CoffeeDetailsRemoImpl


class CoffeeDetailsRepositoryImpl : CoffeeDetailsRemoImpl {
    override suspend fun getCoffeeDetails(model: CoffeeResponseModel.CoffeeResponseModelItem): CoffeeResponseModel.CoffeeResponseModelItem {
        return model
    }

}