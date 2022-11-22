package com.hebaelsaid.android.coffeemenu_jetbackcompose.domain.usecase.coffeelist

import com.hebaelsaid.android.coffeemenu_jetbackcompose.data.model.responsemodel.CoffeeResponseModel
import com.hebaelsaid.android.coffeemenu_jetbackcompose.domain.repository.CoffeeRepoImpl
import com.hebaelsaid.android.coffeemenu_jetbackcompose.utils.Resource
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class GetHotCoffeeListUseCase @Inject constructor(
    private val repository: CoffeeRepoImpl
){
    operator fun invoke() = flow<Resource<CoffeeResponseModel>> {
        try {
            emit(Resource.Loading<CoffeeResponseModel>())
            val coffeeResponseModel = repository.getHotCoffee()
            emit(Resource.Success<CoffeeResponseModel>(coffeeResponseModel))
        }catch (e: HttpException){
            emit(Resource.Error<CoffeeResponseModel>(e.localizedMessage?: "An unexpected error occurred"))
        }catch (e: IOException){
            emit(Resource.Error<CoffeeResponseModel>("Couldn't reach server. Please check your internet connection"))
        }
    }

}