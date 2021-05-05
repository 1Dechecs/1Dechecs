package com.example.petitechec

import android.content.res.Resources
import android.graphics.*

open class Piece(open var resources: Resources, open var xi: Int, open var yi: Int, open var xf: Int, open var yf: Int, open var couleur: Boolean, view: DrawingView){
    val paint = Paint()

    open fun draw(canvas: Canvas?) {
        //* Dessine la pièce *//
    }

    open fun isPossible(case1: Case,case2: Case, lesCases: ArrayList<Case>): Boolean{
        //* Vérifie si le mouvement est autorisé par la pièce *//
        return true
    }
}