package com.hebaelsaid.android.coffeemenu_jetbackcompose.ui.features.home.component

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.hebaelsaid.android.coffeemenu_jetbackcompose.R
import coil.request.ImageRequest
import coil.size.Scale
import com.hebaelsaid.android.coffeemenu_jetbackcompose.data.model.responsemodel.CoffeeResponseModel

@Composable
fun CoffeeListItem(
    model: CoffeeResponseModel.CoffeeResponseModelItem,
    onItemClick: (CoffeeResponseModel.CoffeeResponseModelItem) -> Unit
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onItemClick(model) }
            .padding(bottom = 10.dp)
    ) {
        AsyncImage(
            model = ImageRequest.Builder(LocalContext.current)
                .data(model.image)
                .crossfade(true)
                .error(R.drawable.ic_image_placeholder)
                .placeholder(R.drawable.ic_image_placeholder)
                .scale(Scale.FILL)
                .build(),
            contentScale = ContentScale.FillBounds,
            contentDescription = model.title,
            modifier =  Modifier.fillMaxWidth().height(150.dp).clip(RoundedCornerShape(10.dp)
            )
        )
        Text(
            text = model.title!!,
            style = MaterialTheme.typography.body1,
            overflow = TextOverflow.Ellipsis,
            modifier = Modifier.padding(10.dp),
            color = Color.White
        )
    }
    
}