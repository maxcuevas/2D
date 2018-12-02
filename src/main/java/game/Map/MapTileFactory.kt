package game.Map

import java.awt.geom.Rectangle2D

class MapTileFactory {


    fun create(obstruction: Boolean, x: Double, y: Double, length: Double): MapTile {
        return MapTile(ObstructionImpl(obstruction, Rectangle2D.Double(x, y, length, length)))
    }

}