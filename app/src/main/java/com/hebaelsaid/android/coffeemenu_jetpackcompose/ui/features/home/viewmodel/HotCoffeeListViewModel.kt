package com.hebaelsaid.android.coffeemenu_jetpackcompose.ui.features.home.viewmodel

import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hebaelsaid.android.coffeemenu_jetpackcompose.data.local.database.CoffeeMenuDatabase
import com.hebaelsaid.android.coffeemenu_jetpackcompose.data.local.entities.HotCoffeeDetailsItem
import com.hebaelsaid.android.coffeemenu_jetpackcompose.data.model.responsemodel.CoffeeResponseModel
import com.hebaelsaid.android.coffeemenu_jetpackcompose.domain.usecase.coffeelist.GetHotCoffeeListUseCase
import com.hebaelsaid.android.coffeemenu_jetpackcompose.ui.features.home.state.CoffeeListState
import com.hebaelsaid.android.coffeemenu_jetpackcompose.utils.Constant.HOT_COFFEE_TYPE
import com.hebaelsaid.android.coffeemenu_jetpackcompose.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.withContext
import javax.inject.Inject

private const val TAG = "HotCoffeeListViewModel"

@HiltViewModel
class HotCoffeeListViewModel @Inject constructor(
    private val getHotCoffeeUseCase: GetHotCoffeeListUseCase,
    private val coffeeMenuDatabase: CoffeeMenuDatabase
) : ViewModel() {
    private val _state = mutableStateOf(CoffeeListState())
    val state: State<CoffeeListState> = _state

    init {
        getHotCoffeeList()
    }

    private fun getHotCoffeeList() {
        getHotCoffeeUseCase().onEach { resultState ->
            when (resultState) {
                is Resource.Success -> {
                    _state.value = CoffeeListState(modelItem = resultState.data!!)
                    insertHotListItemsIntoDB(resultState.data)
                }
                is Resource.Loading -> {
                    Log.d(TAG, "getHotCoffeeList: Resource.Loading: true")
                    _state.value = CoffeeListState(isLoading = true)
                }
                is Resource.Error -> {
                    Log.d(TAG, "getHotCoffeeList: Resource.Error")
                    if (resultState.message!!.contains("internet")) {
                        val coffeeList =
                            withContext(Dispatchers.Default) { coffeeMenuDatabase.coffeeMenuDao().getHotAllCoffeeList }
                        _state.value =
                            CoffeeListState(modelItem = getHotCoffeeListItemsFromDB(coffeeList))
                    } else {
                        _state.value = CoffeeListState(
                            error = resultState.message
                        )
                    }
                }
            }
        }.launchIn(viewModelScope)
    }

    private fun getHotCoffeeListItemsFromDB(coffeeList: List<HotCoffeeDetailsItem>): CoffeeResponseModel {
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
                    type = HOT_COFFEE_TYPE
                )
            )
        }
    }

    private suspend fun clearHotCoffeeTable() {
        coffeeMenuDatabase.coffeeMenuDao().clearHotCoffeeMenuDatabase()
    }
}