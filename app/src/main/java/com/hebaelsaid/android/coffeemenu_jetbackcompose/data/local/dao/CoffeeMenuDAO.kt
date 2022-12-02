package com.hebaelsaid.android.coffeemenu_jetbackcompose.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.hebaelsaid.android.coffeemenu_jetbackcompose.data.local.entities.HotCoffeeDetailsItem
import com.hebaelsaid.android.coffeemenu_jetbackcompose.data.local.entities.IcedCoffeeDetailsItem

@Dao
interface CoffeeMenuDAO {
    // hot coffee table
    @get:Query("SELECT * FROM hot_coffee_menu ORDER BY id DESC")
    val getHotAllCoffeeList:List<HotCoffeeDetailsItem>
    @Query("SELECT * FROM hot_coffee_menu WHERE coffee_title =:title")
    suspend fun getHotCoffeeDetailsByName(title:String):HotCoffeeDetailsItem
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertHotCoffeeMenuItem(coffeeItem: HotCoffeeDetailsItem)
    @Query("DELETE  FROM hot_coffee_menu")
    suspend fun clearHotCoffeeMenuDatabase()

    // iced coffee table
    @get:Query("SELECT * FROM iced_coffee_menu ORDER BY id DESC")
    val getAllIcedCoffeeList:List<IcedCoffeeDetailsItem>
    @Query("SELECT * FROM iced_coffee_menu WHERE coffee_title =:title")
    suspend fun getIcedCoffeeDetailsByName(title:String):IcedCoffeeDetailsItem
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertIcedCoffeeMenuItem(coffeeItem: IcedCoffeeDetailsItem)
    @Query("DELETE  FROM iced_coffee_menu")
    suspend fun clearIcedCoffeeMenuDatabase()
}