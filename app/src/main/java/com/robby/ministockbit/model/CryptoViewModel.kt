package com.robby.ministockbit.model

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.robby.ministockbit.data.online.CryptoRepository
import com.robby.ministockbit.utils.LoadingState
import kotlinx.coroutines.launch

class CryptoViewModel(private val userRepository: CryptoRepository) : ViewModel() {

    private var _page = 1
    private val _loadingState = MutableLiveData<LoadingState>()
    val loadingState: LiveData<LoadingState>
        get() = _loadingState

    val data = userRepository.data

    init {
        fetchData()
    }

    private fun fetchData() {
        viewModelScope.launch {
            try {
                _loadingState.value = LoadingState.LOADING
                userRepository.refresh(CryptoRequest(_page))
                _loadingState.value = LoadingState.LOADED
            } catch (e: Exception) {
                _loadingState.value = LoadingState.error(e.message)
            }
        }
    }
}