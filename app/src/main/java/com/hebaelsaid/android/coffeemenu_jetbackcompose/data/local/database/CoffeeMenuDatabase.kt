package com.hebaelsaid.android.coffeemenu_jetbackcompose.data.local.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.hebaelsaid.android.coffeemenu_jetbackcompose.data.local.dao.CoffeeMenuDAO
import com.hebaelsaid.android.coffeemenu_jetbackcompose.data.local.entities.CoffeeDetailsItem

@Database(entities = [CoffeeDetailsItem::class],
    version = 1 , exportSchema = false)
abstract class CoffeeMenuDatabase  : RoomDatabase() {
    companion object{
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

    }
    abstract fun coffeeMenuDao(): CoffeeMenuDAO
}