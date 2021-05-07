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




___________________________________________________________________________________________________________________________________________________________





package com.example.petitechec

import android.app.AlertDialog
import android.app.Dialog
import android.content.Context
import android.content.DialogInterface
import android.content.res.Resources
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.RectF
import android.os.Bundle
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.SurfaceHolder
import android.view.SurfaceView
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.FragmentActivity

class DrawingView @JvmOverloads constructor (context: Context, attributes: AttributeSet? = null, defStyleAttr: Int = 0): SurfaceView(context, attributes,defStyleAttr), SurfaceHolder.Callback, Runnable {
    lateinit var canvas: Canvas
    val backgroundPaint = Paint()
    var screenWidth = 0f
    var screenHeight = 0f
    var drawing = false
    var resource = resources
    var echiquier = Echiquier(resource,0f,0f,0f,this)
    var couleurTour = false
    var cases = ArrayList<Int>()
    var gameOver = false
    var couleur = ""
    val activity = context as FragmentActivity
    lateinit var thread: Thread
    init {
        backgroundPaint.color = Color.argb(255,145,96,48)
    }

    fun pause() {
        drawing = false
        thread.join()
    }

    fun resume() {
        drawing = true
        thread = Thread(this)
        thread.start()
    }

    override fun run() {
        while (drawing) {
            draw()
        }
    }

    fun draw(){
        //* Dessine le fond ainsi que tout ce que contient l'échiquier *//
        if (holder.surface.isValid) {
            canvas = holder.lockCanvas()
            canvas.drawRect(0f, 0f, canvas.width.toFloat(), canvas.height.toFloat(), backgroundPaint)
            echiquier.draw(canvas)
            holder.unlockCanvasAndPost(canvas)
        }
    }

    override fun onSizeChanged(w:Int, h:Int, oldw:Int, oldh:Int) {
        //* Ajuste les dimensions en fonction de la taille de l'écran *//
        super.onSizeChanged(w, h, oldw, oldh)
        screenWidth = w.toFloat()
        screenHeight = h.toFloat()
        echiquier.x = (w  / 2f)
        echiquier.y = (h / 65f)
        echiquier.ecart = (h * 4/ 33f)
        echiquier.updateCases()
        echiquier.nbPaint.textSize = (h / 35f)
    }

    override fun onTouchEvent(e: MotionEvent): Boolean{
        //* Effectue l'action lors du clic, ajoute les numéros des deux cases sélectionnées (si elle peuvent l'être) *//
        val i : Int
        if (e.action == MotionEvent.ACTION_DOWN) {
            val x = e.rawX
            val y = e.rawY - 250
            val lesCases = echiquier.lesCases
            i = echiquier.getNrCase(x, y)
            if (cases.size == 0 && i != -1 && couleurTour == lesCases[i].piece?.couleur) {
                cases.add(i)
            }
            else if (cases.size == 1 && i != -1 && couleurTour != lesCases[i].piece?.couleur){
                cases.add(i)
            }
            if (cases.size == 2) {
                if (lesCases[cases[0]].piece?.isPossible(lesCases[cases[0]], lesCases[cases[1]], lesCases) == true)
                    jouerTour()
                else
                    cases.clear()
            }
        }
        invalidate()
        return true
    }

    fun jouerTour() {
        //* Appelle le déplacement de la pièce, change la couleur du tour et regarde combien de roi il reste (arrête la partie s'il n'en reste qu'un) *//
        echiquier.setPiece(cases[0], cases[1])
        couleurTour = !couleurTour
        cases.clear()
        if (echiquier.isRoi().size == 1){
            if (echiquier.isRoi()[0]?.couleur == true)
                couleur = "noir"
            else
                couleur = "blanc"
            gameOver()
        }
    }

    fun gameOver() {
        //* Arrête la partie et appelle le message de fin adapté *//
        drawing = false
        if (couleur == "noir")
            showGameOverDialog(R.string.noir)
        else if (couleur == "blanc")
            showGameOverDialog(R.string.blanc)
        else
            showGameOverDialog(R.string.pat)
        gameOver = true
    }

    fun showGameOverDialog(messageId: Int) {
        //* Fait apparaître le message de fin, permet aussi de relancer une partie *//
        class GameResult: DialogFragment() {
            override fun onCreateDialog(bundle: Bundle?): Dialog {
                val builder = AlertDialog.Builder(getActivity())
                builder.setTitle(resources.getString(messageId))
                builder.setPositiveButton(R.string.reset_game,
                        DialogInterface.OnClickListener { _, _->newGame()}
                )
                return builder.create()
            }
        }

        activity.runOnUiThread(
                Runnable {
                    val ft = activity.supportFragmentManager.beginTransaction()
                    val prev =
                            activity.supportFragmentManager.findFragmentByTag("dialog")
                    if (prev != null) {
                        ft.remove(prev)
                    }
                    ft.addToBackStack(null)
                    val gameResult = GameResult()
                    gameResult.setCancelable(false)
                    gameResult.show(ft,"dialog")
                }
        )
    }

    fun newGame() {
        //* Relance une partie en réinitialisant tout *//
        echiquier.resetLesCases()
        drawing = true
        if (gameOver) {
            gameOver = false
            couleurTour = false
            thread = Thread(this)
            thread.start()
            couleur = ""
        }
    }

    override fun surfaceChanged(holder: SurfaceHolder, format: Int,
                                width: Int, height: Int) {}

    override fun surfaceCreated(holder: SurfaceHolder) {}

    override fun surfaceDestroyed(holder: SurfaceHolder) {}
}
