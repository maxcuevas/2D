package game.Map

import javafx.scene.input.MouseEvent
import javafx.scene.paint.Color
import javafx.scene.shape.Circle
import javafx.scene.shape.Shape
import java.awt.geom.Rectangle2D

class StoneFactory {

    private val width: Double = 5.0
    private val height: Double = 5.0

    fun create(x: Double, y: Double): Stone {
        val circle = Circle(5.0, Color.SLATEGRAY)
        circle.stroke = Color.BLACK
        circle.strokeWidth = 0.0
        setEvents(circle)
        return Stone(ObstructionImplNoNode(false, Rectangle2D.Double(x, y, width, height)))
    }


    private fun setEvents(item: Shape) {
        item.addEventHandler(MouseEvent.MOUSE_ENTERED,
                { event -> item.strokeWidth = 2.0 })

        item.addEventHandler(MouseEvent.MOUSE_CLICKED,
                { event -> item.fill = Color.BLACK })

        item.addEventHandler(MouseEvent.MOUSE_EXITED,
                { event -> item.strokeWidth = 0.0 })


    }


}