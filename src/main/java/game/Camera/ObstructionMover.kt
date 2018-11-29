package game.Camera

import game.Map.Obstruction
import java.awt.geom.Rectangle2D

class ObstructionMover {

    fun move(obstruction: Obstruction, offsetAndDeltaY: Double, offsetAndDeltaX: Double) {
        obstruction.node.translateX = getNodeX(obstruction.bounds, offsetAndDeltaX)
        obstruction.node.translateY = getNodeY(obstruction.bounds, offsetAndDeltaY)
    }

    private fun getNodeY(bounds: Rectangle2D.Double, offsetAndDeltaY: Double): Double {
        return bounds.getY() + offsetAndDeltaY
    }

    private fun getNodeX(bounds: Rectangle2D.Double, offsetAndDeltaX: Double): Double {
        return bounds.getX() + offsetAndDeltaX
    }


}
