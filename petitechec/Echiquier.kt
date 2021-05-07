import android.content.Context
import android.content.res.Resources
import android.graphics.*
import android.graphics.Color.BLUE
import android.graphics.Color.RED
import android.util.AttributeSet

class Echiquier (var resource: Resources, var x: Float, var y: Float, var ecart: Float, view: DrawingView) {
    val bordure = 30F
    var nbPaint = Paint()
    var contPaint = Paint()
    var casePaint = Paint()
    var lesCases = ArrayList<Case>()
    var lesPieces = ArrayList<Piece?>()
    var r = view
    init {
        //* Initialise une liste de pièces ainsi qu'une liste de cases *//
        lesPieces.add(Roi(resource,0,0,0,0,true,r ))
        lesPieces.add(Cavalier(resource,0,0,0,0,true,r ))
        lesPieces.add(Tour(resource,0,0,0,0,true,r ))
        lesPieces.add(Tour(resource,0,0,0,0,false,r ))
        lesPieces.add(Cavalier(resource,0,0,0,0,false,r ))
        lesPieces.add(Roi(resource,0,0,0,0,false,r ))
        lesCases.add(Case(x, y, ecart, lesPieces[0],1))
        lesCases.add(Case(x, y, ecart, lesPieces[1],2))
        lesCases.add(Case(x, y, ecart, lesPieces[2],3))
        lesCases.add(Case(x, y, ecart, null,4))
        lesCases.add(Case(x, y, ecart, null,5))
        lesCases.add(Case(x, y, ecart, lesPieces[3],6))
        lesCases.add(Case(x, y, ecart, lesPieces[4],7))
        lesCases.add(Case(x, y, ecart, lesPieces[5],8))
    }

    fun draw(canvas: Canvas, k: Int){
        //* Dessine l'échiquier (composé de cases), les pièces (s'il y en a) ainsi que le numéro de case *//
        updateCases()
        contPaint.color = Color.argb(255,47, 27, 12)
        canvas.drawRect(x - bordure, y - bordure, x + ecart + bordure , y + (8 * ecart) + bordure,contPaint)
        for (i in 0 until 8){
            val j = 8 - i
            val case = lesCases[i]
            val carre = RectF(case.x, case.y, case.x + case.ecart, case.y + case.ecart)
            if (i % 2 != 0) {
                casePaint.color = Color.argb(255,91, 60, 17)
            }
            else{
                casePaint.color = Color.argb(255,200, 173, 127)
            }
            case.draw(canvas, carre, casePaint)
            case.piece?.draw(canvas)
            nbPaint.color = Color.WHITE
            drawNrCase(canvas, j, case, nbPaint)
            if (i == k) {
                nbPaint.color = RED
                drawNrCase(canvas, 8 - k, case, nbPaint)
            }
        }
    }

    fun drawNrCase(canvas: Canvas, j: Int, case: Case, nbPaint: Paint){
        canvas.drawText(j.toString(),case.x + 5F, case.y + ecart - 15F, nbPaint)
        canvas.rotate(180F, case.x + (ecart / 2),case.y + (ecart / 2))
        canvas.drawText(j.toString(), case.x + 5F, case.y + ecart - 15F, nbPaint)
        canvas.rotate(180F, case.x + (ecart / 2),case.y + (ecart / 2))
    }

    fun updateCases(){
        //* Met à jour la liste de cases *//
        listeCases(x, y, ecart)
    }

