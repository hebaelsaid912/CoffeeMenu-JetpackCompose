package com.hebaelsaid.android.coffeemenu_jetbackcompose.data.local.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.hebaelsaid.android.coffeemenu_jetbackcompose.data.local.dao.CoffeeMenuDAO
import com.hebaelsaid.android.coffeemenu_jetbackcompose.data.local.entities.HotCoffeeDetailsItem
import com.hebaelsaid.android.coffeemenu_jetbackcompose.data.local.entities.IcedCoffeeDetailsItem
import com.hebaelsaid.android.coffeemenu_jetbackcompose.data.local.entities.TheTypeConverters

@TypeConverters(TheTypeConverters::class)
@Database(entities = [HotCoffeeDetailsItem::class,IcedCoffeeDetailsItem::class],
    version = 1 , exportSchema = false)
abstract class CoffeeMenuDatabase  : RoomDatabase() {
    /*companion object{
        var coffeeMenuDatabase: CoffeeMenuDatabase? = null

        @Synchronized
        fun getDatabase(context: Context) : CoffeeMenuDatabase {
            if( coffeeMenuDatabase == null){
                coffeeMenuDatabase = Room.databaseBuilder(
                    context,
                    CoffeeMenuDatabase::class.java,
                    "coffee_menu.db"
                ).build()
            }
            return coffeeMenuDatabase!!
        }

    }*/
    abstract fun coffeeMenuDao(): CoffeeMenuDAO
}