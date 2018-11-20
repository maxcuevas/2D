package game.Map

import javafx.scene.Node
import java.awt.*
import java.awt.geom.Rectangle2D

class MapTileData(var tile: Rectangle,
                  isObstruction: Boolean,
                  rectangle: Rectangle2D.Double,
                  node: Node)
    : ObstructionData(isObstruction, rectangle, node){
    val tileWidth: Int = 15
    val tileHeight: Int = 15

}




