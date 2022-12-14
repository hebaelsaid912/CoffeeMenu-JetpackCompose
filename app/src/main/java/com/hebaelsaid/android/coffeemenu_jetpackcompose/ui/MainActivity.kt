package com.hebaelsaid.android.coffeemenu_jetpackcompose.ui

import android.os.Bundle
import android.util.Log
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
import com.hebaelsaid.android.coffeemenu_jetpackcompose.ui.features.details.component.CoffeeDetailsScreen
import com.hebaelsaid.android.coffeemenu_jetpackcompose.ui.features.home.component.HomeScreen
import com.hebaelsaid.android.coffeemenu_jetpackcompose.ui.features.onboarding.component.OnBoardingScreen
import com.hebaelsaid.android.coffeemenu_jetpackcompose.ui.theme.CoffeeMenuJetbackComposeTheme
import com.hebaelsaid.android.coffeemenu_jetpackcompose.utils.Constant.PARAM_COFFEE_TITLE
import dagger.hilt.android.AndroidEntryPoint

private const val TAG = "MainActivity"

@AndroidEntryPoint
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
        NavHost(navController = navController, startDestination = Screen.OnBoardingScreen.route) {
            composable(route = Screen.OnBoardingScreen.route) {
                Log.d(TAG, "SetupAppRouteNavigation: OnBoardingScreen")
                OnBoardingScreen(navController = navController)
            }
            composable(route = Screen.OnBoardingScreen.route + "/${Screen.HomeScreen.route}") {
                Log.d(TAG, "SetupAppRouteNavigation: HomeScreen")
                HomeScreen(navController)
            }
            composable(route = Screen.OnBoardingScreen.route + "/${Screen.CoffeeDetailsScreen.route}" + "/{$PARAM_COFFEE_TITLE}"/*+ "/{$PARAM_COFFEE_TYPE}"*/) { navBackStackEntry ->
                Log.d(TAG, "SetupAppRouteNavigation: CoffeeDetailsScreen")
                CoffeeDetailsScreen()
            }
        }
    }
}
