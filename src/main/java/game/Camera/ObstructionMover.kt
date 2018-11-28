package game.Camera

import game.Map.Obstruction

class ObstructionMover {

    fun move(obstruction: Obstruction, offsetAndDeltaY: Double, offsetAndDeltaX: Double) {
        moveX(obstruction, offsetAndDeltaX)
        moveY(obstruction, offsetAndDeltaY)
    }

    private fun moveY(obstruction: Obstruction, offsetAndDeltaY: Double) {
        obstruction.node.translateY = obstruction.bounds.getY() + offsetAndDeltaY
    }

    private fun moveX(obstruction: Obstruction, offsetAndDeltaX: Double) {
        obstruction.node.translateX = obstruction.bounds.getX() + offsetAndDeltaX
    }


}
