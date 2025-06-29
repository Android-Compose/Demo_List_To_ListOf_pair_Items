package com.example.demo.ui.theme.screen

import android.util.Log
import androidx.lifecycle.ViewModel
import com.example.demo.data.DataSource
import com.example.demo.data.Item
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update


class DemoViewModel: ViewModel() {

    private val _uiState = MutableStateFlow(DemoUiState(loading = true))
    val uiState: StateFlow<DemoUiState> = _uiState.asStateFlow()

    init {
        getData()
    }

    private fun getData() {
        _uiState.value.success = true
        if(_uiState.value.success) {
            _uiState.update { currentState ->
                currentState.copy(
                    items = DataSource.data,
                    loading = false
                )
            }
            Log.d("data", uiState.value.items.toString())
        } else {
            _uiState.update { currentState ->
                currentState.copy(error = "Failed to load data", loading = false)
            }
        }
    }
}

data class DemoUiState(
    val items: List<Pair<Int, List<Item>>> = emptyList(),
    val loading: Boolean = false,
    var success: Boolean = false,
    val error: String = ""
)