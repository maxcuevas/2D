package sample;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;

import java.util.HashSet;
import java.util.Random;
import java.util.Set;

public class GameLoop implements Runnable {

    private final FPS fps = new FPS();
    public GraphicsContext graphicsContext;
    private double width;
    private double height;
    Block character;
    public final Set<String> keysDown = new HashSet<>();
    private static boolean isRunning = false;
    private Map map;

    public GameLoop(){
    }

    public void setCanvas(Canvas canvas) {
        graphicsContext = canvas.getGraphicsContext2D();
        height = canvas.getHeight();
        width = canvas.getWidth();
        character = new Block();
        map = new Map();
    }

    @Override
    public void run() {


        while (true) {
            fps.setTimeStart();


            character.clearVelocities();
            character.readInput(keysDown);


            fps.setTimeEnd();


            clearScreen();
              map.render(graphicsContext, 0);
            character.render(graphicsContext, fps.getElapsedTime());
            fps.renderFPS(graphicsContext);

        }
    }

    private void clearScreen() {
        graphicsContext.setFill(Color.WHITE);
        graphicsContext.fillRect(0, 0, width, height);
    }

    public void readInput(String input){
        keysDown.add(input);
    }

    public void clearInput(String input) {
        keysDown.remove(input);
    }

    public boolean isRunning() {
        return isRunning;
    }

    public void setRunning(boolean running) {
        isRunning = running;
    }
}
