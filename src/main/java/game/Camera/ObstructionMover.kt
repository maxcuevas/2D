package game.Camera

import java.awt.geom.Rectangle2D

class ObstructionMover {

    fun getNodeY(bounds: Rectangle2D.Double, offsetAndDeltaY: Double): Double {
        return bounds.getY() + offsetAndDeltaY
    }

    fun getNodeX(bounds: Rectangle2D.Double, offsetAndDeltaX: Double): Double {
        return bounds.getX() + offsetAndDeltaX
    }


}
