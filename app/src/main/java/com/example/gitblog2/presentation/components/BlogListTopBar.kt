package com.example.gitblog2.presentation.components

import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BlogListTopBar(modifier: Modifier = Modifier) {
    TopAppBar(
        windowInsets = WindowInsets(0),
        title = { Text("Git Blog") }
    )
}