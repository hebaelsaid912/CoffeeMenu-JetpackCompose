package com.hebaelsaid.android.coffeemenu_jetpackcompose.domain.repository

import com.hebaelsaid.android.coffeemenu_jetpackcompose.data.local.entities.HotCoffeeDetailsItem

interface CoffeeHotDaoRepo {
    suspend fun getHotAllCoffeeList():List<HotCoffeeDetailsItem>
    suspend fun getHotCoffeeDetailsByName(id:Int):HotCoffeeDetailsItem
    suspend fun insertHotCoffeeMenuItemIntoDB(data:HotCoffeeDetailsItem)
    suspend fun clearHotCoffeeMenuDatabase()
}