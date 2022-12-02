package com.hebaelsaid.android.coffeemenu_jetbackcompose.ui.features.home.viewmodel

import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hebaelsaid.android.coffeemenu_jetbackcompose.data.local.database.CoffeeMenuDatabase
import com.hebaelsaid.android.coffeemenu_jetbackcompose.data.local.entities.HotCoffeeDetailsItem
import com.hebaelsaid.android.coffeemenu_jetbackcompose.data.model.responsemodel.CoffeeResponseModel
import com.hebaelsaid.android.coffeemenu_jetbackcompose.domain.usecase.coffeelist.GetHotCoffeeListUseCase
import com.hebaelsaid.android.coffeemenu_jetbackcompose.ui.features.home.state.CoffeeListState
import com.hebaelsaid.android.coffeemenu_jetbackcompose.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

private const val TAG = "HotCoffeeListViewModel"
@HiltViewModel
class HotCoffeeListViewModel @Inject constructor(
    private val getHotCoffeeUseCase: GetHotCoffeeListUseCase,
    private val coffeeMenuDatabase: CoffeeMenuDatabase
): ViewModel(){
    private val _state  = mutableStateOf(CoffeeListState())
    val state: State<CoffeeListState> = _state

    init {
        getHotCoffeeList()
    }
    private fun getHotCoffeeList(){
        getHotCoffeeUseCase().onEach { resultState->
            when(resultState){
                is Resource.Success ->{
                    if(!resultState.data.isNullOrEmpty()) {
                        _state.value = CoffeeListState(modelItem = resultState.data)
                        insertHotListItemsIntoDB(resultState.data)
                    }else{
                        _state.value = CoffeeListState(error ="Data Return With Null")
                    }
                }
                is Resource.Loading ->{
                    Log.d(TAG, "getHotCoffeeList: Resource.Loading: true")
                    _state.value = CoffeeListState(isLoading = true)
                }
                is Resource.Error ->{
                    Log.d(TAG, "getHotCoffeeList: Resource.Error")
                    _state.value = CoffeeListState(error = resultState.message?: "un expected error occurred")
                }
            }
        }.launchIn(viewModelScope)
    }

    private suspend fun insertHotListItemsIntoDB(data: CoffeeResponseModel) {
        clearHotCoffeeTable()
        for (item in data) {
            coffeeMenuDatabase.coffeeMenuDao().insertHotCoffeeMenuItem(
                    HotCoffeeDetailsItem(
                        description = item.description,
                        item_id = item.id,
                        image = item.image,
                        title = item.title,
                        ingredients = item.ingredients,
                        type = "hot"
                    )
            )
        }
    }

    private suspend fun clearHotCoffeeTable() {
        coffeeMenuDatabase.coffeeMenuDao().clearHotCoffeeMenuDatabase()
    }
}