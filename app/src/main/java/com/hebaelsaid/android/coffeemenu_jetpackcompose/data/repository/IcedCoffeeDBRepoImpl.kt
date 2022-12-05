package com.hebaelsaid.android.coffeemenu_jetpackcompose.data.repository

import com.hebaelsaid.android.coffeemenu_jetpackcompose.data.local.database.CoffeeMenuDatabase
import com.hebaelsaid.android.coffeemenu_jetpackcompose.data.local.entities.IcedCoffeeDetailsItem
import com.hebaelsaid.android.coffeemenu_jetpackcompose.domain.repository.CoffeeIcedDaoRepo
import javax.inject.Inject

class IcedCoffeeDBRepoImpl @Inject constructor(
    private val coffeeMenuDatabase: CoffeeMenuDatabase
): CoffeeIcedDaoRepo {
    override suspend fun getIcedAllCoffeeList(): List<IcedCoffeeDetailsItem> {
        return coffeeMenuDatabase.coffeeMenuDao().getAllIcedCoffeeList
    }

    override suspend fun getIcedCoffeeDetailsByName(id: Int): IcedCoffeeDetailsItem {
        return coffeeMenuDatabase.coffeeMenuDao().getIcedCoffeeDetailsByName(id)
    }

    override suspend fun insertIcedCoffeeMenuItemIntoDB(data: IcedCoffeeDetailsItem) {
        return coffeeMenuDatabase.coffeeMenuDao().insertIcedCoffeeMenuItem(data)
    }

    override suspend fun clearIcedCoffeeMenuDatabase() {
        return coffeeMenuDatabase.coffeeMenuDao().clearIcedCoffeeMenuDatabase()
    }

}