package com.hebaelsaid.android.coffeemenu_jetbackcompose.ui.features.home.viewmodel

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hebaelsaid.android.coffeemenu_jetbackcompose.domain.usecase.coffeelist.GetIcedCoffeeListUseCase
import com.hebaelsaid.android.coffeemenu_jetbackcompose.ui.features.home.state.CoffeeListState
import com.hebaelsaid.android.coffeemenu_jetbackcompose.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class IcedCoffeeListViewModel @Inject constructor(
    private val getIcedCoffeeUseCase: GetIcedCoffeeListUseCase
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
                    }else{
                        _state.value = CoffeeListState(error ="Data Return With Null")
                    }
                }
                is Resource.Loading ->{
                    _state.value = CoffeeListState(isLoading = true)
                }
                is Resource.Error ->{
                    _state.value = CoffeeListState(error = resultState.message?: "un expected error occurred")
                }
            }
        }.launchIn(viewModelScope)
    }
}