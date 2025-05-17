package com.example.gitblog2.presentation.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.gitblog2.core.Loader
import com.example.gitblog2.core.Resource
import com.example.gitblog2.domain.models.BlogModel
import com.example.gitblog2.presentation.components.BlogDetailTopBar
import dev.jeziellago.compose.markdowntext.MarkdownText

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BlogContentScreen(
    onBackClicked: () -> Unit,
    blogDetailState: Resource<BlogModel>,
    onRefresh: () -> Unit
) {
    val scrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior()
    Column(modifier = Modifier
        .fillMaxSize()
        .nestedScroll(scrollBehavior.nestedScrollConnection)) {
        BlogDetailTopBar(onBackClick = onBackClicked, scrollBehavior = scrollBehavior)
        when (blogDetailState) {
            is Resource.Failure -> {
                val error = blogDetailState.error
                Column(
                    modifier = Modifier.fillMaxSize(),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    IconButton(onClick = onRefresh) {
                        Icon(
                            Icons.Default.Refresh,
                            contentDescription = "Refresh"
                        )
                    }
                    Text(text = error)
                }
            }

            Resource.Loading -> {
                Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                    Column(modifier = Modifier.fillMaxSize(), verticalArrangement = Arrangement.Center, horizontalAlignment = Alignment.CenterHorizontally){
                        repeat(10) {
                            Loader()
                        }
                    }
                }
            }

            is Resource.Success -> {
                val blog = blogDetailState.data
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(16.dp)
                        .verticalScroll(rememberScrollState())
                ) {
                    Text(text = blog.title, fontSize = 18.sp, fontWeight = FontWeight.Bold)
                    MarkdownText(
                        markdown = blog.content ?: "",
                        isTextSelectable = true,
                        linkColor = MaterialTheme.colorScheme.secondary,
                        syntaxHighlightColor = MaterialTheme.colorScheme.surfaceVariant,
                        syntaxHighlightTextColor = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                }
            }
        }
    }

}