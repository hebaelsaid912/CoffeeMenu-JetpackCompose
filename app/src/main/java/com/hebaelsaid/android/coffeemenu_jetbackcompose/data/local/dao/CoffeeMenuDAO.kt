package com.hebaelsaid.android.coffeemenu_jetbackcompose.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.hebaelsaid.android.coffeemenu_jetbackcompose.data.local.entities.CoffeeDetailsItem

@Dao
interface CoffeeMenuDAO {
    @get:Query("SELECT * FROM coffee_menu ORDER BY _id DESC")
    val getAllCoffeeList:List<CoffeeDetailsItem>
    @Query("SELECT * FROM coffee_menu WHERE coffee_title =:title")
    fun getCoffeeDetailsByName(title:String):CoffeeDetailsItem
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCoffeeMenuItem(coffeeItem: CoffeeDetailsItem)
    @Query("DELETE  FROM coffee_menu")
    suspend fun clearCoffeeMenuDatabase()
}