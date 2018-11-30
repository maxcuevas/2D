package game.Camera;

import game.Map.MapChunk;
import game.Map.MapTile;
import game.Map.Obstruction;
import game.Map.ObstructionImpl;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;


@RunWith(MockitoJUnitRunner.class)
public class ObstructionDrawerTest {

    double width = 10;
    double height = 10;
    private ObstructionDrawer subject;
    @Mock
    private ObstructionVisibility mockObstructionVisibility;
    @Mock
    private ObstructionMover mockObstructionMover;

    @Before
    public void setUp() {
//        ObstructionVisibility max = mock(ObstructionVisibility.class);
//        ObstructionMover max1 = mock(ObstructionMover.class);

        subject = new ObstructionDrawer(mockObstructionVisibility, mockObstructionMover);
    }

    @Test
    public void drawMap_givenMapChunk_thenVerifyCommandsAreCalled() {
        List<MapChunk> mapChunks = new ArrayList<>();

        ObstructionImpl obstruction = new ObstructionImpl();
        List<Obstruction> obstructions = new ArrayList<>();
        obstructions.add(obstruction);

        MapTile mapTile = new MapTile(obstruction);
        List<MapTile> mapTiles = new ArrayList<>();
        mapTiles.add(mapTile);

        MapChunk e = new MapChunk(0.0, 0.0, mapTiles, obstructions);
        mapChunks.add(e);

        subject.drawMap(mapChunks, 0, 0, 0, 0);

    }

}