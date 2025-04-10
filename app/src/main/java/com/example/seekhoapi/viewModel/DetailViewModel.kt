package com.example.seekhoapi.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.seekhoapi.data.api.RetrofitInstance
import com.example.seekhoapi.data.models.Data
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class DetailViewModel: ViewModel()  {
    private val _animeDetail = MutableStateFlow<Data?>(null)
    val animeDetail: StateFlow<Data?> = _animeDetail

    fun fetchAnime(animeId: Int) {
        viewModelScope.launch {
            try {
                val response = RetrofitInstance.api2.getAnimeDetail(animeId)
                if (response.isSuccessful) {
                    _animeDetail.value = response.body()?.data
                }
            } catch (e: Exception) {
            }
        }
    }
}