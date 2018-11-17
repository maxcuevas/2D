package game.Entity

class Velocity(private val sprintFactor: Double, private val baseVelocity: Double) {

    var velocityX = 0.0
        private set

    var velocityY = 0.0
        private set

    fun setVelocityYPos() {
        velocityY = baseVelocity
    }

    fun setVelocityXPos() {
        velocityX = baseVelocity
    }

    fun setVelocityYNeg() {
        velocityY = -baseVelocity
    }

    fun setVelocityXNeg() {
        velocityX = -baseVelocity
    }

    fun resetVelocityY() {
        velocityY = 0.0
    }

    fun resetVelocityX() {
        velocityX = 0.0
    }

    fun clearVelocities() {
        resetVelocityX()
        resetVelocityY()
    }

    fun setRunning() {
        velocityX *= sprintFactor
        velocityY *= sprintFactor
    }
}
