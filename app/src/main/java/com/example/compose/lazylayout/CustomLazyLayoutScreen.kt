package com.example.compose.lazylayout

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.compose.lazylayout.layout.CustomLazyLayout
import com.example.compose.lazylayout.layout.rememberLazyLayoutState

@Composable
fun CustomLazyLayoutScreen(state: State) {
    val lazyLayoutState = rememberLazyLayoutState()

    Column(
        modifier = Modifier
            .fillMaxSize(),
        verticalArrangement = Arrangement.spacedBy(4.dp)
    ) {

        CustomLazyLayout(
            state = lazyLayoutState,
            modifier = Modifier.fillMaxSize(),
        ) {
            items(state.items) { item ->
                Text(
                    text = "X: ${item.x}\nY: ${item.y}",
                    color = Color.White,
                    modifier = Modifier
                        .clip(RoundedCornerShape(24.dp))
                        .background(MaterialTheme.colorScheme.primary)
                        .padding(12.dp)
                )
            }
        }
    }
}

