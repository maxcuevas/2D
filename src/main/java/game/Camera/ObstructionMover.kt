package game.Camera

import javafx.geometry.Rectangle2D

class ObstructionMover {

    fun getNodeY(rectangle: Rectangle2D, offsetAndDeltaY: Double): Double {
        return rectangle.minY + offsetAndDeltaY
    }

    fun getNodeX(rectangle: Rectangle2D, offsetAndDeltaX: Double, offsetAndDeltaY: Double): Rectangle2D {
        return Rectangle2D(rectangle.minX + offsetAndDeltaX, rectangle.minY + offsetAndDeltaY, rectangle.width, rectangle.height)
    }


}
