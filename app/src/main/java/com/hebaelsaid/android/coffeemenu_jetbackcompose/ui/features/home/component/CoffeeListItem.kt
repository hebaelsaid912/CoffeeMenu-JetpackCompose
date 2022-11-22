package com.hebaelsaid.android.coffeemenu_jetbackcompose.ui.features.home.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import com.hebaelsaid.android.coffeemenu_jetbackcompose.data.model.responsemodel.CoffeeResponseModel

@Composable
fun CoffeeListItem(
    model: CoffeeResponseModel.CoffeeResponseModelItem,
    onItemClick: (CoffeeResponseModel.CoffeeResponseModelItem) -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onItemClick(model) }
            .padding(10.dp),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(
            text = model.title!!,
            style = MaterialTheme.typography.body1,
            overflow = TextOverflow.Ellipsis
        )
        Image(
            painter = rememberAsyncImagePainter(model.image),
            contentDescription = null,
            modifier = Modifier.size(128.dp)
        )

    }
    
}