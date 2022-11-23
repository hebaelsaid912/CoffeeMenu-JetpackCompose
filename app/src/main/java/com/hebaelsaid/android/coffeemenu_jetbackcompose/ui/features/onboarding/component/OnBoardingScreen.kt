package com.hebaelsaid.android.coffeemenu_jetbackcompose.ui.features.onboarding.component

import androidx.compose.runtime.Composable
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.PagerState
import com.google.accompanist.pager.rememberPagerState
import com.hebaelsaid.android.coffeemenu_jetbackcompose.R
import com.hebaelsaid.android.coffeemenu_jetbackcompose.data.model.uimodel.OnBoardingUiModel

@OptIn(ExperimentalPagerApi::class)
@Composable
fun OnBoardingScreen() {
    val pagerState = rememberPagerState()
    val items = setupOnBoardingUiModels()
    OnBoardingPager(item = items, pagerState = pagerState)
}

@Composable
private fun setupOnBoardingUiModels(): ArrayList<OnBoardingUiModel> {
    val items = ArrayList<OnBoardingUiModel>()
    items.add(
        OnBoardingUiModel(
            id = 0,
            image = R.drawable.onboarding_1,
            title = "Hmmm, Delicious coffee",
            desc = "Ingredients are easy to find. all delicious flavors can only be found in one place",
        )
    )

    items.add(
        OnBoardingUiModel(
            id = 1,
            image = R.drawable.onboarding_2,
            title = "Morning coffee",
            desc = "Take your coffee in the morning and enjoy focus and activity"
        )
    )

    items.add(
        OnBoardingUiModel(
            id = 2,
            image = R.drawable.onboarding_3,
            title = "Let’s making an ice coffee",
            desc = "Are you ready to make an iced coffee for your friends or family?",
        )
    )
    return items
}

@ExperimentalPagerApi
@Composable
private fun OnBoardingPager(item: List<OnBoardingUiModel>, pagerState: PagerState){

}