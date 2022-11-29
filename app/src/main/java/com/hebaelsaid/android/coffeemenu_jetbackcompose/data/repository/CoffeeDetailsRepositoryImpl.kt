package com.hebaelsaid.android.coffeemenu_jetbackcompose.data.repository

import com.hebaelsaid.android.coffeemenu_jetbackcompose.data.model.responsemodel.CoffeeResponseModel
import com.hebaelsaid.android.coffeemenu_jetbackcompose.domain.repository.CoffeeDetailsRemoImpl


class CoffeeDetailsRepositoryImpl : CoffeeDetailsRemoImpl {
    override suspend fun getCoffeeDetails(model: CoffeeResponseModel.CoffeeResponseModelItem): CoffeeResponseModel.CoffeeResponseModelItem {
        return model
    }

}