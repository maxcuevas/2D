package game.Camera

import javafx.scene.Node

class ObstructionVisibility {

    fun setVisibility(node: Node, gameScreenWidth: Double, gameScreenHeight: Double) {
        node.isVisible = !isVisible(node, gameScreenWidth, gameScreenHeight)
    }

    private fun isVisible(node: Node, gameScreenWidth: Double, gameScreenHeight: Double): Boolean {
        return node.boundsInParent.minX < 0 ||
                gameScreenWidth < node.boundsInParent.maxX ||
                node.boundsInParent.minY < 0 ||
                gameScreenHeight < node.boundsInParent.maxY
    }
}
