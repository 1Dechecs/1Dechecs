package com.example.petitechec

import android.content.Context
import android.content.res.Resources
import android.graphics.*
import android.graphics.Color.BLUE
import android.graphics.Color.RED
import android.util.AttributeSet

class Echiquier (var resource: Resources, var x: Float, var y: Float, var ecart: Float, view: DrawingView) {
    var casePaint = Paint()
    var lesCases = ArrayList<Case>()
    var lesPieces = ArrayList<Piece?>()
    var r = view
    init {
        lesPieces.add(Roi(resource,0,0,0,0,true,r ))
        lesPieces.add(Cavalier(resource,0,0,0,0,true,r ))
        lesPieces.add(Tour(resource,0,0,0,0,true,r ))
        lesPieces.add(Tour(resource,0,0,0,0,false,r ))
        lesPieces.add(Cavalier(resource,0,0,0,0,false,r ))
        lesPieces.add(Roi(resource,0,0,0,0,false,r ))
        lesCases.add(Case(x, y, ecart, lesPieces[0]))
        lesCases.add(Case(x, y, ecart, lesPieces[1]))
        lesCases.add(Case(x, y, ecart, lesPieces[2]))
        lesCases.add(Case(x, y, ecart, null))
        lesCases.add(Case(x, y, ecart, null))
        lesCases.add(Case(x, y, ecart, lesPieces[3]))
        lesCases.add(Case(x, y, ecart, lesPieces[4]))
        lesCases.add(Case(x, y, ecart, lesPieces[5]))
    }

    fun draw(canvas: Canvas){
        for (i in 0 until 8){
            var case = lesCases[i]
            var carre = RectF(case.x, case.y, case.x + case.ecart, case.y + case.ecart)
            if (i % 2 != 0) {
                casePaint.color = BLUE
                case.draw(canvas, carre, casePaint)
                case.piece?.draw(canvas)
            }
            else{
                casePaint.color = RED
                case.draw(canvas, carre, casePaint)
                case.piece?.draw(canvas)
            }

        }
    }

    fun updateCases(){
        listCases(x, y, ecart)
    }

    fun listCases(x: Float,y: Float,ecart: Float) {
        var Y = y
        var piece : Piece?
        var case : Case
        for (i in 0 until 8 ) {
            case = lesCases[i]
            case.x = x
            case.y = Y
            case.ecart = ecart
            Y += ecart
            if(case.piece != null){
                piece = case.piece
                piece?.xi = case.x.toInt()
                piece?.yi = case.y.toInt()
                piece?.xf = (case.x + case.ecart).toInt()
                piece?.yf = (case.y + case.ecart).toInt()
            }
        }
    }
}