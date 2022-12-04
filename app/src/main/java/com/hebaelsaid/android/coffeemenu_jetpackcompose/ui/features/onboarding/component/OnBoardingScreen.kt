package com.hebaelsaid.android.coffeemenu_jetpackcompose.ui.features.onboarding.component

import android.util.Log
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.rotate
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
import com.hebaelsaid.android.coffeemenu_jetpackcompose.R
import com.hebaelsaid.android.coffeemenu_jetpackcompose.data.model.uimodel.OnBoardingUiModel
import com.hebaelsaid.android.coffeemenu_jetpackcompose.ui.Screen
import com.hebaelsaid.android.coffeemenu_jetpackcompose.ui.theme.*
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

private const val TAG = "OnBoardingScreen"
@OptIn(ExperimentalPagerApi::class)
@Composable
fun OnBoardingScreen(navController: NavController) {
    val pagerState = rememberPagerState()
    val items = setupOnBoardingUiModels()
    Column(modifier = Modifier.fillMaxSize()) {
        //  OnBoardingPager(item = items, pagerState = pagerState)
        OnBoardingPager(navController,items, pagerState)
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

@OptIn(ExperimentalPagerApi::class)
@Composable
fun OnBoardingPager(
    navController: NavController,
    pages: List<OnBoardingUiModel>,
    pagerState: PagerState
) {
    HorizontalPager(count = pages.size, state = pagerState) { page ->
        SetupPageDesign(pages[page])
        PagerIndicator(items = pages, currentPage = pagerState.currentPage)
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.Transparent)
                .padding(20.dp),
            horizontalAlignment = Alignment.End,
            verticalArrangement = Arrangement.Bottom
        ) {
            SetupNextButton(navController, pagerState)
        }
    }

}

@Composable
private fun SetupPageDesign(pageData: OnBoardingUiModel) {
    Box(
        modifier = Modifier.fillMaxSize()
    ) {

        Image(
            painter = painterResource(id = pageData.image),
            contentDescription = pageData.title,
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.FillBounds
        )
        Card(
            backgroundColor = transCoffee,
            modifier = Modifier
                .fillMaxWidth()
                .height(250.dp)
                .background(Color.Transparent)
                .align(Alignment.TopStart),
            elevation = 0.dp,
            shape = RoundedCornerShape(bottomEnd = 80.dp, bottomStart = 20.dp)
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

@Composable
fun PagerIndicator(currentPage: Int, items: List<OnBoardingUiModel>) {
    Row(
        horizontalArrangement = Arrangement.SpaceBetween,
        modifier = Modifier.padding(top = 20.dp, start = 20.dp)
    ) {
        repeat(items.size) {
            Indicator(isSelected = it == currentPage, color = BrownGrey40)
        }
    }
}

@Composable
fun Indicator(isSelected: Boolean, color: Color) {
    val width = animateDpAsState(targetValue = if (isSelected) 40.dp else 10.dp)

    Box(
        modifier = Modifier
            .padding(4.dp)
            .height(10.dp)
            .width(width.value)
            .clip(CircleShape)
            .background(
                if (isSelected) color else CoffeeGrey80.copy(alpha = 0.5f)
            )
    )
}

@OptIn(ExperimentalPagerApi::class, DelicateCoroutinesApi::class)
@Composable
fun SetupNextButton(navController: NavController, pagerState: PagerState) {
    Log.d(TAG, "SetupNextButton: pagerState.currentPage: ${pagerState.currentPage} ")
    if (pagerState.currentPage != 2) {
        OutlinedButton(
            onClick = {
                GlobalScope.launch {
                    Log.d(TAG, "SetupNextButton: pagerState.currentPage on click: ${pagerState.currentPage} ")
                    pagerState.scrollToPage(
                        pagerState.currentPage + 1,
                        pageOffset = 0f
                    )
                 //   delay(500)
                }
            },
            border = BorderStroke(
                14.dp,
                BrownGrey40
            ),
            shape = RoundedCornerShape(50),
            colors = ButtonDefaults.outlinedButtonColors(contentColor = BrownGrey40),
            modifier = Modifier.size(65.dp)
        ) {
            Icon(
                painter = painterResource(id = R.drawable.ic_arrow_back),
                contentDescription = "",
                tint = BrownGrey40,
                modifier = Modifier
                    .fillMaxSize()
                    .rotate(180f)
            )
        }
    } else {
        Button(
            onClick = {
                navController.navigate(Screen.OnBoardingScreen.route + "/${Screen.HomeScreen.route}")
            },
            modifier = Modifier.fillMaxWidth(),
            colors = ButtonDefaults.buttonColors(
                backgroundColor = BrownGrey40
            ),
            contentPadding = PaddingValues(vertical = 12.dp),
            elevation = ButtonDefaults.elevation(
                defaultElevation = 0.dp
            )
        ) {
            Text(
                text = "Get Started",
                color = Color.White,
                fontSize = 16.sp
            )
        }
    }

}