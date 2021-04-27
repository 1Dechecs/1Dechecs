package com.example.1Dechecs

import android.graphics.BitmapFactory
import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.Rect


class Pièce (var pieceSize: Float, var view: DrawingView) {


    val pieces = intArrayOf(R.drawable.roi_blanc,R.drawable.roi_noir,R.drawable.cavalier_blanc,R.drawable.cavalier_noir,R.drawable.tour_blanc,R.drawable.tour_noir)

    fun onDraw(canvas: Canvas) {
        val paint = Paint()
        val roiBlancBitmap = BitmapFactory.decodeResource(drawable,pieces[1])
        canvas?.drawBitmap(roiBlancBitmap, null, Rect(0,0,600,600), paint)
    }
}

