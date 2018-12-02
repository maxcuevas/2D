package game.Camera;


import game.Map.Obstruction;
import game.Map.ObstructionImpl;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

import static org.assertj.core.api.Assertions.assertThat;


@RunWith(MockitoJUnitRunner.class)
public class ObstructionMoverTest {

    private ObstructionMover subject;

    private Obstruction obstruction;

    @Before
    public void setUp() {
        subject = new ObstructionMover();
        obstruction = new ObstructionImpl();
    }

    @Test
    public void move_givenValueForY_returnObstructionsNodeMovedY() {
        double deltaY = 10.0;
        double actual = subject.getNodeY(obstruction.getBounds(), deltaY);
        assertThat(actual).isEqualTo(deltaY);
    }

    @Test
    public void move_givenValueForX_returnObstructionsNodeMovedX() {
        double deltaX = 10.0;
        double actual = subject.getNodeX(obstruction.getBounds(), deltaX);
        assertThat(actual).isEqualTo(deltaX);
    }
}