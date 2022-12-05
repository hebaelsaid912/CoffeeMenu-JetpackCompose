package com.hebaelsaid.android.coffeemenu_jetpackcompose.domain.usecase.coffeedetails

import com.hebaelsaid.android.coffeemenu_jetpackcompose.data.model.responsemodel.CoffeeResponseModel
import com.hebaelsaid.android.coffeemenu_jetpackcompose.data.repository.HotCoffeeDBRepoImpl
import com.hebaelsaid.android.coffeemenu_jetpackcompose.data.repository.IcedCoffeeDBRepoImpl
import com.hebaelsaid.android.coffeemenu_jetpackcompose.utils.Constant.HOT_COFFEE_TYPE
import com.hebaelsaid.android.coffeemenu_jetpackcompose.utils.Constant.ICED_COFFEE_TYPE
import com.hebaelsaid.android.coffeemenu_jetpackcompose.utils.Resource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.withContext
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class GetCoffeeDetailsUseCase @Inject constructor(
    private val hotCoffeeDBRepositoryImpl: HotCoffeeDBRepoImpl,
    private val icedCoffeeDBRepositoryImpl: IcedCoffeeDBRepoImpl
) {
    operator fun invoke(id: Int, type: String) =
        flow<Resource<CoffeeResponseModel.CoffeeResponseModelItem>> {
            try {
                emit(Resource.Loading<CoffeeResponseModel.CoffeeResponseModelItem>())
                when (type) {
                    HOT_COFFEE_TYPE -> {
                        val coffeeModel = withContext(Dispatchers.Default) {
                           hotCoffeeDBRepositoryImpl.getHotCoffeeDetailsByName(id = id)
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
                    ICED_COFFEE_TYPE -> {
                        val coffeeModel = withContext(Dispatchers.Default) {
                           icedCoffeeDBRepositoryImpl.getIcedCoffeeDetailsByName(id)
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