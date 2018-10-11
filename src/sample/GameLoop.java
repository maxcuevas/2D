package sample;

import javafx.animation.AnimationTimer;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.Pane;

import java.util.HashSet;
import java.util.Set;

public class GameLoop extends AnimationTimer {

    private final FPS fps = new FPS();
    private double width;
    private double height;
    Character character;
    public final Set<KeyCode> keysDown = new HashSet<>();
    private static boolean isRunning = false;
    private Map map;
    private Pane gameScreen;

    public GameLoop() {
    }

    @Override
    public void handle(long now) {
        fps.setTimeStart();


        character.resetSpeed();
        character.readInput(keysDown);

        fps.setTimeEnd();

        character.moveX(map, fps.getElapsedTime());
        character.moveY(map, fps.getElapsedTime());
    }

    public void setGameScreen(Pane gameScreen) {
        this.gameScreen = gameScreen;
        map = new Map();
        map.render(gameScreen);
        character = new Character(0, 0);
        character.render(gameScreen);
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
