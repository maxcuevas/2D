package game.Camera

import javafx.scene.Node

class ObstructionDrawer(private val obstructionVisibility: ObstructionVisibility, private val obstructionMover: ObstructionMover) {
    fun getMapTilesAndItems(mapTilesAndItems: List<Node>, gameScreenWidth: Double, gameScreenHeight: Double, offsetAndDeltaY: Double, offsetAndDeltaX: Double) {


        setNodesVisibility(mapTilesAndItems, offsetAndDeltaY, offsetAndDeltaX)

//        mapTilesAndItems.forEach { mapChunk -> moveNodes(mapChunk.items, offsetAndDeltaY, offsetAndDeltaX) }
//        mapTilesAndItems.forEach { mapChunk -> setNodesVisibility(mapChunk.mapTiles, gameScreenWidth, gameScreenHeight) }
//        mapTilesAndItems.forEach { mapChunk -> setNodesVisibility(mapChunk.items, gameScreenWidth, gameScreenHeight) }
//
//        mapTilesAndItems.forEach { mapChunk -> mapChunk.items.forEach { item -> item.node.toFront() } }
    }

//    private fun moveNodes(nodes: List<Node>, : List<> offsetAndDeltaY: Double, offsetAndDeltaX: Double) {
//        nodes
//                .forEach { obstruction ->
//                    obstruction.translateX
//                    obstructionMover.getNodeX(
//                            obstruction,
//                            offsetAndDeltaX)
//                }
//        nodes
//                .forEach { obstruction ->
//                    obstruction.translateY
//                    obstructionMover.getNodeY(
//                            obstruction,
//                            offsetAndDeltaY)
//                }
//    }

    private fun setNodesVisibility(nodes: List<Node>, gameScreenWidth: Double, gameScreenHeight: Double) {
        nodes.map { node -> obstructionVisibility.isVisible(node, gameScreenWidth, gameScreenHeight) }
    }
}
