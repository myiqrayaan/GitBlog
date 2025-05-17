package com.example.gitblog2.navigation

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.gitblog2.presentation.screens.BlogContentScreen
import com.example.gitblog2.presentation.screens.BlogListScreen
import com.example.gitblog2.presentation.viewModels.BlogDetailViewModel
import com.example.gitblog2.presentation.viewModels.BlogListViewModel
import org.koin.androidx.compose.koinViewModel

@Composable
fun AppNavigation(modifier: Modifier = Modifier) {
    val navController = rememberNavController()

    NavHost(modifier = modifier.fillMaxSize(), navController = navController, startDestination = Routes.BlogList) {
        composable<Routes.BlogList> {
            BlogListScreen(navController)

        }

        composable<Routes.BlogDetail> {
            val blogDetailViewModel: BlogDetailViewModel = koinViewModel()
            val blogDetailState = blogDetailViewModel.blogDetailState.collectAsStateWithLifecycle()
            BlogContentScreen(
                onBackClicked = { navController.navigateUp()},
                blogDetailState=blogDetailState.value,
                onRefresh = {blogDetailViewModel.getBlogById()}
            )
        }
    }
}