package com.example.petitechec

import android.content.res.Resources
import android.graphics.BitmapFactory
import android.graphics.Canvas
import android.graphics.Rect

class Cavalier(resources: Resources, xi: Int, yi: Int, xf: Int, yf: Int, couleur: Boolean, view: DrawingView): Piece(resources,xi,yi,xf,yf,couleur,view) {
    val cavalier = arrayOf(R.drawable.cavalier_blanc,R.drawable.cavalier_noir)

    override fun draw(canvas: Canvas?) {
        //* Dessine le cavalier *//
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

    override fun isPossible(case1: Case, case2: Case, lesCases: ArrayList<Case>): Boolean{
        //* Vérifie si le mouvement est autorisé par la pièce *//
        var possible = false
        if (case1.nr + 2 == case2.nr || case1.nr - 2 == case2.nr) possible = true
        return possible
    }
}