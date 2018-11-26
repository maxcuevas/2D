package game.Map

import game.Biome.BiomeFactory
import game.Biome.BiomeType

class MapChunkFactory(private val biomeFactory: BiomeFactory, private val stoneFactory: StoneFactory = StoneFactory()) {

    private val tileLength = 15
    private val chunkLength = 5

    fun create(minX: Double, minY: Double, biomeType: BiomeType, mapItems: List<Obstruction>): MapChunk {
        val mapTiles = biomeFactory.getBiome(biomeType, minX, minY, chunkLength, tileLength)
        return MapChunk(minX, minY, mapTiles, mapItems)
    }


    fun newPlainsChunk(minX: Double, minY: Double): MapChunk {
        val stone = stoneFactory.create(minX + 10, minY + 10)
        return create(minX, minY, BiomeType.PLAIN, listOf(stone))
    }

    fun newDesert(minX: Double, minY: Double): MapChunk {
        return create(minX, minY, BiomeType.DESERT, listOf())
    }


}
