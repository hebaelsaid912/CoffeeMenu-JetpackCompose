package com.hebaelsaid.android.coffeemenu_jetbackcompose.ui.features.home.component

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Divider
import androidx.compose.material.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.hebaelsaid.android.coffeemenu_jetbackcompose.ui.features.home.viewmodel.IcedCoffeeListViewModel

@Composable
fun IcedCoffeeScreen(
    navController: NavController,
    viewModel: IcedCoffeeListViewModel = hiltViewModel()
) {
    val state = viewModel.state.value
    Box(modifier = Modifier.fillMaxSize().padding(15.dp)) {
        LazyColumn(modifier = Modifier
            .fillMaxSize()
            .padding(10.dp)){
                items(state.modelItem.size){ coffeeModels ->
                    CoffeeListItem(model = state.modelItem[coffeeModels], onItemClick ={
                        // navController.navigate(Screen.CoinDetailsScreen.route + "/${coffeeModel.coin_id}")
                    } )
                    Divider()
                }
        }
        if(state.error.isNotBlank()){
            Text(
                text = state.error,
                color = MaterialTheme.colors.error,
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 20.dp)
                    .align(Alignment.Center)
            )
        }
        if(state.isLoading){
            CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
        }
    }
}
