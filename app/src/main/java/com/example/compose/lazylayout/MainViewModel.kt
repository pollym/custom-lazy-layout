package com.example.compose.lazylayout

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlin.random.Random

class MainViewModel : ViewModel() {

    private val size: Int = 100
    private val maxX: Int = 5000
    private val maxY: Int = 600

    private val items: List<ListItem> = List(size) { i: Int ->
        ListItem(Random.nextInt(maxX), Random.nextInt(maxY))
    }

    val state = MutableStateFlow(State(items))

}
data class State(
    val items: List<ListItem> = emptyList()
)
