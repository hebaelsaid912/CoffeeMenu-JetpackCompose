package com.hebaelsaid.android.coffeemenu_jetbackcompose.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.hebaelsaid.android.coffeemenu_jetbackcompose.ui.theme.CoffeeMenuJetbackComposeTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            CoffeeMenuJetbackComposeTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    SetupAppRouteNavigation()
                }
            }
        }
    }

    @Composable
    private fun SetupAppRouteNavigation() {
        val navController = rememberNavController()
        NavHost(navController = navController, startDestination = Screen.CoffeeListScreen.route) {
            composable(route = Screen.CoffeeListScreen.route + "/{hot}") {
                // CoffeeListScreen(navController = navController)
                TODO("call hot CoffeeListScreen")
            }
            composable(route = Screen.CoffeeListScreen.route + "/{iced}") {
                //CoffeeDetailsScreen()
                TODO("call iced CoffeeListScreen")
            }
            composable(route = Screen.CoffeeDetailsScreen.route) {
                //CoffeeDetailsScreen()
                TODO("call CoffeeDetailsScreen")
            }
        }
    }
}