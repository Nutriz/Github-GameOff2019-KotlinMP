package engine

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.util.AttributeSet
import android.util.Log
import android.view.MotionEvent
import android.view.SurfaceHolder
import android.view.SurfaceView
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.cancel
import kotlinx.coroutines.launch

class GameSurfaceView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : SurfaceView(context, attrs, defStyleAttr), SurfaceHolder.Callback {

    private var mustQuit = false
    // manage the lifecycle of the coroutine with the game loop in it
    private val surfaceViewScope = CoroutineScope(Dispatchers.Default)

    private val player = GameEngine.player
    private val blocks = GameEngine.blocks

    private val debuggerPaint = Paint(Paint.ANTI_ALIAS_FLAG or Paint.DITHER_FLAG).apply {
        color = Color.BLACK
        style = Paint.Style.FILL
    }
    private val playerPaint = Paint(Paint.ANTI_ALIAS_FLAG or Paint.DITHER_FLAG).apply {
        color = Color.BLUE
        style = Paint.Style.FILL
    }

    init {
        holder.addCallback(this)
        setOnTouchListener { _, event ->
            val touchEvent = if (event.action == MotionEvent.ACTION_DOWN) TouchEvent.Type.TOUCH_DOWN else null
            if (touchEvent != null && player.canJump) {
                GameEngine.touchHandle(event.x, event.y, touchEvent)
            }
            true
        }
    }

    override fun surfaceCreated(holder: SurfaceHolder) {
        mustQuit = false
        surfaceViewScope.launch {
            while (!mustQuit && isAttachedToWindow) {
                synchronized(holder) {
                    val canvas = holder.lockCanvas()
                    canvas?.let {
                        canvas.drawColor(Color.LTGRAY)
                        drawBlocks(canvas)
                        drawPlayer(canvas)
                        GameEngine.updateGame()
                        checkCollisions()
                        holder.unlockCanvasAndPost(it)
                    }
                }
            }
        }
    }

    private fun drawBlocks(canvas: Canvas) {
        for (b in blocks) {
            canvas.drawRect(b.x, b.y, b.x + b.width.toFloat(), b.y + b.height.toFloat(), debuggerPaint)
        }
    }

    private fun drawPlayer(canvas: Canvas) {
        canvas.drawRect(player.x, player.y, player.x + player.width.toFloat(), player.y + player.height.toFloat(), playerPaint)
    }

    private fun checkCollisions() {
        for (b in blocks) {
            if (b.collision(player)) {
                player.canJump = true
                player.y = b.y - player.width
            } else {
                player.canJump = false
            }
        }
    }

    override fun surfaceChanged(holder: SurfaceHolder, format: Int, width: Int, height: Int) {
        Log.d("GameSurfaceView", "surfaceChanged:")
    }

    override fun surfaceDestroyed(holder: SurfaceHolder) {
        mustQuit = true
        surfaceViewScope.cancel()
    }
}