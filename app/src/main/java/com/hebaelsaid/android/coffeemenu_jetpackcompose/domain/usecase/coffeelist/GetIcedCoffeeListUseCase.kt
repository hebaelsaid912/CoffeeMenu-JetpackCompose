package com.hebaelsaid.android.coffeemenu_jetpackcompose.domain.usecase.coffeelist

import com.hebaelsaid.android.coffeemenu_jetpackcompose.data.model.responsemodel.CoffeeResponseModel
import com.hebaelsaid.android.coffeemenu_jetpackcompose.domain.repository.CoffeeApiRepoImpl
import com.hebaelsaid.android.coffeemenu_jetpackcompose.utils.Resource
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class GetIcedCoffeeListUseCase @Inject constructor(
    private val repository: CoffeeApiRepoImpl
){
    operator fun invoke() = flow<Resource<CoffeeResponseModel>> {
        try {
            emit(Resource.Loading<CoffeeResponseModel>())
            val coffeeResponseModel = repository.getIcedCoffee()
            if (!coffeeResponseModel.isEmpty()) {
                emit(Resource.Success<CoffeeResponseModel>(data = coffeeResponseModel))
            }else {
                emit(Resource.Error<CoffeeResponseModel>( "Data Return With Null"))
            }
        }catch (e: HttpException){
            emit(Resource.Error<CoffeeResponseModel>(e.localizedMessage?: "An unexpected error occurred"))
        }catch (e: IOException){
            emit(Resource.Error<CoffeeResponseModel>("Couldn't reach server. Please check your internet connection"))
        }
    }

}