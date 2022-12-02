package com.hebaelsaid.android.coffeemenu_jetbackcompose.ui.features.details.component

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.AsyncImage
import coil.request.ImageRequest
import coil.size.Scale
import com.google.accompanist.flowlayout.FlowRow
import com.hebaelsaid.android.coffeemenu_jetbackcompose.R
import com.hebaelsaid.android.coffeemenu_jetbackcompose.ui.features.details.viewmodel.CoffeeDetailsViewModel
import com.hebaelsaid.android.coffeemenu_jetbackcompose.ui.theme.Brown40
import com.hebaelsaid.android.coffeemenu_jetbackcompose.ui.theme.BrownGrey40


@Composable
fun CoffeeDetailsScreen(
    //modelItem: CoffeeResponseModel.CoffeeResponseModelItem,
    viewModel: CoffeeDetailsViewModel = hiltViewModel()
) {
    val state = viewModel.state.value
    Box(modifier = Modifier.fillMaxSize()) {
        if (state.modelItem != null) {
            Column(modifier = Modifier.fillMaxSize()) {
                AsyncImage(
                    model = ImageRequest.Builder(LocalContext.current)
                        .data(state.modelItem.image)
                        .crossfade(true)
                        .error(R.drawable.ic_image_placeholder)
                        .placeholder(R.drawable.ic_image_placeholder)
                        .scale(Scale.FILL)
                        .build(),
                    contentScale = ContentScale.FillBounds,
                    contentDescription = state.modelItem.title,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(250.dp)
                        /*.clip(
                            RoundedCornerShape(10.dp)
                        )*/
                )
                LazyColumn(
                    modifier = Modifier.fillMaxSize(),
                    contentPadding = PaddingValues(20.dp)
                ) {
                    item {
                        Text(
                            text = state.modelItem.title!!,
                            style = MaterialTheme.typography.h4,
                            fontWeight = FontWeight.ExtraBold,
                            fontSize = 20.sp,
                            color = Brown40 ,
                            modifier = Modifier.fillMaxWidth()
                        )

                        Spacer(modifier = Modifier.height(15.dp))
                        Text(
                            text = state.modelItem.description!!,
                            style = MaterialTheme.typography.body2,
                            fontWeight = FontWeight.Light,
                            color = BrownGrey40,
                            fontSize = 18.sp,
                        )
                        Spacer(modifier = Modifier.height(15.dp))
                        Text(
                            text = "ingredients",
                            color = Brown40 ,
                            style = MaterialTheme.typography.h5
                        )
                        Spacer(modifier = Modifier.height(15.dp))
                        FlowRow(
                            mainAxisSpacing = 10.dp,
                            crossAxisSpacing = 10.dp,
                            modifier = Modifier.fillMaxWidth()
                        ) {
                            if (!state.modelItem.ingredients.isNullOrEmpty()) {
                                state.modelItem.ingredients.forEach { ingredient ->
                                    CoffeeIngredientsItem(ingredient = ingredient!!)
                                }
                            }
                        }
                        Spacer(modifier = Modifier.height(15.dp))
                    }
                }
            }
        }
        if (state.error.isNotBlank()) {
            androidx.compose.material3.Text(
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
    }
}
