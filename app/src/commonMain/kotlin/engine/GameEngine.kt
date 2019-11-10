package engine

import kotlin.random.Random

// not used
data class TouchEvent (val type:Type) {
    enum class Type {
        TOUCH_DOWN,
        TOUCH_UP,
        TOUCH_MOVE
    }
}

/**
 * Object that holds data for the game
 */
object GameEngine {

    val player = Player(100f, 400f, 50, 50)
    val blocks = mutableListOf<Block>()

    init {
        generateBlocks()
    }

    /**
     * Called when platform specific have touch event
     */
    fun touchHandle(x: Float, y: Float, touchEvent: TouchEvent.Type?) {
        if (touchEvent == TouchEvent.Type.TOUCH_DOWN)
            player.jump()
    }

    private fun generateBlocks() {
        for (b in 0..50) {
            val y = 700 + Random.nextInt(0, 100).toFloat()
            val block = Block(300f * b, 700f)
            blocks.add(block)
        }
    }

    /**
     * Updates all objects data and states. Called for platform game loop
     */
    fun updateGame() {
        player.update()
    }
}