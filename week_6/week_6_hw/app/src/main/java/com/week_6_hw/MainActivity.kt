package com.week_6_hw

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.combinedClickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.week_6_hw.ui.theme.Purple40
import com.week_6_hw.ui.theme.Week_6_hwTheme

class MainActivity : ComponentActivity() {
	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		setContent {
			Week_6_hwTheme {
				// A surface container using the 'background' color from the theme
				Surface(
					modifier = Modifier.fillMaxSize(),
					color = MaterialTheme.colorScheme.background
				) {
					AppScaffold()
				}
			}
		}
	}
}

@Preview(showBackground = true)
@OptIn(ExperimentalMaterial3Api::class, ExperimentalFoundationApi::class)
@Composable
fun AppScaffold() {
	val openContext = remember { mutableStateOf(false) }
	val openMenu = remember { mutableStateOf(false) }
	val contextActions = listOf("Copy image", "Save image")
	val menuActions = listOf("Action 1", "Action 2", "Action 3")
	Scaffold(topBar = {
		TopAppBar(
			title = { Text(text = "Menus") },
			actions = {
				IconButton(
					onClick = { openMenu.value = true },
				) {
					// creates a menu icon
					Image(
						imageVector = Icons.Filled.MoreVert, contentDescription = "More vert",
						colorFilter = ColorFilter.tint(Color.White)
					)
				}
				DropdownMenu(
					expanded = openMenu.value,
					onDismissRequest = { openMenu.value = false }) {
					menuActions.map { action ->
						DropdownMenuItem(
							text = { Text(text = action) },
							onClick = { openMenu.value = false })
					}
				}
			},
			colors = TopAppBarDefaults.mediumTopAppBarColors(
				containerColor = Purple40,
				titleContentColor = Color.White
			)
		)
	}) {
		Column(
			modifier = Modifier
				.padding(it)
				.padding(10.dp)
				.fillMaxSize(),
			horizontalAlignment = Alignment.CenterHorizontally
		) {
			Text(text = "Long press the image for extra context")
			Row {
				Image(painter = painterResource(id = R.drawable.dog), contentDescription = "A dog",
					modifier = Modifier.combinedClickable(onLongClick = {
						openContext.value = true
					}) { })
				DropdownMenu(
					expanded = openContext.value,
					onDismissRequest = { openContext.value = false }) {
					contextActions.map { action ->
						DropdownMenuItem(
							text = { Text(text = action) },
							onClick = { openContext.value = false })
					}
				}
			}
		}
	}
}