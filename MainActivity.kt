package com.example.1Dechecs

import android.content.Context
import android.util.AttributeSet
import android.view.SurfaceView

class Pieces @JvmOverloads constructor (context: Context, attributes: AttributeSet? = null, defStyleAttr: Int = 0): SurfaceView(context, attributes,defStyleAttr) {
}

---------------------------------------------------------------------------------------------------------------------



import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

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
}
