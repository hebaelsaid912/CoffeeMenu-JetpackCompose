package com.hebaelsaid.android.coffeemenu_jetbackcompose.ui.features.details.component

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import coil.size.Scale
import com.google.accompanist.flowlayout.FlowRow
import com.hebaelsaid.android.coffeemenu_jetbackcompose.R
import com.hebaelsaid.android.coffeemenu_jetbackcompose.data.model.responsemodel.CoffeeResponseModel

@Composable
fun CoffeeDetailsScreenContent(
    model: CoffeeResponseModel.CoffeeResponseModelItem? = null
) {

    Box(modifier = Modifier.fillMaxSize()) {
        AsyncImage(
            model = ImageRequest.Builder(LocalContext.current)
                .data(model!!.image)
                .crossfade(true)
                .error(R.drawable.ic_image_placeholder)
                .placeholder(R.drawable.ic_image_placeholder)
                .scale(Scale.FILL)
                .build(),
            contentScale = ContentScale.FillBounds,
            contentDescription = model.title,
            modifier = Modifier
                .fillMaxWidth()
                .height(150.dp)
                .clip(
                    RoundedCornerShape(10.dp)
                )
        )
        LazyColumn(
            modifier = Modifier.fillMaxSize(),
            contentPadding = PaddingValues(20.dp)
        ) {
            item {
                Text(
                    text = model.title!!,
                    style = MaterialTheme.typography.h4,
                    fontWeight = FontWeight.ExtraBold,
                    fontSize = 20.sp,
                    modifier = Modifier.fillMaxWidth()
                )

                Spacer(modifier = Modifier.height(15.dp))
                Text(
                    text = model.description!!,
                    style = MaterialTheme.typography.body2,
                    fontWeight = FontWeight.Light,
                    fontSize = 20.sp,
                )
                Spacer(modifier = Modifier.height(15.dp))
                Text(
                    text = "ingredients",
                    style = MaterialTheme.typography.h5
                )
                Spacer(modifier = Modifier.height(15.dp))
                FlowRow(
                    mainAxisSpacing = 10.dp,
                    crossAxisSpacing = 10.dp,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    if (!model.ingredients.isNullOrEmpty()) {
                        model.ingredients.forEach { ingredient ->
                            CoffeeIngredientsItem(ingredient = ingredient!!)
                        }
                    }
                }
                Spacer(modifier = Modifier.height(15.dp))
                Text(
                    text = "Team members",
                    style = MaterialTheme.typography.h5
                )
                Spacer(modifier = Modifier.height(15.dp))
            }
        }
    }
}