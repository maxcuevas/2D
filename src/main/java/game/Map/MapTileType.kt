package game.Map

import javafx.scene.paint.Color

enum class MapTileType(val color: Color) {
    GRASS(Color.GREEN),
    STONE(Color.GRAY),
    DIRT(Color.BROWN),
    WATER(Color.BLUE),
    SAND(Color.SANDYBROWN),
    UNKNOWN(Color.YELLOW)
}