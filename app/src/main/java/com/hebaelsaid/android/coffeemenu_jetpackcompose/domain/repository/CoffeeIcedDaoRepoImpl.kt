package com.hebaelsaid.android.coffeemenu_jetpackcompose.domain.repository

import com.hebaelsaid.android.coffeemenu_jetpackcompose.data.local.entities.IcedCoffeeDetailsItem

interface CoffeeIcedDaoRepoImpl {
    suspend fun getIcedAllCoffeeList():List<IcedCoffeeDetailsItem>
    suspend fun getIcedCoffeeDetailsByName(id:Int):IcedCoffeeDetailsItem
    suspend fun insertIcedCoffeeMenuItemIntoDB(data:IcedCoffeeDetailsItem)
    suspend fun clearIcedCoffeeMenuDatabase()
}