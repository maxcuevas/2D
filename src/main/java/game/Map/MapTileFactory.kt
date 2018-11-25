package game.Map

import javafx.scene.paint.Color
import javafx.scene.shape.Rectangle
import javafx.scene.shape.StrokeType
import java.awt.geom.Rectangle2D

class MapTileFactory {


    fun create(obstruction: Boolean, x: Double, y: Double, length: Double, mapTileType: MapTileType): MapTileData {
        return MapTileData(ObstructionImpl(obstruction, Rectangle2D.Double(x, y, length, length), createView(length, length, mapTileType)))
    }

    private fun createView(width: Double, height: Double, mapTileType: MapTileType): Rectangle {
        val tile = Rectangle(width, height)
        tile.fill = mapTileType.color
        tile.stroke = Color.BLACK
        tile.strokeType = StrokeType.INSIDE
        tile.strokeWidth = 0.0
        return tile
    }
}