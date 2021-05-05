package com.example.petitechec

import android.content.res.Resources
import android.graphics.BitmapFactory
import android.graphics.Canvas
import android.graphics.Rect
import kotlin.math.abs

class Tour(resources: Resources, xi: Int, yi: Int, xf: Int, yf: Int, couleur: Boolean, view: DrawingView): Piece(resources,xi,yi,xf,yf,couleur,view) {
    val tour = arrayOf(R.drawable.tour_blanc, R.drawable.tour_noir)

    override fun draw(canvas: Canvas?) {
        //* Dessine la tour *//
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

    override fun isPossible(case1: Case, case2: Case, lesCases: ArrayList<Case>): Boolean{
        //* Vérifie si le mouvement est autorisé par la pièce *//
        var possible = true
        for (i in 1 until abs(case2.nr - case1.nr)) {
            if (case2.nr > case1.nr) {
                if (lesCases[case1.nr - 1 + i].piece != null)
                    possible = false
            }
            else {
                if (lesCases[case1.nr - 1 - i].piece != null)
                    possible = false
            }
        }
        println(possible)
        return possible
    }
}