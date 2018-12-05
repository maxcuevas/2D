package game.Camera

import javafx.geometry.Rectangle2D

class ObstructionDrawer(private val obstructionVisibility: ObstructionVisibility, private val obstructionMover: ObstructionMover) {
    fun draw(mapTilesAndItems: List<Rectangle2D>, gameScreenWidth: Double, gameScreenHeight: Double, offsetAndDeltaY: Double, offsetAndDeltaX: Double): List<Rectangle2D> {
        val s = setNodesVisibility(mapTilesAndItems, gameScreenWidth, gameScreenHeight)
        return moveNodes(s, offsetAndDeltaY, offsetAndDeltaX)
    }

    private fun moveNodes(nodes: List<Rectangle2D>, offsetAndDeltaY: Double, offsetAndDeltaX: Double): List<Rectangle2D> {
        return nodes
                .map { obstruction ->
                    obstructionMover.getNodeX(
                            obstruction,
                            offsetAndDeltaX, offsetAndDeltaY)
                }

    }

    private fun setNodesVisibility(nodes: List<Rectangle2D>, gameScreenWidth: Double, gameScreenHeight: Double): List<Rectangle2D> {
        val map = nodes.map { node -> obstructionVisibility.isVisible(node, gameScreenWidth, gameScreenHeight) }

        return map.map { x -> x.get() }
    }
}
