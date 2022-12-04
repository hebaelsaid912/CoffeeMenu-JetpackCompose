package com.hebaelsaid.android.coffeemenu_jetpackcompose.ui.features.details.component

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.hebaelsaid.android.coffeemenu_jetpackcompose.ui.theme.Coffee80

@Composable
fun CoffeeIngredientsItem(
    ingredient: String?
) {
    Box(
        modifier = Modifier
            .border(
                width = 1.dp,
                color = Coffee80,
                shape = RoundedCornerShape(100.dp)
            )
            .padding(10.dp)

    ) {
        Text(
            text = ingredient!!,
            color = Coffee80,
            textAlign = TextAlign.Center,
            style = MaterialTheme.typography.body2
            )
    }
}