package game.Map

class MapChunk(
        val minX: Double = 0.0,
        val minY: Double = 0.0,
        val mapTiles: List<MapTile> = listOf(),
        val items: List<ObstructionNoNode> = listOf()
) {


    private val tileLength: Int = 15
    private val chunkLength: Int = 5

    val sideLength = tileLength * chunkLength
    val maxX: Double = minX + sideLength
    val maxY: Double = minY + sideLength

    fun getTile(i: Int): MapTile {
        return mapTiles[i]
    }

    fun getTileCount(): Int {
        return mapTiles.size
    }

}