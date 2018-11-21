package game.Map

import java.awt.geom.Rectangle2D
import javafx.scene.shape.Rectangle


class MapTileData
    : ObstructionData {
    constructor(isObstruction: Boolean, x: Double, y:Double, tile: Rectangle)
            : super(isObstruction, Rectangle2D.Double(
            x, y, tile.width, tile.height
    ), tile)


}




