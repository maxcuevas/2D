package game;

import game.Biome.BiomeFactory;
import game.Camera.Camera;
import game.Camera.ObstructionDrawer;
import game.Camera.ObstructionMover;
import game.Camera.ObstructionVisibility;
import game.Entity.Player;
import game.Map.Map;
import game.Map.MapChunkFactory;
import game.Map.MapTileFactory;
import game.Map.StoneFactory;
import javafx.animation.AnimationTimer;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.Pane;

import java.util.HashSet;
import java.util.Set;

public class GameLoop extends AnimationTimer {

    private final FPS fps = new FPS();
    Player player;
    public final Set<KeyCode> keysDown = new HashSet<>();
    private static boolean isRunning = false;
    private Map map;
    private Camera camera;


    public GameLoop() {
    }

    @Override
    public void handle(long now) {
        fps.setTimeStart();


        player.resetSpeed();
        player.readInput(keysDown);

        fps.setTimeEnd();

        player.moveX(map, fps.getElapsedTime());
        player.moveY(map, fps.getElapsedTime());


        map.updateMap(player.getX(), player.getY());


        camera.updateCamera(player, map);
    }

    public void setGameScreen(Pane gameScreen) {
        map = new Map(new MapChunkFactory(new BiomeFactory(new MapTileFactory()), new StoneFactory()));
        player = new Player(10, 10);
        camera = new Camera(gameScreen, player, new ObstructionDrawer(new ObstructionVisibility(),new ObstructionMover()));
    }


    public void readInput(KeyCode input) {
        keysDown.add(input);
    }

    public void clearInput(KeyCode input) {
        keysDown.remove(input);
    }

    public boolean isRunning() {
        return isRunning;
    }

    public void setRunning(boolean running) {
        isRunning = running;
    }


}
