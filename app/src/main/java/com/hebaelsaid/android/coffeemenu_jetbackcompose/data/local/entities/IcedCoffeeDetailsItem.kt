package com.hebaelsaid.android.coffeemenu_jetbackcompose.data.local.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "iced_coffee_menu")
data class IcedCoffeeDetailsItem(
    @PrimaryKey(autoGenerate = true)
    var _id:Int?=null,
    @ColumnInfo(name = "coffee_description")
    val description: String?, // Black coffee is as simple as it gets with ground coffee beans steeped in hot water, served warm. And if you want to sound fancy, you can call black coffee by its proper name: cafe noir.
    @ColumnInfo(name = "coffee_id")
    val id: Int?, // 1
    @ColumnInfo(name = "coffee_image")
    val image: String?, // https://upload.wikimedia.org/wikipedia/commons/thumb/4/45/A_small_cup_of_coffee.JPG/640px-A_small_cup_of_coffee.JPG
    @ColumnInfo(name = "coffee_ingredients")
    val ingredients: List<String?>?,
    @ColumnInfo(name = "coffee_title")
    val title: String?, // Black
    @ColumnInfo(name = "coffee_type")
    val type: String? // Black
)