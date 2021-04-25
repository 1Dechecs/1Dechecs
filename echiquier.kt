import android.graphics.*

class Echiquier (var cibleDistance: Float, var cibleDebut: Float, var cibleFin: Float, var width: Float, var view: DrawingView) {

    val CIBLE_PIECES = 8
    val cible = RectF(cibleDistance, cibleDebut,
            cibleDistance + width, cibleFin)
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
        cible.set(cibleDistance, cibleDebut,
                cibleDistance + width, cibleFin)
        longueurPiece = (cibleFin - cibleDebut) / CIBLE_PIECES
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
