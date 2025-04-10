package com.example.seekhoapi.viewModel

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.seekhoapi.data.api.RetrofitInstance
import com.example.seekhoapi.data.models.Data
import kotlinx.coroutines.launch

class PostViewModel: ViewModel() {
    private val _posts = mutableStateOf<List<Data>>(emptyList())
    val posts: State<List<Data>> = _posts

    init {
        fetchPosts()
    }

    private fun fetchPosts() {
        viewModelScope.launch {
            try {
                _posts.value = RetrofitInstance.api.getPosts().data
            } catch (e: Exception) {
            }
        }
    }
}