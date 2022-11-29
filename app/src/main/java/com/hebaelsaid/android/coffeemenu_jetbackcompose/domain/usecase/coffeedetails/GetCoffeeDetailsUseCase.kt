package com.hebaelsaid.android.coffeemenu_jetbackcompose.domain.usecase.coffeedetails

import com.hebaelsaid.android.coffeemenu_jetbackcompose.data.model.responsemodel.CoffeeResponseModel
import com.hebaelsaid.android.coffeemenu_jetbackcompose.domain.repository.CoffeeDetailsRemoImpl
import com.hebaelsaid.android.coffeemenu_jetbackcompose.utils.Resource
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class GetCoffeeDetailsUseCase @Inject constructor(
    private val repository: CoffeeDetailsRemoImpl
) {
    operator fun invoke(model: CoffeeResponseModel.CoffeeResponseModelItem) =
        flow<Resource<CoffeeResponseModel.CoffeeResponseModelItem>> {
            try {
                emit(Resource.Loading<CoffeeResponseModel.CoffeeResponseModelItem>())
                val coffeeModel = repository.getCoffeeDetails(model)
                emit(Resource.Success<CoffeeResponseModel.CoffeeResponseModelItem>(data = coffeeModel))
            } catch (e: HttpException) {
                emit(
                    Resource.Error<CoffeeResponseModel.CoffeeResponseModelItem>(
                        e.localizedMessage ?: "An unexpected error occurred"
                    )
                )
            } catch (e: IOException) {
                emit(Resource.Error<CoffeeResponseModel.CoffeeResponseModelItem>("Couldn't reach server. Please check your internet connection"))
            }
        }

}