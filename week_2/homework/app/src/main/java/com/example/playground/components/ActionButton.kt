package com.example.playground.components

import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.example.playground.ui.theme.Blue40

@Preview
@Composable
fun ActionButton() {
	FloatingActionButton(
		onClick = { /*TODO*/ },
		containerColor = Blue40,
		contentColor = MaterialTheme.colorScheme.background,
		shape = CircleShape,
	) {
		Icon(
			imageVector = Icons.Filled.Email,
			contentDescription = "Email",
			tint = MaterialTheme.colorScheme.background
		)
	}
}