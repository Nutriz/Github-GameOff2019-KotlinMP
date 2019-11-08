package fr.nutriz.gameoff2019

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.util.AttributeSet
import android.view.SurfaceHolder
import android.view.SurfaceView
import android.view.View
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


//FIXME not working for the moment

class GameSurfaceView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : SurfaceView(context, attrs, defStyleAttr), SurfaceHolder.Callback {

    private var doAnimate = true

    private val surfaceViewScope = CoroutineScope(Dispatchers.Default)

    private val debuggerPaint = Paint(Paint.ANTI_ALIAS_FLAG).apply {
        color = Color.BLUE
    }

//    private val drawAnimate by lazy {
//        DrawAnimate(height, width)
//    }

    init {
        holder.addCallback(this)
    }

    override fun surfaceChanged(holder: SurfaceHolder, format: Int, width: Int, height: Int) {
        // Do nothing for now
    }

    override fun surfaceDestroyed(holder: SurfaceHolder) {
        doAnimate = false
//        surfaceViewScope.cancel()
    }

    override fun surfaceCreated(holder: SurfaceHolder) {
        doAnimate = true
        surfaceViewScope.launch {
            while (doAnimate && isAttachedToWindow) {
                synchronized(holder) {
                    val canvas = holder.lockCanvas()
                    canvas?.let {
                        canvas.drawCircle(100f, 0f, 100f, debuggerPaint)
                        holder.unlockCanvasAndPost(it)
                    }
                }
            }
        }
    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)

        val desiredWidth = suggestedMinimumWidth + paddingLeft + paddingRight
        val desiredHeight = suggestedMinimumHeight + paddingTop + paddingBottom

        setMeasuredDimension(
            View.resolveSize(desiredWidth, widthMeasureSpec),
            View.resolveSize(desiredHeight, heightMeasureSpec)
        )
    }
}
