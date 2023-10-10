package com.week_13_hw.components

import android.media.AudioAttributes
import android.media.MediaPlayer
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.ui.Modifier

@Composable
fun Player(modifier: Modifier = Modifier) {
	val mediaPlayer = MediaPlayer()
	mediaPlayer.apply {
		setAudioAttributes(
			AudioAttributes
				.Builder()
				.setContentType(AudioAttributes.CONTENT_TYPE_MUSIC)
				.build()
		)
		setDataSource("https://www.soundhelix.com/examples/mp3/SoundHelix-Song-1.mp3")
		prepare()
	}
	DisposableEffect(Unit) {
		onDispose {
			mediaPlayer.release()
		}
	}

	Column(modifier = modifier) {
		Button(onClick = { mediaPlayer.start() }) {
			Text(text = "Play")
		}
		Button(onClick = { mediaPlayer.pause() }) {
			Text(text = "Pause")
		}
	}
}