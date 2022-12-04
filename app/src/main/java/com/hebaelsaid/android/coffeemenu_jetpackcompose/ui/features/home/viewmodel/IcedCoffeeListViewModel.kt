package com.hebaelsaid.android.coffeemenu_jetpackcompose.ui.features.home.viewmodel

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hebaelsaid.android.coffeemenu_jetpackcompose.data.local.database.CoffeeMenuDatabase
import com.hebaelsaid.android.coffeemenu_jetpackcompose.data.local.entities.IcedCoffeeDetailsItem
import com.hebaelsaid.android.coffeemenu_jetpackcompose.data.model.responsemodel.CoffeeResponseModel
import com.hebaelsaid.android.coffeemenu_jetpackcompose.domain.usecase.coffeelist.GetIcedCoffeeListUseCase
import com.hebaelsaid.android.coffeemenu_jetpackcompose.ui.features.home.state.CoffeeListState
import com.hebaelsaid.android.coffeemenu_jetpackcompose.utils.Constant.ICED_COFFEE_TYPE
import com.hebaelsaid.android.coffeemenu_jetpackcompose.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class IcedCoffeeListViewModel @Inject constructor(
    private val getIcedCoffeeUseCase: GetIcedCoffeeListUseCase,
    private val coffeeMenuDatabase: CoffeeMenuDatabase
): ViewModel(){
    private val _state  = mutableStateOf(CoffeeListState())
    val state: State<CoffeeListState> = _state

    init {
        getIcedCoffeeList()
    }
    private fun getIcedCoffeeList(){
        getIcedCoffeeUseCase().onEach { resultState->
            when(resultState){
                is Resource.Success ->{
                    if(!resultState.data.isNullOrEmpty()) {
                        _state.value = CoffeeListState(modelItem = resultState.data)
                        insertIcedListItemsIntoDB(resultState.data)
                    }else{
                        _state.value = CoffeeListState(error ="Data Return With Null")
                    }
                }
                is Resource.Loading ->{
                    _state.value = CoffeeListState(isLoading = true)
                }
                is Resource.Error ->{
                    if(resultState.message!!.contains("internet")){
                        val coffeeList = withContext(Dispatchers.Default){ coffeeMenuDatabase.coffeeMenuDao().getAllIcedCoffeeList}
                        _state.value = CoffeeListState(modelItem = getIcedCoffeeListItemsFromDB(coffeeList))
                    }else {
                        _state.value = CoffeeListState(
                            error = resultState.message ?: "un expected error occurred"
                        )
                    }
                }
            }
        }.launchIn(viewModelScope)
    }

    private fun getIcedCoffeeListItemsFromDB(coffeeList: List<IcedCoffeeDetailsItem>) : CoffeeResponseModel{
        // val list = ArrayList<CoffeeResponseModel.CoffeeResponseModelItem>()
        val list = CoffeeResponseModel()
        for (item in coffeeList) {
            list.add(
                CoffeeResponseModel.CoffeeResponseModelItem(
                    id = item.item_id,
                    description = item.description,
                    image = item.image,
                    ingredients = item.ingredients,
                    title = item.title
                )
            )
        }
        list.reverse()
        return list
    }

    private suspend fun insertIcedListItemsIntoDB(data: CoffeeResponseModel) {
        clearIcedCoffeeTable()
        for (item in data) {
            coffeeMenuDatabase.coffeeMenuDao().insertIcedCoffeeMenuItem(
                IcedCoffeeDetailsItem(
                    description = item.description,
                    item_id = item.id,
                    image = item.image,
                    title = item.title,
                    ingredients = item.ingredients,
                    type = ICED_COFFEE_TYPE
                )
            )
        }
    }

    private suspend fun clearIcedCoffeeTable() {
        coffeeMenuDatabase.coffeeMenuDao().clearIcedCoffeeMenuDatabase()
    }
}