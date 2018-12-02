package game.Map

import java.awt.geom.Rectangle2D

open class ObstructionImpl(
        override val isObstruction: Boolean = false,
        override val bounds: Rectangle2D.Double = Rectangle2D.Double(0.0, 0.0, 0.0, 0.0)
) : Obstruction

