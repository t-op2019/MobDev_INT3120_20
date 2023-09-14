package com.example.week_5_hw

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.week_5_hw.ui.theme.Week_5_hwTheme
import com.example.week_5_hw.views.Clock
import com.example.week_5_hw.views.Login

class MainActivity : ComponentActivity() {
	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		setContent {
			Week_5_hwTheme {
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
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppScaffold() {
	val (selectedIndex, setSelectedIndex) = remember { mutableIntStateOf(0) }
	Scaffold(topBar = { TopTabRow(selectedIndex = selectedIndex, onSelected = setSelectedIndex) }) {
		Column(
			modifier = Modifier
				.padding(it)
				.padding(10.dp)
		) {
			when (selectedIndex) {
				0 -> Clock()
				1 -> Login()
			}
		}
	}
}

@Composable
fun TopTabRow(selectedIndex: Int, onSelected: (Int) -> Unit) {
	TabRow(selectedTabIndex = selectedIndex, modifier = Modifier.fillMaxWidth()) {
		Tab(
			selected = selectedIndex == 0,
			onClick = { onSelected(0) },
			modifier = Modifier.padding(horizontal = 0.dp, vertical = 10.dp)
		) {
			Text(text = "1-Clock")
		}
		Tab(
			selected = selectedIndex == 1,
			onClick = { onSelected(1) },
			modifier = Modifier.padding(horizontal = 0.dp, vertical = 10.dp)
		) {
			Text(text = "2-Login")
		}
	}
}