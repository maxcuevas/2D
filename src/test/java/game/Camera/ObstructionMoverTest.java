package game.Camera;


import game.Map.ObstructionImpl;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

import static org.assertj.core.api.Assertions.assertThat;


@RunWith(MockitoJUnitRunner.class)
public class ObstructionMoverTest {

    private ObstructionMover subject;

    private ObstructionImpl obstruction;

    @Before
    public void setUp() {
        subject = new ObstructionMover();
        obstruction = new ObstructionImpl();
    }

    @Test
    public void move_givenValueForY_returnObstructionsNodeMovedY() {
        double deltaY = 10.0;
        subject.move(obstruction, deltaY, 0.0);
        assertThat(obstruction.getNode().getTranslateY()).isEqualTo(10);
    }

    @Test
    public void move_givenValueForX_returnObstructionsNodeMovedX() {
        double deltaX = 10.0;
        subject.move(obstruction, 0.0, deltaX);
        assertThat(obstruction.getNode().getTranslateX()).isEqualTo(10);
    }
}