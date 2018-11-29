package game.Map

import javafx.scene.Node
import javafx.scene.shape.Rectangle

import java.awt.geom.Rectangle2D

open class ObstructionImpl(
        override val isObstruction: Boolean = false,
        override val bounds: Rectangle2D.Double = Rectangle2D.Double(0.0, 0.0, 0.0, 0.0),
        override val node: Node = Rectangle(0.0, 0.0, 0.0, 0.0)) : Obstruction

