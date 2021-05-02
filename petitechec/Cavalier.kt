package com.example.petitechec

import android.content.res.Resources
import android.graphics.BitmapFactory
import android.graphics.Canvas
import android.graphics.Rect

class Cavalier(override var resources: Resources, override var xi: Int, override var yi: Int, override var xf: Int, override var yf: Int, override var couleur: Boolean, view: DrawingView): Piece(resources,xi,yi,xf,yf,couleur,view) {
    val cavalier = arrayOf(R.drawable.cavalier_blanc,R.drawable.cavalier_noir)

    override fun draw(canvas: Canvas?) {
        super.draw(canvas)
        if (couleur){
            val pieceBitmap = BitmapFactory.decodeResource(resources, cavalier[1])
            canvas?.drawBitmap(pieceBitmap, null, Rect(xi+30, yi+30,xf-30,yf-30), paint)
        }
        else{
            val pieceBitmap = BitmapFactory.decodeResource(resources, cavalier[0])
            canvas?.drawBitmap(pieceBitmap, null, Rect(xi+30, yi+30,xf-30,yf-30), paint)
        }
    }
}