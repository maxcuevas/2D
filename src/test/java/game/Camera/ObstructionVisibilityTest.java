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
    public void setVisibility_givenNodeInScreen_returnTrue() {
        Node node = new Rectangle(1.0, 1.0, 1.0, 1.0);
        assertTrue(subject.isVisible(node, width, height));
    }

    @Test
    public void setVisibility_givenNodeLeftOfScreen_returnFalse() {
        Node node = new Rectangle(-1.0, 1.0, 1.0, 1.0);
        assertFalse(subject.isVisible(node, width, height));
    }

    @Test
    public void setVisibility_givenNodeAboveScreen_returnFalse() {
        Node node = new Rectangle(1.0, -1.0, 1.0, 1.0);
        assertFalse(subject.isVisible(node, width, height));
    }

    @Test
    public void setVisibility_givenNodeRightOfScreen_returnFalse() {
        Node node = new Rectangle(11.0, 1.0, 1.0, 1.0);
        assertFalse(subject.isVisible(node, width, height));
    }

    @Test
    public void setVisibility_givenNodeBelowScreen_returnFalse() {
        Node node = new Rectangle(1.0, 11.0, 1.0, 1.0);
        assertFalse(subject.isVisible(node, width, height));
    }


}