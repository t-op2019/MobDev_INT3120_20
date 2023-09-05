package com.example.playground.components

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.example.playground.ui.theme.Blue40

@Preview
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopBar() {
	TopAppBar(
		title = {
			Text(text = "Donation. 1.5")
		},
		actions = {
			IconButton(onClick = { /* doSomething() */ }) {
				Icon(
					imageVector = Icons.Filled.MoreVert,
					contentDescription = "More",
					tint = MaterialTheme.colorScheme.background
				)
			}
		},
		colors = TopAppBarDefaults.smallTopAppBarColors(
			containerColor = Blue40,
			titleContentColor = MaterialTheme.colorScheme.background,
		),
	)
}
