package game.Map

class MapChunkData(
        val minX: Double,
        val minY: Double,
        val chunk: List<Obstruction>) {


    private val tileLength: Int = 15
    private val chunkLength: Int = 5

    val sideLength = tileLength * chunkLength
    val tileCount: Int = chunk.size
    val biomeLength: Int = sideLength
    val maxX: Double = minX + sideLength
    val maxY: Double = minY + sideLength
    var inRange: Boolean = false

    fun getTile(i: Int): Obstruction {
        return chunk[i]
    }

}