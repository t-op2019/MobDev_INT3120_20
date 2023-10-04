package com.week_10_hw

import android.content.ContentValues
import android.os.Bundle
import android.provider.ContactsContract
import android.provider.UserDictionary
import android.view.inputmethod.EditorInfo
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
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
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.week_10_hw.ui.theme.Week_10_hwTheme

class MainActivity : ComponentActivity() {
	@OptIn(ExperimentalMaterial3Api::class)
	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		setContent {
			val (userDictionary, setUserDictionary) = remember { mutableStateOf("") }
			val (name, setName) = remember { mutableStateOf("") }
			val (id, setId) = remember { mutableStateOf("") }
			val spacerModifier = Modifier.padding(vertical = 5.dp)
			Week_10_hwTheme {
				// A surface container using the 'background' color from the theme
				Surface(
					modifier = Modifier.fillMaxSize(),
					color = MaterialTheme.colorScheme.background
				) {
					Column(
						modifier = Modifier
							.fillMaxSize()
							.padding(16.dp)
					) {
						Button(
							onClick = { setUserDictionary(readContacts()) },
							modifier = spacerModifier
						) {
							Text(text = "Read All User Dictionary")
						}
						if (userDictionary.isNotEmpty()) {
							Text(text = userDictionary, modifier = spacerModifier)
						}
						OutlinedTextField(
							value = name,
							onValueChange = { setName(it) },
							modifier = spacerModifier,
							label = { Text(text = "Word") }
						)
						OutlinedTextField(
							value = id,
							onValueChange = { setId(it) },
							modifier = spacerModifier,
							label = { Text(text = "ID") },
							keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
						)
						Button(
							onClick = { insertContactName(name); setName("") },
							modifier = spacerModifier
						) {
							Text(text = "Insert EN Word")
						}
						Button(
							onClick = {
								if (id.isNotEmpty()) {
									updateById(name, id.toInt()); setName(""); setId("")
								}
							},
							modifier = spacerModifier
						) {
							Text(text = "Update EN Word")
						}
						Button(
							onClick = {
								if (id.isNotEmpty()) {
									deleteById(id.toInt()); setName(""); setId("")
								}
							},
							modifier = spacerModifier
						) {
							Text(text = "Delete EN Word")
						}
					}
				}
			}
		}
	}

	private fun readContacts(): String {
		var res = ""
		val projection =
			arrayOf(ContactsContract.Contacts._ID, ContactsContract.Contacts.DISPLAY_NAME)
		val cursor = contentResolver.query(
			ContactsContract.Contacts.CONTENT_URI,
			projection,
			null,
			null,
			null
		)
		cursor?.use {
			while (it.moveToNext()) {
				val contactId = it.getLong(it.getColumnIndex(ContactsContract.Contacts._ID) ?: 0)
				val name =
					it.getString(it.getColumnIndex(ContactsContract.Contacts.DISPLAY_NAME) ?: 0)
				res += "$contactId: $name\n"
			}
		}
		cursor?.close()
		println("res: $res")
		return res
	}

	private fun insertContactName(name: String) {
		val values = ContentValues().apply {
			put(ContactsContract.Contacts.DISPLAY_NAME, name)
		}
		contentResolver.insert(ContactsContract.RawContacts.CONTENT_URI, values)
	}

	private fun updateById(word: String, id: Int) {
		val values = ContentValues().apply {
			put(ContactsContract.Contacts.DISPLAY_NAME, word)
		}
		val selection = "${ContactsContract.Contacts._ID} = ?"
		val selectionArgs = arrayOf(id.toString())
		contentResolver.update(
			ContactsContract.Contacts.CONTENT_URI,
			values,
			selection,
			selectionArgs
		)
	}

	private fun deleteById(id: Int) {
		val selection = "${ContactsContract.Contacts._ID} = ?"
		val selectionArgs = arrayOf(id.toString())
		contentResolver.delete(ContactsContract.Contacts.CONTENT_URI, selection, selectionArgs)
	}
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
	Text(
		text = "Hello $name!",
		modifier = modifier
	)
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
	Week_10_hwTheme {
		Greeting("Android")
	}
}