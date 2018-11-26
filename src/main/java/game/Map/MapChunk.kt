package game.Map

class MapChunk(
        val minX: Double,
        val minY: Double,
        val obstructions: List<Obstruction>,
        val tileItems: List<Obstruction>
) {


    private val tileLength: Int = 15
    private val chunkLength: Int = 5

    val sideLength = tileLength * chunkLength
    val maxX: Double = minX + sideLength
    val maxY: Double = minY + sideLength

    fun getTile(i: Int): Obstruction {
        return obstructions[i]
    }

    fun getObstructionCount(): Int {
        return obstructions.size
    }

}