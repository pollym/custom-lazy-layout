package com.example.compose.lazylayout.layout

import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.gestures.ScrollableDefaults
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.lazy.layout.LazyLayout
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clipToBounds
import androidx.compose.ui.layout.Placeable
import androidx.compose.ui.unit.Constraints
import com.example.compose.lazylayout.ListItem

@Composable
fun CustomLazyLayout(
    modifier: Modifier = Modifier,
    state: LazyLayoutState = rememberLazyLayoutState(),
    content: CustomLazyListScope.() -> Unit
) {
    val itemProvider = rememberItemProvider(content)

    LazyLayout(
        modifier = modifier
            .clipToBounds()
            .scrollable(
                orientation = Orientation.Horizontal,
                flingBehavior = ScrollableDefaults.flingBehavior(),
                overscrollEffect = ScrollableDefaults.overscrollEffect(),
                state = state
            ),
        itemProvider = itemProvider,
    ) { constraints ->
        val boundaries = state.getBoundaries(constraints)
        val indexes = itemProvider.getItemIndexesInRange(boundaries)

        val indexesWithPlaceables = indexes.associateWith {
            measure(it, Constraints())
        }

        layout(constraints.maxWidth, constraints.maxHeight) {
            indexesWithPlaceables.forEach { (index, placeables) ->
                val item = itemProvider.getItem(index)
                item?.let { placeItem(state, item, placeables) }
            }
        }
    }
}

private fun Placeable.PlacementScope.placeItem(state: LazyLayoutState, listItem: ListItem, placeables: List<Placeable>) {
    val xPosition = listItem.x - state.offsetState.value.x
    val yPosition = listItem.y - state.offsetState.value.y

    placeables.forEach { placeable ->
        placeable.placeRelative(
            xPosition,
            yPosition
        )
    }
}
