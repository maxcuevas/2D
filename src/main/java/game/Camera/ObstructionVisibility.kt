package game.Camera

import javafx.geometry.Rectangle2D
import java.util.*

class ObstructionVisibility {
    fun isVisible(node: Rectangle2D, gameScreenWidth: Double, gameScreenHeight: Double): Optional<Rectangle2D> {
        if (areNodeXsInBounds(node, gameScreenWidth) && areNodeYsInBounds(node, gameScreenHeight)) {
            return Optional.of(node)
        }
        return Optional.empty()
    }

    private fun areNodeXsInBounds(node: Rectangle2D, gameScreenWidth: Double): Boolean {
        return node.minX > 0 &&
                gameScreenWidth > node.maxX
    }

    private fun areNodeYsInBounds(node: Rectangle2D, gameScreenHeight: Double): Boolean {
        return node.minY > 0 &&
                gameScreenHeight > node.maxY
    }
}
