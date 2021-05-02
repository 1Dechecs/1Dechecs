package com.example.petitechec

import android.content.Context
import android.content.res.Resources
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.RectF
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.SurfaceHolder
import android.view.SurfaceView

class DrawingView @JvmOverloads constructor (context: Context, attributes: AttributeSet? = null, defStyleAttr: Int = 0): SurfaceView(context, attributes,defStyleAttr), SurfaceHolder.Callback, Runnable {
    lateinit var canvas: Canvas
    val backgroundPaint = Paint()
    var screenWidth = 0f
    var screenHeight = 0f
    var drawing = false
    var resource = resources
    var echiquier = Echiquier(resource,0f,0f,0f,this)
    lateinit var thread: Thread
    init {
        backgroundPaint.color = Color.WHITE
    }

    fun pause() {
        drawing = false
        thread.join()
    }

    fun resume() {
        drawing = true
        thread = Thread(this)
        thread.start()
    }

    override fun run() {
        while (drawing) {
            draw()
        }
    }

    fun draw(){
        if (holder.surface.isValid) {
            canvas = holder.lockCanvas()
            canvas.drawRect(0f, 0f, canvas.width.toFloat(), canvas.height.toFloat(), backgroundPaint)
            echiquier.draw(canvas)
            holder.unlockCanvasAndPost(canvas)
        }
    }

    override fun onSizeChanged(w:Int, h:Int, oldw:Int, oldh:Int) {
        super.onSizeChanged(w, h, oldw, oldh)

        screenWidth = w.toFloat()
        screenHeight = h.toFloat()
        echiquier.x = (w / 2f)
        echiquier.y = (h / 8f)
        echiquier.ecart = (h * 3/ 32f)
        echiquier.updateCases()
    }

    override fun onTouchEvent(e: MotionEvent): Boolean{
        if (e.action == MotionEvent.ACTION_DOWN) {
            val x = e.rawX
            val y = e.rawY -250
            for (i in 0 until 8) {
                var case = echiquier.lesCases[i]
                var carre = RectF(case.x,case.y,case.x+case.ecart,case.y+case.ecart)
                if (carre.contains(x, y)) case.piece = null
            }
        }
        invalidate()
        return true
    }

    override fun surfaceChanged(holder: SurfaceHolder, format: Int,
                                width: Int, height: Int) {}

    override fun surfaceCreated(holder: SurfaceHolder) {}

    override fun surfaceDestroyed(holder: SurfaceHolder) {}
}