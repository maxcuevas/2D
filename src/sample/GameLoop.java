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

    public GameLoop(){}

    public void setCanvas(Canvas canvas) {
        graphicsContext = canvas.getGraphicsContext2D();
        height = canvas.getHeight();
        width = canvas.getWidth();
        character = new Block();
    }

    @Override
    public void run() {


        while (true) {

            character.clearVelocities();

            if (keysDown.contains("s")) {
                character.setVelocityYPos();
            }
            if (keysDown.contains("w")) {
                character.setVelocityYNeg();
            }
            if (keysDown.contains("a")) {
                character.setVelocityXNeg();
            }
            if (keysDown.contains("d")) {
                character.setVelocityXPos();
            }

            character.render(graphicsContext, fps.getElapsedTime());


            fps.setTimeStart();


            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            fps.setTimeEnd();


            clearScreen();
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
