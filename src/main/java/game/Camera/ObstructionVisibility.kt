package game.Camera

import javafx.scene.Node

class ObstructionVisibility {
    fun isVisible(node: Node, gameScreenWidth: Double, gameScreenHeight: Double): Boolean {
        return areNodeXsInBounds(node, gameScreenWidth) && areNodeYsInBounds(node, gameScreenHeight)
    }

    private fun areNodeXsInBounds(node: Node, gameScreenWidth: Double): Boolean {
        return node.boundsInParent.minX > 0 &&
                gameScreenWidth > node.boundsInParent.maxX
    }

    private fun areNodeYsInBounds(node: Node, gameScreenHeight: Double): Boolean {
        return node.boundsInParent.minY > 0 &&
                gameScreenHeight > node.boundsInParent.maxY
    }
}
