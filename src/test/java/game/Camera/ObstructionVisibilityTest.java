package game.Camera;


import javafx.scene.Node;
import javafx.scene.shape.Rectangle;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;


public class ObstructionVisibilityTest {

    private ObstructionVisibility subject;
    private double width;
    private double height;

    @Before
    public void setUp() {
        subject = new ObstructionVisibility();
        height = 10;
        width = 10;
    }

    @Test
    public void setObstruction_givenNodeInScreen_returnTrue() {
        Node node = new Rectangle(1.0, 1.0, 1.0, 1.0);
        subject.setVisibility(node, width, height);

        assertTrue(node.isVisible());
    }

    @Test
    public void setObstruction_givenNodeLeftOfScreen_returnFalse() {
        Node node = new Rectangle(-1.0, 1.0, 1.0, 1.0);
        subject.setVisibility(node, width, height);
        assertFalse(node.isVisible());
    }

    @Test
    public void setObstruction_givenNodeAboveScreen_returnFalse() {
        Node node = new Rectangle(1.0, -1.0, 1.0, 1.0);
        subject.setVisibility(node, width, height);
        assertFalse(node.isVisible());
    }

    @Test
    public void setObstruction_givenNodeRightOfScreen_returnFalse() {
        Node node = new Rectangle(11.0, 1.0, 1.0, 1.0);
        subject.setVisibility(node, width, height);
        assertFalse(node.isVisible());
    }

    @Test
    public void setObstruction_givenNodeBelowScreen_returnFalse() {
        Node node = new Rectangle(1.0, 11.0, 1.0, 1.0);
        subject.setVisibility(node, width, height);

        assertFalse(node.isVisible());
    }


}