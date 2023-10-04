package com.week_9_hw

import android.os.Bundle
import android.os.Environment
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.week_9_hw.ui.theme.Week_9_hwTheme
import java.io.File

class MainActivity : ComponentActivity() {
	@OptIn(ExperimentalMaterial3Api::class)
	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		val preferenceName = "myPref"
		val preferenceKey = "myKey"
		val fileName = "myFile"
		setContent {
			val (content, setContent) = remember { mutableStateOf("Hello") }
			Week_9_hwTheme {
				// A surface container using the 'background' color from the theme
				Surface(
					modifier = Modifier.fillMaxSize(),
					color = MaterialTheme.colorScheme.background
				) {
					Column(
						modifier = Modifier
							.padding(10.dp)
							.fillMaxSize()
							.verticalScroll(
								rememberScrollState()
							)
					) {
						OutlinedTextField(value = content, onValueChange = { setContent(it) })
						CustomBtn(text = "create preference") {
							createPreference(preferenceName, preferenceKey, content)
						}
						CustomBtn(text = "read preference") {
							println(
								"read preference ${
									readPreference(
										preferenceName,
										preferenceKey
									)
								}"
							)
						}
						CustomBtn(text = "create internal bleeding") {
							createInternalBleeding(fileName, content)
						}
						CustomBtn(text = "read internal bleeding") {
							println("read internal bleeding ${readInternalBleeding(fileName)}")
						}
						CustomBtn(text = "log dir") {
							logDir()
						}
						CustomBtn(text = "create new music album") {
							createNewAlbumDir("myAlbum")
						}
						CustomBtn(text = "create private home work dir") {
							createPrivateHomeWorkDir()
						}

						DBCompose(context = this@MainActivity)
					}
				}
			}
		}
	}

	private fun createPreference(name: String, key: String, value: String) {
		val pref = getSharedPreferences(name, MODE_PRIVATE)
		val editor = pref.edit()
		editor.putString(key, value)
		editor.apply()
	}

	private fun readPreference(name: String, key: String): String {
		val pref = getSharedPreferences(name, MODE_PRIVATE)
		val value = pref.getString(key, "default")
		return value!!
	}

	private fun createInternalBleeding(fileName: String, fileContent: String) {
		val fos = openFileOutput(fileName, MODE_PRIVATE)
		fos.write(fileContent.toByteArray())
		fos.close()
	}

	private fun readInternalBleeding(fileName: String): String {
		val fis = openFileInput(fileName)
		val content = fis.readBytes().toString(Charsets.UTF_8)
		fis.close()
		return content
	}

	private fun logDir() {
		println("print files dir ${filesDir.absolutePath}")
		getDir("myBith", MODE_PRIVATE)
		println("files dir after change ${filesDir.absolutePath}")
		println("print myBith dir ${getDir("myBith", MODE_PRIVATE).absolutePath}")
	}

	private fun isExternalStorageWritable(): Boolean {
		val state = Environment.getExternalStorageState()
		return Environment.MEDIA_MOUNTED == state
	}

	private fun isExternalStorageReadable(): Boolean {
		val state = Environment.getExternalStorageState()
		return Environment.MEDIA_MOUNTED == state || Environment.MEDIA_MOUNTED_READ_ONLY == state
	}

	private fun createNewAlbumDir(albumName: String): File {
		val publicDocDir =
			Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOCUMENTS)
		println("public music dir path: ${publicDocDir.absolutePath}")
		val albumDir = File(publicDocDir, albumName)
		if (!albumDir.mkdirs()) {
			Log.e("error", "Directory not created")
		} else {
			val file = File(albumDir, "$albumName.txt")
			file.writeText("This is a txt file for $albumName")
			println("$albumName file path: ${file.absolutePath}")
		}
		return albumDir
	}

	private fun createPrivateHomeWorkDir(): File {
		val privateDir = getExternalFilesDir(Environment.DIRECTORY_DOCUMENTS)
		println("private dir path: ${privateDir!!.absolutePath}")
		val homeWorkDir = File(privateDir, "homeWork")
		if (!homeWorkDir.mkdirs()) {
			Log.e("error", "Directory not created")
		} else {
			val file = File(homeWorkDir, "homeWork.txt")
			file.writeText("This is a txt file for homeWork")
			println("homeWork file path: ${file.absolutePath}")
		}
		return homeWorkDir
	}
}

@Composable
fun CustomBtn(text: String, modifier: Modifier = Modifier, onClick: () -> Unit) {
	Button(
		onClick = onClick,
		modifier = modifier.padding(vertical = 5.dp, horizontal = 0.dp)
	) {
		Text(text = text)
	}
}