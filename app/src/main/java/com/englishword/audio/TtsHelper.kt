package com.englishword.audio

import android.content.Context
import android.speech.tts.TextToSpeech
import android.util.Log
import java.util.Locale

class TtsHelper(context: Context, private val onInit: (Boolean) -> Unit) : TextToSpeech.OnInitListener {

    private val tts: TextToSpeech = TextToSpeech(context, this)
    private var isInitialized = false

    var language: Locale = Locale.US
        set(value) {
            if (isInitialized) {
                tts.language = value
            }
            field = value
        }

    var pitch: Float = 1.0f
        set(value) {
            if (isInitialized) {
                tts.setPitch(value)
            }
            field = value
        }

    var speed: Float = 1.0f
        set(value) {
            if (isInitialized) {
                tts.setSpeechRate(value)
            }
            field = value
        }

    override fun onInit(status: Int) {
        if (status == TextToSpeech.SUCCESS) {
            isInitialized = true
            tts.language = language
            tts.setPitch(pitch)
            tts.setSpeechRate(speed)
            onInit(true)
            Log.d("TtsHelper", "TTS Initialized successfully.")
        } else {
            isInitialized = false
            onInit(false)
            Log.e("TtsHelper", "TTS Initialization failed.")
        }
    }

    fun speak(text: String) {
        if (isInitialized) {
            tts.speak(text, TextToSpeech.QUEUE_FLUSH, null, null)
        } else {
            Log.e("TtsHelper", "TTS not initialized, cannot speak.")
        }
    }

    fun shutdown() {
        if (this::tts.isInitialized) {
            tts.stop()
            tts.shutdown()
        }
    }
}
