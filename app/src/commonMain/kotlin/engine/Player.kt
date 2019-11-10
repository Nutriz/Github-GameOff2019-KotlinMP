package engine

class Player(
    x: Float,
    y: Float,
    width: Int,
    height: Int
) : GameObject(x, y, width, height) {

    private val gravity = 100
    private val mass = 30
    private var velocityY = 0.0
    private var timeStep = 0.2
    var canJump = false

    override fun draw(canvas: Any?) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    fun jump() {
        velocityY += -300
        canJump = false
    }

    override fun update() {

        if (canJump) {
            velocityY = 0.0
            timeStep = 0.0
        } else
            timeStep = 0.2

        // FORCE CALCULATIONS
        val forceY = mass * gravity
        val accelerationY = forceY / mass
        velocityY += accelerationY * timeStep
        y += (velocityY * timeStep).toFloat()
    }
}