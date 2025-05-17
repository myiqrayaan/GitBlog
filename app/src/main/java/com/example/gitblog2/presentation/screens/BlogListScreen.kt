package com.example.gitblog2.presentation.screens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.pulltorefresh.PullToRefreshBox
import androidx.compose.material3.pulltorefresh.PullToRefreshState
import androidx.compose.material3.pulltorefresh.pullToRefresh
import androidx.compose.material3.pulltorefresh.rememberPullToRefreshState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import com.example.gitblog2.core.Loader
import com.example.gitblog2.core.Resource
import com.example.gitblog2.domain.models.BlogModel
import com.example.gitblog2.navigation.Routes
import com.example.gitblog2.presentation.components.BlogCard
import com.example.gitblog2.presentation.components.BlogListTopBar
import com.example.gitblog2.presentation.viewModels.BlogListViewModel
import org.koin.androidx.compose.koinViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BlogListScreen(navController: NavController) {
    val viewModel: BlogListViewModel = koinViewModel()
    val state = viewModel.state.collectAsStateWithLifecycle()

    Column(modifier = Modifier.fillMaxSize()) {
        BlogListTopBar()
        when (state.value) {
            is Resource.Failure -> {
                val error = (state.value as Resource.Failure).error
                Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                    Text(text = error)
                }
            }

            Resource.Loading -> {
                Column(
                    modifier = Modifier.fillMaxSize(),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    repeat(10) {
                        Loader()
                    }
                }
            }

            is Resource.Success -> {
                val blogs = (state.value as Resource.Success<List<BlogModel>>).data
                LazyVerticalGrid(
                    columns = GridCells.Adaptive(minSize = 300.dp),
                    contentPadding = PaddingValues(10.dp),
                    verticalArrangement = Arrangement.spacedBy(20.dp),
                    horizontalArrangement = Arrangement.spacedBy(20.dp),
                ) {
                    items(blogs) { blog ->
                        BlogCard(blog = blog, modifier = Modifier.clickable(onClick = {
                            navController.navigate(
                                Routes.BlogDetail(blog.id)
                            )
                        }))

                    }
                }
            }
        }


    }
}