package game.Map

import javafx.scene.Node

import java.awt.geom.Rectangle2D

interface IObstruction {

    val isObstruction: Boolean
    val bounds: Rectangle2D.Double
    val node: Node

}
