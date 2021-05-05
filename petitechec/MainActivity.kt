package com.example.petitechec

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View

class MainActivity: AppCompatActivity() {

    lateinit var DrawingView: DrawingView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        DrawingView = findViewById<DrawingView>(R.id.vMain)
    }

    override fun onPause() {
        super.onPause()
        DrawingView.pause()
    }

    override fun onResume() {
        super.onResume()
        DrawingView.resume()
    }

    fun onClick(v: View){
        DrawingView.gameOver()
    }
}