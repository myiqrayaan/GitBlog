package com.example.gitblog2.presentation.viewModels

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.toRoute
import com.example.gitblog2.core.Resource
import com.example.gitblog2.domain.models.BlogModel
import com.example.gitblog2.domain.repository.BlogRepository
import com.example.gitblog2.navigation.Routes
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class BlogDetailViewModel(private val blogRepository: BlogRepository, savedStateHandle: SavedStateHandle): ViewModel() {
    private val id = savedStateHandle.toRoute<Routes.BlogDetail>().id
    private val _blogDetailState = MutableStateFlow<Resource<BlogModel>>(Resource.Loading)
    val blogDetailState = _blogDetailState.asStateFlow()

    init {
        getBlogById()
    }
    fun getBlogById(){
        viewModelScope.launch {
            _blogDetailState.update{ blogRepository.getBlogById(id) }
        }
    }
}