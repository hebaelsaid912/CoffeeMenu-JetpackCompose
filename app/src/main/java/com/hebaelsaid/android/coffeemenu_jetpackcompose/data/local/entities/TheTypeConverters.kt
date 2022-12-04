package com.hebaelsaid.android.coffeemenu_jetpackcompose.data.local.entities

import androidx.room.TypeConverter

class TheTypeConverters {
    @TypeConverter
    fun fromListStringToString(list: List<String>): String = list.toString()
    @TypeConverter
    fun toListStringFromString(stringList: String): List<String> {
        val result = ArrayList<String>()
        val split =stringList.replace("[","").replace("]","").replace(" ","").split(",")
        for (n in split) {
            try {
                result.add(n)
            } catch (e: Exception) {

            }
        }
        return result
    }
}