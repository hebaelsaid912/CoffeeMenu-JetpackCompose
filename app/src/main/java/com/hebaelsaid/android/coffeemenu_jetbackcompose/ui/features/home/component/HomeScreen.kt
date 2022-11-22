package com.hebaelsaid.android.coffeemenu_jetbackcompose.ui.features.home.component

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
import com.google.accompanist.pager.*
import com.hebaelsaid.android.coffeemenu_jetbackcompose.ui.theme.Purple40
import com.hebaelsaid.android.coffeemenu_jetbackcompose.ui.theme.PurpleGrey80
import kotlinx.coroutines.launch

@OptIn(ExperimentalPagerApi::class)
@Composable
fun Tabs(tabs: List<TabItem>, pagerState: PagerState) {
    val scope = rememberCoroutineScope()
    TabRow(
        selectedTabIndex = pagerState.currentPage,
        backgroundColor = Purple40,
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
                Animatable(PurpleGrey80)
            }
            LaunchedEffect(key1 = pagerState.currentPage == index) {
                color.animateTo(
                    if (pagerState.currentPage == index) Color.White else Purple40
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
                                color = Purple40,
                                fontSize = 20.sp,
                                fontWeight = FontWeight.Bold
                            )
                        else
                            TextStyle(color = PurpleGrey80, fontSize = 18.sp)
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

@OptIn(ExperimentalPagerApi::class)
@Composable
fun TabsContent(tabs: List<TabItem>, pagerState: PagerState) {
    HorizontalPager(count = tabs.size, state = pagerState) { page ->
        tabs[page].screen()
    }
}