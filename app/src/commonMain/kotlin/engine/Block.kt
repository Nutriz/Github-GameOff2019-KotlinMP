package engine

/**
 * A simple block platform for the game
 */
class Block(x: Float, y: Float, width: Int = 150, height: Int = 50) : GameObject(x, y, width, height) {
    override fun update() {
        TODO("not implemented, block is static for the moment")
    }

    override fun draw(canvas: Any?) {
        TODO("not implemented, draw directly into SurfaceView")
    }
}
