package game.Map

class MapChunk(
        val minX: Double,
        val minY: Double,
        val chunk: List<Obstruction>) {


    private val tileLength: Int = 15
    private val chunkLength: Int = 5

    val sideLength = tileLength * chunkLength
    val maxX: Double = minX + sideLength
    val maxY: Double = minY + sideLength

    fun getTile(i: Int): Obstruction {
        return chunk[i]
    }

    fun getObstructionCount(): Int {
        return chunk.size
    }

}