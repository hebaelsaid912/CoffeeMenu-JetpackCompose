package com.hebaelsaid.android.coffeemenu_jetbackcompose.domain.usecase.coffeedetails

import com.hebaelsaid.android.coffeemenu_jetbackcompose.data.local.database.CoffeeMenuDatabase
import com.hebaelsaid.android.coffeemenu_jetbackcompose.data.model.responsemodel.CoffeeResponseModel
import com.hebaelsaid.android.coffeemenu_jetbackcompose.utils.Resource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.withContext
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class GetCoffeeDetailsUseCase @Inject constructor(
    private val coffeeMenuDatabase: CoffeeMenuDatabase
) {
    operator fun invoke(title: String, type: String) =
        flow<Resource<CoffeeResponseModel.CoffeeResponseModelItem>> {
            try {
                emit(Resource.Loading<CoffeeResponseModel.CoffeeResponseModelItem>())
                when (type) {
                    "hot" -> {
                        val coffeeModel = withContext(Dispatchers.Default) {
                            coffeeMenuDatabase.coffeeMenuDao().getHotCoffeeDetailsByName(title)
                        }
                        emit(
                            Resource.Success<CoffeeResponseModel.CoffeeResponseModelItem>(
                                data = CoffeeResponseModel.CoffeeResponseModelItem(
                                    id = coffeeModel.item_id,
                                    title = coffeeModel.title,
                                    ingredients = coffeeModel.ingredients,
                                    image = coffeeModel.image,
                                    description = coffeeModel.description
                                )
                            )
                        )
                    }
                    "iced" -> {
                        val coffeeModel = withContext(Dispatchers.Default) {
                            coffeeMenuDatabase.coffeeMenuDao().getIcedCoffeeDetailsByName(title)
                        }
                        emit(
                            Resource.Success<CoffeeResponseModel.CoffeeResponseModelItem>(
                                data = CoffeeResponseModel.CoffeeResponseModelItem(
                                    id = coffeeModel.item_id,
                                    title = coffeeModel.title,
                                    ingredients = coffeeModel.ingredients,
                                    image = coffeeModel.image,
                                    description = coffeeModel.description
                                )
                            )
                        )
                    }
                }
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