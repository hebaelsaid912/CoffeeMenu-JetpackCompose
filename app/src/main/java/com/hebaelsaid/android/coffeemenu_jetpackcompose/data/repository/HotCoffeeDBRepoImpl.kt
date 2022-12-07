package com.hebaelsaid.android.coffeemenu_jetpackcompose.data.repository

import com.hebaelsaid.android.coffeemenu_jetpackcompose.data.local.database.CoffeeMenuDatabase
import com.hebaelsaid.android.coffeemenu_jetpackcompose.data.local.entities.HotCoffeeDetailsItem
import com.hebaelsaid.android.coffeemenu_jetpackcompose.domain.repository.HotCoffeeRepo
import javax.inject.Inject

class HotCoffeeDBRepoImpl @Inject constructor(
    private val coffeeMenuDatabase: CoffeeMenuDatabase
): HotCoffeeRepo {
    override suspend fun getHotAllCoffeeList(): List<HotCoffeeDetailsItem> {
        return coffeeMenuDatabase.coffeeMenuDao().getHotAllCoffeeList
    }

    override suspend fun getHotCoffeeDetailsByName(id: Int): HotCoffeeDetailsItem {
        return coffeeMenuDatabase.coffeeMenuDao().getHotCoffeeDetailsByName(id)
    }

    override suspend fun insertHotCoffeeMenuItemIntoDB(data: HotCoffeeDetailsItem) {
        return coffeeMenuDatabase.coffeeMenuDao().insertHotCoffeeMenuItem(data)
    }

    override suspend fun clearHotCoffeeMenuDatabase() {
        return coffeeMenuDatabase.coffeeMenuDao().clearHotCoffeeMenuDatabase()
    }

}