package game.Map

import javafx.scene.Node

import java.awt.geom.Rectangle2D

open class ObstructionImpl(
        override val isObstruction: Boolean,
        override val bounds: Rectangle2D.Double,
        override val node: Node) : Obstruction {

}

