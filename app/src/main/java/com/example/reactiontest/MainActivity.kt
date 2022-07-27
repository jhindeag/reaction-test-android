package com.example.reactiontest

import android.os.Bundle
import android.os.Handler
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout
import java.util.*
import kotlin.concurrent.timerTask

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val screen = findViewById<ConstraintLayout>(R.id.screen)
        val introText = findViewById<TextView>(R.id.introText)
        var isStarted = false
        var shouldClick = false
        var time = 0
        val handler = Handler()
        var timer = Timer()
        screen.setOnClickListener {
            if (!isStarted) {
                isStarted = true
                introText.textSize = 30.toFloat()
                introText.text = "Wait for green then tap."
                time = 0
                val delay = ((Random().nextInt(3) + 2) * 1000).toLong()
                handler.postDelayed({
                    screen.setBackgroundColor(resources.getColor(android.R.color.holo_green_light))
                    introText.text = "Tap now!"
                    shouldClick = true
                    timer = Timer()
                    timer.scheduleAtFixedRate(timerTask { time += 1 }, 0, 1)
                }, delay)
            } else {
                timer.cancel()
                handler.removeCallbacksAndMessages(null)
                isStarted = false
                screen.setBackgroundColor(resources.getColor(R.color.white))
                if (shouldClick) {
                    introText.text = "$time ms.\nTap to try again."
                } else {
                    introText.text = "Too soon.\nTap to try again."
                }
                shouldClick = false
            }
        }
    }
}