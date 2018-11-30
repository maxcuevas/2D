package game.Camera

import game.Map.MapChunk
import game.Map.Obstruction

class ObstructionDrawer(private val obstructionVisibility: ObstructionVisibility, private val obstructionMover: ObstructionMover) {

    fun drawMap(mapChunks: List<MapChunk>, gameScreenWidth: Double, gameScreenHeight: Double, offsetAndDeltaY: Double, offsetAndDeltaX: Double) {
        mapChunks.forEach { mapChunk -> moveObstructions(mapChunk.mapTiles, offsetAndDeltaY, offsetAndDeltaX) }
        mapChunks.forEach { mapChunk -> moveObstructions(mapChunk.items, offsetAndDeltaY, offsetAndDeltaX) }
        mapChunks.forEach { mapChunk -> setObstructionsVisibility(mapChunk.mapTiles, gameScreenWidth, gameScreenHeight) }
        mapChunks.forEach { mapChunk -> setObstructionsVisibility(mapChunk.items, gameScreenWidth, gameScreenHeight) }

        mapChunks.forEach { mapChunk -> mapChunk.items.forEach { item -> item.node.toFront() } }
    }


    private fun moveObstructions(obstructions: List<Obstruction>, offsetAndDeltaY: Double, offsetAndDeltaX: Double) {
        obstructions
                .forEach { obstruction ->
                    obstructionMover.move(
                            obstruction,
                            offsetAndDeltaY,
                            offsetAndDeltaX)
                }
    }


    private fun setObstructionsVisibility(obstructions: List<Obstruction>, gameScreenWidth: Double, gameScreenHeight: Double) {
        obstructions.map { obstruction -> obstructionVisibility.setVisibility(obstruction.node, gameScreenWidth, gameScreenHeight) }
    }

}
