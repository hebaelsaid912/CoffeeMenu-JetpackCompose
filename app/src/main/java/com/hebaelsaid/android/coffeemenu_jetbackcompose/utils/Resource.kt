package com.hebaelsaid.android.coffeemenu_jetbackcompose.utils

import com.hebaelsaid.android.coffeemenu_jetbackcompose.data.model.responsemodel.CoffeeResponseModel

sealed class Resource<T> (val data:T? = null , val message:String? = null){
    class Success<T>(data: T) : Resource<T>(data)
    class Error<T>(message: String?, data: T?=null) : Resource<T>(data = data, message = message)
    class Loading<T>(data: T? = null) : Resource<T>(data)
}
