package com.hebaelsaid.android.coffeemenu_jetbackcompose.data.remote

import com.hebaelsaid.android.coffeemenu_jetbackcompose.data.model.CoffeeResponseModel
import retrofit2.http.GET

interface CoffeeApiInterface {

    @GET("hot")
    suspend fun getHotCoffee(): CoffeeResponseModel
    @GET("iced")
    suspend fun getIcedCoffee(): CoffeeResponseModel

}