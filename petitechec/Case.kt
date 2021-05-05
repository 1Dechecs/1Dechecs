package com.example.petitechec

import android.graphics.Canvas
import android.graphics.Color.BLUE
import android.graphics.Color.RED
import android.graphics.Paint
import android.graphics.PointF
import android.graphics.RectF

class Case(var x: Float, var y: Float, var ecart: Float, var piece: Piece?, var nr: Int) {

    fun draw(canvas: Canvas, carre: RectF, casePaint: Paint) {
        //* Dessine une case *//
        canvas.drawRect(carre, casePaint)
    }

}