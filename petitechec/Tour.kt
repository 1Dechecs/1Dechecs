package com.example.petitechec

import android.content.res.Resources
import android.graphics.BitmapFactory
import android.graphics.Canvas
import android.graphics.Rect

class Tour(override var resources: Resources, override var xi: Int, override var yi: Int, override var xf: Int, override var yf: Int, override var couleur: Boolean, view: DrawingView): Piece(resources,xi,yi,xf,yf,couleur,view) {
    val tour = arrayOf(R.drawable.tour_blanc, R.drawable.tour_noir)

    override fun draw(canvas: Canvas?) {
        super.draw(canvas)
        if (couleur){
            val pieceBitmap = BitmapFactory.decodeResource(resources, tour[1])
            canvas?.drawBitmap(pieceBitmap, null, Rect(xi+35, yi+40,xf-45,yf-40), paint)
        }
        else{
            val pieceBitmap = BitmapFactory.decodeResource(resources, tour[0])
            canvas?.drawBitmap(pieceBitmap, null, Rect(xi+45, yi+40,xf-35,yf-40), paint)
        }
    }
}