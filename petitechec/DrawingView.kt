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
    var k = -1
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
            echiquier.draw(canvas, k)
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
            i = echiquier.getNrCase(x, y, canvas)
            if (cases.size == 0 && i != -1 && couleurTour == lesCases[i].piece?.couleur) {
                cases.add(i)
                k = i
            }
            else if (cases.size == 1 && i != -1 && couleurTour != lesCases[i].piece?.couleur){
                cases.add(i)
                k = -1
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
