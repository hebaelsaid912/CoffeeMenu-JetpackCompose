package com.hebaelsaid.android.coffeemenu_jetpackcompose.ui.features.home.component

import androidx.compose.animation.Animatable
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Tab
import androidx.compose.material.TabRow
import androidx.compose.material.TabRowDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.google.accompanist.pager.*
import com.hebaelsaid.android.coffeemenu_jetpackcompose.ui.theme.Brown40
import com.hebaelsaid.android.coffeemenu_jetpackcompose.ui.theme.BrownGrey40
import com.hebaelsaid.android.coffeemenu_jetpackcompose.ui.theme.Coffee80
import com.hebaelsaid.android.coffeemenu_jetpackcompose.ui.theme.CoffeeGrey80
import kotlinx.coroutines.launch

@Composable
fun HomeScreen(navController: NavController) {
    MainContent(navController)
}

@Composable
fun MainContent(navController: NavController) {
    val list = listOf(TabItem.HotCoffee,TabItem.IcedCoffee)
    val pagerState = rememberPagerState()
    Column(modifier = Modifier.fillMaxSize()) {
        Text(
            text = "Coffee Menu",
            color = Brown40,
            fontSize = 25.sp,
            modifier = Modifier.padding(10.dp,20.dp),
            fontWeight = FontWeight.Bold
        )
        Tabs(tabs = list, pagerState = pagerState)
        TabsContent(navController,tabs = list, pagerState = pagerState)
    }
}
@Composable
private fun Tabs(tabs: List<TabItem>, pagerState: PagerState) {
    val scope = rememberCoroutineScope()
    TabRow(
        selectedTabIndex = pagerState.currentPage,
        backgroundColor = Coffee80,
        modifier = Modifier
            .padding(10.dp)
            .background(Color.Transparent)
            .height(40.dp)
            .clip(RoundedCornerShape(20.dp)),
        indicator = { tabPositions ->
            TabRowDefaults.Indicator(
                Modifier
                    .pagerTabIndicatorOffset(pagerState = pagerState, tabPositions = tabPositions)
                    .width(0.dp)
                    .height(0.dp)
            )
        }
    ) {
        tabs.forEachIndexed { index, tabItem ->
            val color = remember {
                Animatable(CoffeeGrey80)
            }
            LaunchedEffect(key1 = pagerState.currentPage == index) {
                color.animateTo(
                    if (pagerState.currentPage == index) Color.White else Coffee80
                )
            }
            Tab(
                selected = pagerState.currentPage == index,
                onClick = {
                    scope.launch {
                        pagerState.animateScrollToPage(index)
                    }
                },
                text = {
                    Text(
                       textAlign = TextAlign.Center,
                        text = tabItem.title,
                        style = if (pagerState.currentPage == index)
                            TextStyle(
                                color = Brown40,
                                fontSize = 20.sp,
                                fontWeight = FontWeight.Bold
                            )
                        else
                            TextStyle(color = BrownGrey40, fontSize = 18.sp)
                    )
                },
                modifier = Modifier.background(
                    color = color.value,
                    shape = RoundedCornerShape(20.dp)
                )/*,
                icon = {
                    // Icon(imageVector = tabItem.icon!!, contentDescription = null)
                }*/
            )
        }
    }

}

@Composable
private fun TabsContent(navController: NavController, tabs: List<TabItem>, pagerState: PagerState) {
    HorizontalPager(count = tabs.size, state = pagerState) { page ->
        when(tabs[page].title){
            "Hot" -> {
                HotCoffeeScreen(navController = navController)
            }
            "Iced" -> {
                IcedCoffeeScreen(navController = navController)
            }
        }
    }
}
