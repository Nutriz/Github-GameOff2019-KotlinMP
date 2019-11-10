package engine

/**
 * Base class for an object represented into the game
 *
 * @param x absolute position in x
 * @param y absolute position in y
 * @param width width of the object
 * @param height height of the object
 */
abstract class GameObject(
    var x: Float = 0f,
    var y: Float = 0f,
    var width: Int = 10,
    var height: Int = 10
) {

    // not used for the moment
    abstract fun draw(canvas: Any?)

    /**
     * Checks if this object is colliding with an other
     *
     * @param other the other game object to checks with
     * @return tue if the two objects are colliding
     */
    fun collision(other: GameObject) : Boolean {
        return !(other.x >= x + width
                || other.y >= y + height
                || other.x + width <= x
                || other.y + other.height <= y)
    }

    /**
     * To update data like position, states...into the object
     */
    abstract fun update()
}