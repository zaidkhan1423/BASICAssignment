package com.zaid.basicassignment.feature_home.presentation.home_screen

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.zaid.basicassignment.feature_home.domain.repository.HomeRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject
import com.zaid.basicassignment.feature_home.presentation.data.toLocal

@HiltViewModel
class HomeScreenViewModel @Inject constructor(
    private val repository: HomeRepository
) : ViewModel() {

    private val _homeUiState = MutableStateFlow(HomeUiState())
    val homeUiState = _homeUiState.asStateFlow()

    init {
        getData()
    }

    private fun getData() = viewModelScope.launch(Dispatchers.IO) {
        _homeUiState.update {
            it.copy(
                loading = true,
                snackBarMessage = null
            )
        }

        try {
            val response = repository.getData()

            if (response.isSuccessful || response.body() != null) {
                Log.d("HomeVM", "Success ${response.body()}")
                _homeUiState.update { uiState ->
                    uiState.copy(
                        loading = false,
                        videoResponse = response.body()!!.toLocal(),
                        snackBarMessage = "Data Fetch Successfully"
                    )
                }
            } else {
                _homeUiState.update { uiState ->
                    Log.d("HomeVM", "Error Code:${response.code()} Message:${response.message()}")
                    uiState.copy(
                        loading = false, snackBarMessage = response.message()
                    )
                }
            }
        } catch (e: Exception) {
            _homeUiState.update { uiState ->
                Log.d("HomeVM", "Error ${e.message}")
                uiState.copy(
                    loading = false, snackBarMessage = e.message
                )
            }
        }
    }

}