package game.Map

import java.awt.geom.Rectangle2D

interface Obstruction {
    val isObstruction: Boolean
    val bounds: Rectangle2D.Double
}
