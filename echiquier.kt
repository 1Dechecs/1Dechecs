import android.graphics.*

class Echiquier (var Xi: Float, var Yi: Float, var ecart: Float, var width: Float, var view: DrawingView) {

    val CIBLE_PIECES = 8
    val cible = RectF(Xi, Yi,
        Xi + width, ecart)
    var cibleTouchee = BooleanArray(CIBLE_PIECES)
    val ciblePaint = Paint()
    val nbPaint = Paint()
    val contPaint = Paint()
    var longueurPiece = 0f

    fun draw(canvas: Canvas) {
        val currentPoint = PointF()
        currentPoint.x = cible.left
        currentPoint.y = cible.top
        nbPaint.color = Color.WHITE
        contPaint.color = Color.argb(255,47, 27, 12)
        canvas.drawRect(Xi - (longueurPiece / 6), Yi - (longueurPiece / 6), Xi + longueurPiece + (longueurPiece / 5), Yi + (8 * longueurPiece) + (longueurPiece / 6), contPaint)
        for (i in 0 until CIBLE_PIECES) {
            if (!cibleTouchee[i]) {
                if (i % 2 != 0)
                    ciblePaint.color = Color.argb(255,91, 60, 17)
                else
                    ciblePaint.color = Color.argb(255,200, 173, 127)
                canvas.drawRect(currentPoint.x,currentPoint.y,cible.right,
                    currentPoint.y+longueurPiece,ciblePaint)
                nbPaint.setTextSize(40F)
                var j = 8 - i
                canvas.drawText(j.toString(), currentPoint.x + (longueurPiece / 12), currentPoint.y + (9 * longueurPiece / 10), nbPaint)
                canvas.rotate(180F,currentPoint.x + (longueurPiece / 2),currentPoint.y + (longueurPiece / 2))
                canvas.drawText(j.toString(), currentPoint.x + (longueurPiece / 20), currentPoint.y + (9 * longueurPiece / 10), nbPaint)
                canvas.rotate(180F,currentPoint.x + (longueurPiece / 2),currentPoint.y + (longueurPiece / 2))
            }
            currentPoint.y += longueurPiece
        }
    }

    fun setRect() {
        cible.set(Xi, Yi,
            Xi + width, ecart)
        longueurPiece = (ecart - Yi) / CIBLE_PIECES
    }
}



______________________________________________________________________________________________________________________________________

class Echiquier (var Xi: Float, var Yi: Float, var ecart: Float, var width: Float, var view: DrawingView) {

    val CIBLE_PIECES = 8
    val cible = RectF(Xi, Yi,
        Xi + width, ecart)
    var cibleTouchee = BooleanArray(CIBLE_PIECES)
    val ciblePaint = Paint()
    val nbPaint = Paint()
    val contPaint = Paint()
    var longueurPiece = 0f

    fun draw(canvas: Canvas) {
        val currentPoint = PointF()
        currentPoint.x = cible.left
        currentPoint.y = cible.top
        nbPaint.color = Color.WHITE
        contPaint.color = Color.argb(255,47, 27, 12)
        canvas.drawRect(Xi - (longueurPiece / 6), Yi - (longueurPiece / 6), Xi + longueurPiece + (longueurPiece / 5), Yi + (8 * longueurPiece) + (longueurPiece / 6), contPaint)
        for (i in 0 until CIBLE_PIECES) {
            if (!cibleTouchee[i]) {
                if (i % 2 != 0)
                    ciblePaint.color = Color.argb(255,91, 60, 17)
                else
                    ciblePaint.color = Color.argb(255,200, 173, 127)
                canvas.drawRect(currentPoint.x,currentPoint.y,cible.right,
                    currentPoint.y+longueurPiece,ciblePaint)
                nbPaint.setTextSize(50F)
                var j = i + 1
                canvas.drawText(j.toString(), currentPoint.x + (longueurPiece / 12), currentPoint.y + (longueurPiece / 3), nbPaint)
            }
            currentPoint.y += longueurPiece
        }
    }

    fun setRect() {
        cible.set(Xi, Yi,
            Xi + width, ecart)
        longueurPiece = (ecart - Yi) / CIBLE_PIECES
    }
}


