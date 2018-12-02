package game.Map

import java.awt.geom.Rectangle2D

interface ObstructionNoNode {
    val isObstruction: Boolean
    val bounds: Rectangle2D.Double
}
