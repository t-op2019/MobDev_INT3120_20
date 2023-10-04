package com.week_11_hw

import android.content.ComponentName
import android.content.Intent
import android.content.ServiceConnection
import android.os.Bundle
import android.os.IBinder
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Button
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.remember
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.week_11_hw.services.MyBoundService
import com.week_11_hw.ui.theme.Week_11_hwTheme

class BoundActivity : ComponentActivity() {
	private lateinit var boundService: MyBoundService
	private var isBound = false

	private val connection = object : ServiceConnection {
		override fun onServiceConnected(name: ComponentName?, service: IBinder?) {
			val binder = service as MyBoundService.LocalBinder
			boundService = binder.getService()
			isBound = true
		}

		override fun onServiceDisconnected(name: ComponentName?) {
			isBound = false
		}
	}

	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		setContent {
			val (randNum, setRandNum) = remember { mutableStateOf(0) }
			Week_11_hwTheme {
				Surface {
					Column {
						Button(onClick = { setRandNum(getRandomNumber()) }) {
							Text(text = "Random Number")
						}
						Text(text = "The random number is: $randNum")
						Spacer(modifier = Modifier.height(10.dp))
						Button(onClick = { finish() }) {
							Text(text = "Go Back")
						}
					}
				}
			}
		}
	}

	override fun onStart() {
		super.onStart()
		Intent(this, MyBoundService::class.java).also { intent ->
			bindService(intent, connection, BIND_AUTO_CREATE)
		}
	}

	override fun onStop() {
		super.onStop()
		unbindService(connection)
		isBound = false
	}

	private fun getRandomNumber(): Int {
		if (isBound) {
			return boundService.getRandomNumber
		}
		return 0
	}
}