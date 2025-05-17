package com.example.gitblog2.presentation.viewModels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.gitblog2.core.Resource
import com.example.gitblog2.domain.models.BlogModel
import com.example.gitblog2.domain.repository.BlogRepository
import com.example.gitblog2.presentation.screens.BlogListScreen

import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch


class BlogListViewModel (private val blogRepository: BlogRepository): ViewModel() {

    private val _state = MutableStateFlow<Resource<List<BlogModel>>>(Resource.Loading)

    val state  = _state.asStateFlow()

    init {
        fetchAllBlogs()
    }

    fun fetchAllBlogs() {

        viewModelScope.launch {
            _state.update { blogRepository.fetchAllBlogs() }
            }
        }
    }
