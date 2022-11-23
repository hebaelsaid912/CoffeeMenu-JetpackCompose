package com.hebaelsaid.android.coffeemenu_jetbackcompose.ui.features.onboarding.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.TabRow
import androidx.compose.material.TabRowDefaults
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.google.accompanist.pager.*
import com.hebaelsaid.android.coffeemenu_jetbackcompose.R
import com.hebaelsaid.android.coffeemenu_jetbackcompose.data.model.uimodel.OnBoardingUiModel
import com.hebaelsaid.android.coffeemenu_jetbackcompose.ui.theme.*

@OptIn(ExperimentalPagerApi::class)
@Composable
fun OnBoardingScreen(navController: NavController) {
    val pagerState = rememberPagerState()
    val items = setupOnBoardingUiModels()
    Column(modifier = Modifier.fillMaxSize()) {
        //  OnBoardingPager(item = items, pagerState = pagerState)
        OnBoardingPager(items, pagerState)
    }
}

@Composable
private fun setupOnBoardingUiModels(): ArrayList<OnBoardingUiModel> {
    val items = ArrayList<OnBoardingUiModel>()
    items.add(
        OnBoardingUiModel(
            id = 0,
            image = R.drawable.onboarding_1,
            title = "Hmmm, Delicious coffee",
            desc = "Ingredients are easy to find. all delicious flavors can only be found in one place Ingredients are easy to find. all delicious flavors can only be found in one place Ingredients are easy to find. all delicious flavors can only be found in one place",
        )
    )

    items.add(
        OnBoardingUiModel(
            id = 1,
            image = R.drawable.onboarding_2,
            title = "Morning coffee",
            desc = "Take your coffee in the morning and enjoy focus and activity\nTake your coffee in the morning and enjoy focus and activity Take your coffee in the morning and enjoy focus and activity"
        )
    )

    items.add(
        OnBoardingUiModel(
            id = 2,
            image = R.drawable.onboarding_3,
            title = "Let’s making an ice coffee",
            desc = "Are you ready to make an iced coffee for your friends or family? Are you ready to make an iced coffee for your friends or family? Are you ready to make an iced coffee for your friends or family?",
        )
    )
    return items
}

/*@ExperimentalPagerApi
@Composable
private fun OnBoardingPager(item: List<OnBoardingUiModel>, pagerState: PagerState) {
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
        item.forEachIndexed { index, onBoardingUiModel ->
            val color = remember {
                Animatable(CoffeeGrey80)
            }
            LaunchedEffect(key1 = pagerState.currentPage == index) {
                color.animateTo(
                    if (pagerState.currentPage == index) CoffeeGrey40 else Coffee80
                )
            }
            Tab(
                selected = pagerState.currentPage == index,
                onClick = {
                    scope.launch {
                        pagerState.animateScrollToPage(index)
                    }
                },
                text = null,
                modifier = Modifier.background(
                    color = color.value,
                    shape = RoundedCornerShape(20.dp)
                )
            )
        }
    }
}*/

@OptIn(ExperimentalPagerApi::class)
@Composable
fun OnBoardingPager(pages: List<OnBoardingUiModel>, pagerState: PagerState) {
    HorizontalPager(count = pages.size, state = pagerState) { page ->
        SetupPageDesign(pages[page],pagerState)
    }

}

@OptIn(ExperimentalPagerApi::class)
@Composable
private fun SetupPageDesign(pageData: OnBoardingUiModel, pagerState: PagerState) {
    Box(
        modifier = Modifier.fillMaxSize()
    ) {

        Image(
            painter = painterResource(id = pageData.image),
            contentDescription = pageData.title,
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.FillBounds
        )
        TabRow(
            selectedTabIndex = 0,
            backgroundColor = transCoffee,
            modifier = Modifier
                .fillMaxWidth()
                .height(250.dp)
                .background(Color.Transparent)
                .clip(RoundedCornerShape(bottomEnd = 80.dp, bottomStart = 20.dp))
                .align(Alignment.TopStart),
            indicator = { tabPositions ->
                TabRowDefaults.Indicator(
                    Modifier
                        .background(Color.Transparent)
                        .pagerTabIndicatorOffset(pagerState = pagerState, tabPositions = tabPositions)
                        .width(0.dp)
                        .height(0.dp)
                )
            }
        ) {
            Column(
                modifier = Modifier.fillMaxSize()
            ) {
                Text(
                    text = pageData.title,
                    color = Brown40,
                    fontSize = 25.sp,
                    fontWeight = FontWeight.Bold,
                    fontStyle = FontStyle.Italic,
                    style = MaterialTheme.typography.h2,
                    modifier = Modifier.padding(top = 60.dp, start = 20.dp),
                    overflow = TextOverflow.Ellipsis
                )
                Text(
                    text = pageData.desc,
                    color = BrownGrey40,
                    fontSize = 14.sp,
                    fontWeight = FontWeight.W400,
                    style = MaterialTheme.typography.h6,
                    modifier = Modifier.padding(
                        top = 10.dp,
                        start = 20.dp,
                        bottom = 10.dp,
                        end = 20.dp
                    ),
                    overflow = TextOverflow.Ellipsis
                )
            }
        }
    }
}