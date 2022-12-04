package com.hebaelsaid.android.coffeemenu_jetpackcompose.data.remote

import com.hebaelsaid.android.coffeemenu_jetpackcompose.data.model.responsemodel.CoffeeResponseModel
import retrofit2.http.GET

interface CoffeeApiInterface {

    @GET("hot")
    suspend fun getHotCoffee(): CoffeeResponseModel
    @GET("iced")
    suspend fun getIcedCoffee(): CoffeeResponseModel

}