package com.hebaelsaid.android.coffeemenu_jetbackcompose.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.rememberPagerState
import com.hebaelsaid.android.coffeemenu_jetbackcompose.ui.features.home.component.TabItem
import com.hebaelsaid.android.coffeemenu_jetbackcompose.ui.features.home.component.Tabs
import com.hebaelsaid.android.coffeemenu_jetbackcompose.ui.features.home.component.TabsContent
import com.hebaelsaid.android.coffeemenu_jetbackcompose.ui.theme.CoffeeMenuJetbackComposeTheme
import com.hebaelsaid.android.coffeemenu_jetbackcompose.ui.theme.Brown40

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
                    //SetupAppRouteNavigation()
                    MainContent()
                }
            }
        }
    }

    @OptIn(ExperimentalPagerApi::class)
    @Preview
    @Composable
    fun MainContent() {
        val list = listOf(TabItem.HotCoffee,TabItem.IcedCoffee)
        val pagerState = rememberPagerState()
        Column(modifier = Modifier.fillMaxSize()) {
            Text(
                text = "Coffee Categories",
                color = Brown40,
                fontSize = 25.sp,
                modifier = Modifier.padding(10.dp,20.dp),
                fontWeight = FontWeight.Bold
                )
            Tabs(tabs = list, pagerState = pagerState)
            TabsContent(tabs = list, pagerState = pagerState)
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