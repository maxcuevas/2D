package game.Map

import javafx.scene.paint.Color
import javafx.scene.shape.Rectangle
import java.awt.geom.Rectangle2D

class StoneFactory {

    private val width: Double = 25.0
    private val height: Double = 25.0

    fun create(x: Double, y: Double): Stone {
        val node = Rectangle(x, y, width, height)
        node.fill = Color.GRAY

        return Stone(ObstructionImpl(false, Rectangle2D.Double(x, y, width, height), node))
    }
}