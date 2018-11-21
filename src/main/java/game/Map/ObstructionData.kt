package game.Map

import javafx.scene.Node

import java.awt.geom.Rectangle2D

open class ObstructionData(var isObstruction: Boolean,
                           val bounds: Rectangle2D.Double,
                           var node: Node) {

}

