package game;

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


        boolean mapChange = map.updateMap(player.getX(), player.getY());


        player.update();
        camera.updateCamera(player, map, mapChange);
    }

    public void setGameScreen(Pane gameScreen) {
        map = new Map();
        map.render(gameScreen);
        player = new Player(0, 0);
        player.render(gameScreen);
        camera = new Camera(gameScreen, player);
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
