package com.hebaelsaid.android.coffeemenu_jetbackcompose.data.model.responsemodel


class CoffeeResponseModel : ArrayList<CoffeeResponseModel.CoffeeResponseModelItem>(){
    data class CoffeeResponseModelItem(
        val description: String?, // Black coffee is as simple as it gets with ground coffee beans steeped in hot water, served warm. And if you want to sound fancy, you can call black coffee by its proper name: cafe noir.
        val id: Int?, // 1
        val image: String?, // https://upload.wikimedia.org/wikipedia/commons/thumb/4/45/A_small_cup_of_coffee.JPG/640px-A_small_cup_of_coffee.JPG
        val ingredients: List<String?>?,
        val title: String? // Black
    )
}