    fun listeCases(x: Float,y: Float,ecart: Float) {
        //* Donne les bonnes coordonnées aux cases et aux pièces (s'il y en a) *//
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

    fun getNrCase(x: Float, y: Float, canvas: Canvas) : Int {
        //* Prend la position du clic et vérifie si elle correspond à une case, si oui, renvoie le numéro de la case, sinon, renvoie -1 *//
        var case : Case
        for (i in 0 until lesCases.size) {
            case = lesCases[i]
            val carre = RectF(case.x, case.y, case.x + case.ecart, case.y + case.ecart)
            if (carre.contains(x, y)) {
                return i
            }
        }
        return -1
    }

    fun setPiece(i: Int, j: Int) {
        //* Exécute le déplacement de la pièce sélectionnée *//
        lesCases[j].piece = lesCases[i].piece
        lesCases[i].piece = null
    }

    fun isRoi(): ArrayList<Piece?> {
        //* Renvoie une liste contenant le(s) roi(s) encore présent sur l'échiquier *//
        val roi = ArrayList<Piece?>()
        for (i in 0 until 8)
            if (lesCases[i].piece == lesPieces[0] || lesCases[i].piece == lesPieces[5])
                roi.add(lesCases[i].piece)
        return roi
    }

    fun resetLesCases(){
        //* Replace les pièces à leur position de début de partie *//
        lesCases.clear()
        lesCases.add(Case(x, y, ecart, lesPieces[0],1))
        lesCases.add(Case(x, y, ecart, lesPieces[1],2))
        lesCases.add(Case(x, y, ecart, lesPieces[2],3))
        lesCases.add(Case(x, y, ecart, null,4))
        lesCases.add(Case(x, y, ecart, null,5))
        lesCases.add(Case(x, y, ecart, lesPieces[3],6))
        lesCases.add(Case(x, y, ecart, lesPieces[4],7))
        lesCases.add(Case(x, y, ecart, lesPieces[5],8))
    }
}


_________________________________________________________________________________________________________________________________________________________



package com.example.petitechec

import android.content.Context
import android.content.res.Resources
import android.graphics.*
import android.graphics.Color.BLUE
import android.graphics.Color.RED
import android.util.AttributeSet

class Echiquier (var resource: Resources, var x: Float, var y: Float, var ecart: Float, view: DrawingView) {
    val bordure = 30F
    var nbPaint = Paint()
    var contPaint = Paint()
    var casePaint = Paint()
    var lesCases = ArrayList<Case>()
    var lesPieces = ArrayList<Piece?>()
    var r = view
    init {
        //* Initialise une liste de pièces ainsi qu'une liste de cases *//
        lesPieces.add(Roi(resource,0,0,0,0,true,r ))
        lesPieces.add(Cavalier(resource,0,0,0,0,true,r ))
        lesPieces.add(Tour(resource,0,0,0,0,true,r ))
        lesPieces.add(Tour(resource,0,0,0,0,false,r ))
        lesPieces.add(Cavalier(resource,0,0,0,0,false,r ))
        lesPieces.add(Roi(resource,0,0,0,0,false,r ))
        lesCases.add(Case(x, y, ecart, lesPieces[0],1))
        lesCases.add(Case(x, y, ecart, lesPieces[1],2))
        lesCases.add(Case(x, y, ecart, lesPieces[2],3))
        lesCases.add(Case(x, y, ecart, null,4))
        lesCases.add(Case(x, y, ecart, null,5))
        lesCases.add(Case(x, y, ecart, lesPieces[3],6))
        lesCases.add(Case(x, y, ecart, lesPieces[4],7))
        lesCases.add(Case(x, y, ecart, lesPieces[5],8))
    }

    fun draw(canvas: Canvas){
        //* Dessine l'échiquier (composé de cases), les pièces (s'il y en a) ainsi que le numéro de case *//
        updateCases()
        contPaint.color = Color.argb(255,47, 27, 12)
        canvas.drawRect(x - bordure, y - bordure, x + ecart + bordure , y + (8 * ecart) + bordure,contPaint)
        for (i in 0 until 8){
            val j = 8 - i
            val case = lesCases[i]
            val carre = RectF(case.x, case.y, case.x + case.ecart, case.y + case.ecart)
            if (i % 2 != 0) {
                casePaint.color = Color.argb(255,91, 60, 17)
            }
            else{
                casePaint.color = Color.argb(255,200, 173, 127)
            }
            case.draw(canvas, carre, casePaint)
            case.piece?.draw(canvas)
            nbPaint.color = Color.WHITE
            canvas.drawText(j.toString(),case.x + 5F, case.y + ecart - 15F, nbPaint)
            canvas.rotate(180F, case.x + (ecart / 2),case.y + (ecart / 2))
            canvas.drawText(j.toString(), case.x + 5F, case.y + ecart - 15F, nbPaint)
            canvas.rotate(180F, case.x + (ecart / 2),case.y + (ecart / 2))
        }
    }

    fun updateCases(){
        //* Met à jour la liste de cases *//
        listeCases(x, y, ecart)
    }

    fun listeCases(x: Float,y: Float,ecart: Float) {
        //* Donne les bonnes coordonnées aux cases et aux pièces (s'il y en a) *//
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

    fun getNrCase(x: Float, y: Float) : Int {
        //* Prend la position du clic et vérifie si elle correspond à une case, si oui, renvoie le numéro de la case, sinon, renvoie -1 *//
        var case : Case
        for (i in 0 until lesCases.size) {
            case = lesCases[i]
            val carre = RectF(case.x, case.y, case.x + case.ecart, case.y + case.ecart)
            if (carre.contains(x, y))
                return i
        }
        return -1
    }

    fun setPiece(i: Int, j: Int) {
        //* Exécute le déplacement de la pièce sélectionnée *//
        lesCases[j].piece = lesCases[i].piece
        lesCases[i].piece = null
    }

    fun isRoi(): ArrayList<Piece?> {
        //* Renvoie une liste contenant le(s) roi(s) encore présent sur l'échiquier *//
        val roi = ArrayList<Piece?>()
        for (i in 0 until 8)
            if (lesCases[i].piece == lesPieces[0] || lesCases[i].piece == lesPieces[5])
                roi.add(lesCases[i].piece)
        return roi
    }

    fun resetLesCases(){
        //* Replace les pièces à leur position de début de partie *//
        lesCases.clear()
        lesCases.add(Case(x, y, ecart, lesPieces[0],1))
        lesCases.add(Case(x, y, ecart, lesPieces[1],2))
        lesCases.add(Case(x, y, ecart, lesPieces[2],3))
        lesCases.add(Case(x, y, ecart, null,4))
        lesCases.add(Case(x, y, ecart, null,5))
        lesCases.add(Case(x, y, ecart, lesPieces[3],6))
        lesCases.add(Case(x, y, ecart, lesPieces[4],7))
        lesCases.add(Case(x, y, ecart, lesPieces[5],8))
    }
}
