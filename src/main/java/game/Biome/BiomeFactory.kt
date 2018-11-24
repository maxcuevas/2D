package game.Biome

import game.Map.MapTileFactory
import game.Map.Obstruction
import java.util.stream.Collectors
import java.util.stream.IntStream
import kotlin.random.Random.Default.nextInt

class BiomeFactory(private val mapTileFactory: MapTileFactory) {





    fun getNewChunkRow(iBiomeProbabilities: IBiomeProbabilities, row: Int, chunkLength: Int,
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