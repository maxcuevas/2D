package game.Map

import javafx.scene.paint.Color
import javafx.scene.shape.Circle
import java.awt.geom.Rectangle2D

class StoneFactory {

    private val width: Double = 5.0
    private val height: Double = 5.0

    fun create(x: Double, y: Double): Stone {
        return Stone(ObstructionImpl(false, Rectangle2D.Double(x, y, width, height), Circle(5.0, Color.SLATEGRAY)))
    }
}