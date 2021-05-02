package com.example.petitechec

import android.graphics.Canvas
import android.graphics.Color.BLUE
import android.graphics.Color.RED
import android.graphics.Paint
import android.graphics.PointF
import android.graphics.RectF

class Case(var x: Float, var y: Float, var ecart: Float, var piece: Piece?) {

    fun draw(canvas: Canvas, carre: RectF, casePaint: Paint) {
        canvas.drawRect(carre, casePaint)
    }
}