package game.Biome

import game.Map.MapTileFactory
import game.Map.Obstruction
import java.util.stream.Collectors
import java.util.stream.IntStream
import kotlin.random.Random.Default.nextInt


class BiomeFactory(private val mapTileFactory: MapTileFactory) {


    fun createBiome(biomeType: BiomeType, minX: Double, minY: Double, chunkLength: Int, tileLength: Int): List<Obstruction> {
        return if (biomeType == BiomeType.PLAIN) {
            getNewBiome(Plain(), minX, minY, chunkLength, tileLength)
        } else getNewBiome(Desert(), minX, minY, chunkLength, tileLength)
    }


    private fun getNewBiome(iBiomeProbabilities: IBiomeProbabilities, minX: Double, minY: Double, chunkLength: Int, tileLength: Int): List<Obstruction> {
        val listOfChunkRows = IntStream
                .range(0, chunkLength)
                .mapToObj { row ->
                    getNewChunkRow(iBiomeProbabilities, row, chunkLength,
                            minX, minY, tileLength.toDouble())
                }
                .collect(Collectors.toList())

        return listOfChunkRows
                .flatMap { it.asIterable() }
    }


    private fun getNewChunkRow(iBiomeProbabilities: IBiomeProbabilities, row: Int, chunkLength: Int,
                       xPos: Double, yPos: Double, tileSize: Double
    ): List<Obstruction> {
        val randomNumbers = List(chunkLength) { nextInt(0, 100) }

        return IntStream
                .range(0, chunkLength)
                .mapToObj { count ->
                    mapTileFactory.create(false,
                            xPos + tileSize * row,
                            yPos + tileSize* count,
                            tileSize,
                            iBiomeProbabilities.getBiomeTile(randomNumbers[count]))
                }
                .collect(Collectors.toList())
    }


}