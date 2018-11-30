package game.Map

import game.Biome.BiomeFactory
import game.Biome.BiomeType
import java.util.stream.Collectors
import java.util.stream.IntStream
import kotlin.random.Random

class MapChunkFactory(private val biomeFactory: BiomeFactory, private val stoneFactory: StoneFactory = StoneFactory()) {

    private val tileLength = 15
    private val chunkLength = 5

    private fun create(minX: Double, minY: Double, mapTiles: List<MapTile>, mapItems: List<Obstruction>): MapChunk {


        return MapChunk(minX, minY, mapTiles, mapItems)
    }


    fun newPlainsChunk(minX: Double, minY: Double): MapChunk {
        val mapTiles = biomeFactory.getBiome(BiomeType.PLAIN, minX, minY, chunkLength, tileLength)

        val mapTilesThatStonesWillBeMadeOn = mapTiles.filter { Random.nextInt(0, 10000) < 300 }

        val collect = IntStream.range(0, mapTilesThatStonesWillBeMadeOn.size)
                .mapToObj { count -> stoneFactory.create(mapTilesThatStonesWillBeMadeOn[count].obstruction.bounds.x, mapTilesThatStonesWillBeMadeOn[count].obstruction.bounds.y) }
                .collect(Collectors.toList())

        return create(minX, minY, mapTiles, collect)
    }

    fun newDesertChunk(minX: Double, minY: Double): MapChunk {
        val mapTiles = biomeFactory.getBiome(BiomeType.DESERT, minX, minY, chunkLength, tileLength)

        return create(minX, minY, mapTiles, listOf())
    }


}
