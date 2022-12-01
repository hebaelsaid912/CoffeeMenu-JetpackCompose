package com.hebaelsaid.android.coffeemenu_jetbackcompose.ui

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.hebaelsaid.android.coffeemenu_jetbackcompose.ui.features.details.component.CoffeeDetailsScreen
import com.hebaelsaid.android.coffeemenu_jetbackcompose.ui.features.home.component.*
import com.hebaelsaid.android.coffeemenu_jetbackcompose.ui.features.onboarding.component.OnBoardingScreen
import com.hebaelsaid.android.coffeemenu_jetbackcompose.ui.theme.CoffeeMenuJetbackComposeTheme
import com.hebaelsaid.android.coffeemenu_jetbackcompose.utils.ConnectionState
import com.hebaelsaid.android.coffeemenu_jetbackcompose.utils.currentConnectivityState
import com.hebaelsaid.android.coffeemenu_jetbackcompose.utils.observeConnectivityAsFlow
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.ExperimentalCoroutinesApi

private const val TAG = "MainActivity"
@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalCoroutinesApi::class)
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
                    ConnectivityStatus()
                }
            }
        }
    }

    @Composable
    private fun SetupAppRouteNavigation() {
        val navController = rememberNavController()
        NavHost(navController = navController, startDestination = Screen.OnBoardingScreen.route) {
            composable(route = Screen.OnBoardingScreen.route ) {
                Log.d(TAG, "SetupAppRouteNavigation: OnBoardingScreen")
                OnBoardingScreen(navController = navController)
            }
            composable(route = Screen.OnBoardingScreen.route + "/${Screen.HomeScreen.route}" ) {
                Log.d(TAG, "SetupAppRouteNavigation: HomeScreen")
                HomeScreen(navController)
            }
            composable(route = Screen.OnBoardingScreen.route + "/${Screen.CoffeeDetailsScreen.route}") { navBackStackEntry ->
                Log.d(TAG, "SetupAppRouteNavigation: CoffeeDetailsScreen")
                CoffeeDetailsScreen()
            }
            
        }
    }
    @ExperimentalCoroutinesApi
    @Composable
    fun connectivityState(): State<ConnectionState> {
        val context = LocalContext.current
        // Creates a State<ConnectionState> with current connectivity state as initial value
        return produceState(initialValue = context.currentConnectivityState) {
            // In a coroutine, can make suspend calls
            context.observeConnectivityAsFlow().collect { value = it }
        }
    }
    @ExperimentalCoroutinesApi
    @Composable
    fun ConnectivityStatus() {
        // This will cause re-composition on every network state change
        val connection by connectivityState()

        val isConnected = connection === ConnectionState.Available

        if (!isConnected) {
           /* Snackbar(
                action = {
                    Button(onClick = {}) {
                        Text("check internet")
                    }
                },
                modifier = Modifier.fillMaxWidth().padding(8.dp).height(20.dp).background(Color.Red)
            ) { Text(text = "You are offline") }*/
        } else {
           /* Snackbar(
                modifier = Modifier.fillMaxWidth().padding(8.dp).height(20.dp).background(Color.Green)
            ) { Text(text = "Back online") }*/
        }
    }
}
