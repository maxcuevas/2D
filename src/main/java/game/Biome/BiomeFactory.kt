package game.Biome

import game.Map.MapTile
import game.Map.MapTileFactory
import java.util.stream.Collectors
import java.util.stream.IntStream


class BiomeFactory(private val mapTileFactory: MapTileFactory) {


    fun getBiome(biomeType: BiomeType, minX: Double, minY: Double, chunkLength: Int, tileLength: Int): List<MapTile> {
        return createBiome(getBiomeProbabilities(biomeType), minX, minY, chunkLength, tileLength)
    }

    private fun getBiomeProbabilities(biomeType: BiomeType): IBiomeProbabilities {
        return if (biomeType == BiomeType.PLAIN) {
            Plain()
        } else {
            Desert()
        }
    }


    private fun createBiome(iBiomeProbabilities: IBiomeProbabilities, minX: Double, minY: Double, chunkLength: Int, tileLength: Int): List<MapTile> {
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
    ): List<MapTile> {

        return IntStream
                .range(0, chunkLength)
                .mapToObj { count ->
                    mapTileFactory.create(false,
                            xPos + tileSize * row,
                            yPos + tileSize * count,
                            tileSize)
                }
                .collect(Collectors.toList())
    }


}