package fr.nutriz.gameoff2019

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.util.AttributeSet
import android.view.View

class GameView @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : View(context, attrs, defStyleAttr) {

    private val debugPainter = Paint(Paint.ANTI_ALIAS_FLAG)

    override fun onDraw(canvas: Canvas?) {
        canvas?.let {
            it.drawColor(Color.WHITE)
            it.drawCircle(100f, 100f, 100f, debugPainter)
            it.drawCircle(300f, 100f, 100f, debugPainter)
        }
    }
}