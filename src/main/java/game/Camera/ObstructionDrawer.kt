package game.Camera

import javafx.scene.Node
import javafx.scene.shape.Rectangle

class ObstructionDrawer(private val obstructionVisibility: ObstructionVisibility, private val obstructionMover: ObstructionMover) {
    fun draw(mapTilesAndItems: List<Rectangle>, gameScreenWidth: Double, gameScreenHeight: Double, offsetAndDeltaY: Double, offsetAndDeltaX: Double) {
        moveNodes(mapTilesAndItems, offsetAndDeltaY, offsetAndDeltaX)
        setNodesVisibility(mapTilesAndItems, gameScreenWidth, gameScreenHeight)
    }

    private fun moveNodes(nodes: List<Rectangle>, offsetAndDeltaY: Double, offsetAndDeltaX: Double) {
        nodes
                .forEach { obstruction ->
                    obstruction.x =
                    obstructionMover.getNodeX(
                            obstruction ,
                            offsetAndDeltaX)
                }

        nodes
                .forEach { obstruction ->
                    obstruction.y =
                    obstructionMover.getNodeY(
                            obstruction ,
                            offsetAndDeltaY)
                }
    }

    private fun setNodesVisibility(nodes: List<Node>, gameScreenWidth: Double, gameScreenHeight: Double) {
        nodes.map { node -> node.isVisible =  obstructionVisibility.isVisible(node, gameScreenWidth, gameScreenHeight) }
    }
}
