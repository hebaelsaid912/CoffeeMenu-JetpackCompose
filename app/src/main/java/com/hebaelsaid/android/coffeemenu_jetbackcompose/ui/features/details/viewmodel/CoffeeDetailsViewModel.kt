package com.hebaelsaid.android.coffeemenu_jetbackcompose.ui.features.details.viewmodel

import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hebaelsaid.android.coffeemenu_jetbackcompose.domain.usecase.coffeedetails.GetCoffeeDetailsUseCase
import com.hebaelsaid.android.coffeemenu_jetbackcompose.ui.features.details.state.CoffeeDetailsState
import com.hebaelsaid.android.coffeemenu_jetbackcompose.utils.Constant.PARAM_COFFEE_TITLE
import com.hebaelsaid.android.coffeemenu_jetbackcompose.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

private const val TAG = "CoffeeDetailsViewModel"
@HiltViewModel
class CoffeeDetailsViewModel @Inject constructor(
    private val getCoffeeDetailsUseCase: GetCoffeeDetailsUseCase,
    stateHandle: SavedStateHandle
): ViewModel(){
    private val _state  = mutableStateOf(CoffeeDetailsState())
    val state: State<CoffeeDetailsState> = _state
    init {
        Log.d(TAG, "CoffeeDetailsViewModel:init: ")
            stateHandle.get<String>(PARAM_COFFEE_TITLE)?.let { data ->
                Log.d(TAG, "CoffeeDetailsViewModel:init:stateHandle ")
                val title = data.split('-')[0]
                val type = data.split('-')[1]
                getCoffeeDetails(modelTitle = title, modelType =type )
            }
    }
    private fun getCoffeeDetails(modelTitle: String,modelType: String){
         getCoffeeDetailsUseCase(modelTitle,modelType).onEach { resultState->
             when(resultState){
                 is Resource.Success ->{
                     _state.value = CoffeeDetailsState(modelItem = resultState.data)
                 }
                 is Resource.Loading ->{
                     Log.d(TAG, "getCoffeeDetails: Resource.Loading: true")
                     _state.value = CoffeeDetailsState(isLoading = true)
                 }
                 is Resource.Error ->{
                     Log.d(TAG, "getCoffeeDetails: Resource.Error")
                     _state.value = CoffeeDetailsState(error = resultState.message?: "un expected error occurred")
                 }
             }
         }.launchIn(viewModelScope)
    }
}