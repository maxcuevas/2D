package game.Camera

import game.Map.MapChunk
import game.Map.MapTile
import game.Map.Obstruction

class ObstructionDrawer(private val obstructionVisibility: ObstructionVisibility, private val obstructionMover: ObstructionMover) {

    fun drawMap(mapChunks: List<MapChunk>, gameScreenWidth: Double, gameScreenHeight: Double, offsetAndDeltaY: Double, offsetAndDeltaX: Double) {
        mapChunks.forEach { mapChunk -> drawMapTiles(mapChunk.mapTiles, offsetAndDeltaY, offsetAndDeltaX) }
        mapChunks.forEach { mapChunk -> drawItems(mapChunk.items, offsetAndDeltaY, offsetAndDeltaX) }
        mapChunks.forEach { mapChunk -> setMapTileVisibility(mapChunk.mapTiles, gameScreenWidth, gameScreenHeight) }
        mapChunks.forEach { mapChunk -> setItemVisibility(mapChunk.items, gameScreenWidth, gameScreenHeight) }
    }


    private fun drawMapTiles(mapTiles: List<Obstruction>, offsetAndDeltaY: Double, offsetAndDeltaX: Double) {
        mapTiles
                .forEach { mapTile ->
                    obstructionMover.move(
                            mapTile,
                            offsetAndDeltaY,
                            offsetAndDeltaX)
                }
    }

    private fun drawItems(items: List<Obstruction>, offsetAndDeltaY: Double, offsetAndDeltaX: Double) {
        items
                .forEach { item ->
                    obstructionMover.move(
                            item,
                            offsetAndDeltaY,
                            offsetAndDeltaX)
                }
    }

    private fun setItemVisibility(items: List<Obstruction>, gameScreenWidth: Double, gameScreenHeight: Double) {
        val itemsToHide: List<Obstruction> = items.filter { item -> !obstructionVisibility.isVisible(item.node, gameScreenWidth, gameScreenHeight) }
        itemsToHide.map { mapTile -> mapTile.node.isVisible = false }
    }

    private fun setMapTileVisibility(mapTiles: List<MapTile>, gameScreenWidth: Double, gameScreenHeight: Double) {
        val mapTilesToHide: List<MapTile> = mapTiles.filter { mapTile -> !obstructionVisibility.isVisible(mapTile.node, gameScreenWidth, gameScreenHeight) }
        mapTilesToHide.map { mapTile -> mapTile.node.isVisible = false }
    }

}
