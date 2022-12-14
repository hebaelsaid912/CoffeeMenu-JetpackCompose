package com.hebaelsaid.android.coffeemenu_jetpackcompose.ui.features.home.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
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
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.hebaelsaid.android.coffeemenu_jetpackcompose.R
import com.hebaelsaid.android.coffeemenu_jetpackcompose.ui.Screen
import com.hebaelsaid.android.coffeemenu_jetpackcompose.ui.features.home.state.CoffeeListState
import com.hebaelsaid.android.coffeemenu_jetpackcompose.ui.features.home.viewmodel.IcedCoffeeListViewModel
import com.hebaelsaid.android.coffeemenu_jetpackcompose.utils.Constant.ICED_COFFEE_TYPE
import com.hebaelsaid.android.coffeemenu_jetpackcompose.utils.connectivityStatus

@Composable
fun IcedCoffeeScreen(
    navController: NavController,
    viewModel: IcedCoffeeListViewModel = hiltViewModel()
) {
    val state = viewModel.state.value
    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(15.dp)
    ) {
        if (connectivityStatus()) {
            SetupCoffeeListUi(state, navController)
            if (state.error.isNotBlank()) {
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
            if (state.isLoading) {
                CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
            }
        } else {
            if (state.modelItem.isNotEmpty()) {
                SetupCoffeeListUi(state, navController)
            } else {
                SetupOfflineUi()
            }
        }
    }
}

@Composable
private fun SetupCoffeeListUi(
    state: CoffeeListState,
    navController: NavController
) {
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(10.dp)
    ) {
        items(state.modelItem.size) { coffeeModels ->
            CoffeeListItem(
                model = state.modelItem[coffeeModels],
                onItemClick = { model ->
                    navController.navigate(Screen.OnBoardingScreen.route + "/${Screen.CoffeeDetailsScreen.route}" + "/${model.id}-$ICED_COFFEE_TYPE")
                })
            Divider()
        }
    }
}

@Composable
private fun SetupOfflineUi() {
    Column(
        Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Image(
            painter = painterResource(id = R.drawable.network),
            contentDescription = "no internet connection"
        )
        Text(text = "No Internet Connection! or cashing data")
        Text(text = "please, open internet at least one time ", fontSize = 14.sp)
    }
}
