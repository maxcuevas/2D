package game.Camera

import javafx.scene.shape.Rectangle

class ObstructionMover {

    fun getNodeY(rectangle: Rectangle, offsetAndDeltaY: Double): Double {
        return rectangle.y + offsetAndDeltaY
    }

    fun getNodeX(rectangle: Rectangle, offsetAndDeltaX: Double): Double {
        return rectangle.x + offsetAndDeltaX
    }


}