-----------------------------------------------------------------------------------------------------------------------------------

import android.graphics.*

class Echiquier (var Xi: Float, var Yi: Float, var ecart: Float, var width: Float, var view: DrawingView) {

    val CIBLE_PIECES = 8
    val cible = RectF(Xi, Yi,
        Xi + width, ecart)
    var cibleTouchee = BooleanArray(CIBLE_PIECES)
    val ciblePaint = Paint()
    val nbPaint = Paint()
    val contPaint = Paint()
    var longueurPiece = 0f

    fun draw(canvas: Canvas) {
        val currentPoint = PointF()
        currentPoint.x = cible.left
        currentPoint.y = cible.top
        nbPaint.color = Color.WHITE
        contPaint.color = Color.BLACK
        canvas.drawRect(Xi - (longueurPiece / 6), Yi - (longueurPiece / 6), Xi + longueurPiece + (longueurPiece / 5), Yi + (8 * longueurPiece) + (longueurPiece / 6), contPaint)
        for (i in 0 until CIBLE_PIECES) {
            if (!cibleTouchee[i]) {
                if (i % 2 != 0)
                    ciblePaint.color = Color.DKGRAY
                else
                    ciblePaint.color = Color.GRAY
                canvas.drawRect(currentPoint.x,currentPoint.y,cible.right,
                    currentPoint.y+longueurPiece,ciblePaint)
                nbPaint.setTextSize(50F)
                var j = i + 1
                canvas.drawText(j.toString(), currentPoint.x + (longueurPiece / 12), currentPoint.y + (longueurPiece / 3), nbPaint)
            }
            currentPoint.y += longueurPiece
        }
    }

    fun setRect() {
        cible.set(Xi, Yi,
            Xi + width, ecart)
        longueurPiece = (ecart - Yi) / CIBLE_PIECES
    }
}

_______________________________________________________________________________________________________________________________________


import android.graphics.*

class Echiquier (var Xi: Float, var Yi: Float, var ecart: Float, var width: Float, var view: DrawingView) {

    val CIBLE_PIECES = 8
    val cible = RectF(Xi, Yi,
            Xi + width, ecart)
    var cibleTouchee = BooleanArray(CIBLE_PIECES)
    val ciblePaint = Paint()
    var longueurPiece = 0f

    fun draw(canvas: Canvas) {
        val currentPoint = PointF()
        currentPoint.x = cible.left
        currentPoint.y = cible.top
        for (i in 0 until CIBLE_PIECES) {
            if (!cibleTouchee[i]) {
                if (i % 2 != 0)
                    ciblePaint.color = Color.BLUE
                else
                    ciblePaint.color = Color.RED
                canvas.drawRect(currentPoint.x,currentPoint.y,cible.right,
                        currentPoint.y+longueurPiece,ciblePaint)
            }
            currentPoint.y += longueurPiece
        }
    }

    fun setRect() {
        cible.set(Xi, Yi,
                Xi + width, ecart)
        longueurPiece = (ecart - Yi) / CIBLE_PIECES
    }
}


------------------------------------------------------------------------------------------------------------------------

package com.example.echecier

import android.graphics.*
import android.view.SurfaceView

class case (var Xi: Float, var Yi: Float, var ecart : Float) {

    val nb_case = 8
    val casePaint = Paint()
    val nbPaint = Paint()
    val contPaint = Paint()

    fun draw(canvas: Canvas) {
        nbPaint.color = Color.WHITE
        contPaint.color = Color.BLACK
        var X = Xi
        var Y = Yi
        val bordure = ecart / 8
        canvas.drawRect(Xi - bordure, Yi - bordure, Xi + ecart + bordure, Yi + (8 * ecart) + bordure, contPaint)
        for (i in 0 until nb_case) {
            if (i % 2 != 0)
                casePaint.color = Color.BLUE
            else
                casePaint.color = Color.GRAY
            canvas.drawRect(X,Y,X + ecart, Y + ecart,casePaint)
            canvas.drawText(i.toString(), X, Y, nbPaint)
        }
        Y += ecart
    }
}
