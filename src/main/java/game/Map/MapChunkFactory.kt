package game.Map

import game.Biome.BiomeFactory
import game.Biome.BiomeType

class MapChunkFactory(private val biomeFactory: BiomeFactory) {

    private val tileLength = 15
    private val chunkLength = 5

    fun create(minX: Double, minY: Double, biomeType: BiomeType): MapChunk {
        return MapChunk(minX, minY, biomeFactory.getBiome(biomeType, minX, minY, chunkLength, tileLength))
    }


}
