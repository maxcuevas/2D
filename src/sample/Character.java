package sample;

import javafx.scene.input.KeyCode;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

import java.util.Set;


public class Character implements Render {

    private double x;
    private double y;
    private final int width = 10;
    private final int height = 10;
    Rectangle character = new Rectangle(width, height, Color.BLACK);


    private int velocityX = 0;
    private int velocityY = 0;
    private double sprintFactor = 1.75;
    private int baseVelocity = 50;

    public Character() {
        character.setX(x);
        character.setY(y);
    }


    public void render(Pane gameScreen) {

        gameScreen.getChildren().add(character);
        character.toFront();

    }

    public void move(long deltaTime) {
        x += velocityX * (deltaTime * 1e-3);
        y += velocityY * (deltaTime * 1e-3);
        character.setX(x);
        character.setY(y);
        character.toFront();
    }


    public void setVelocityYPos() {
        velocityY = baseVelocity;
    }

    public void setVelocityXPos() {
        velocityX = baseVelocity;
    }

    public void setVelocityYNeg() {
        velocityY = -baseVelocity;
    }

    public void setVelocityXNeg() {
        velocityX = -baseVelocity;
    }

    public void resetVelocityY() {
        velocityY = 0;
    }

    public void resetVelocityX() {
        velocityX = 0;
    }

    public void clearVelocities() {
        resetVelocityX();
        resetVelocityY();
    }

    private void setRunning() {
        velocityX *= sprintFactor;
        velocityY *= sprintFactor;
    }

    public void readInput(Set<KeyCode> keysDown) {

        if (keysDown.contains(KeyCode.S)) {
            setVelocityYPos();
        }
        if (keysDown.contains(KeyCode.W)) {
            setVelocityYNeg();
        }
        if (keysDown.contains(KeyCode.A)) {
            setVelocityXNeg();
        }
        if (keysDown.contains(KeyCode.D)) {
            setVelocityXPos();
        }
        if (keysDown.contains(KeyCode.SHIFT)) {
            setRunning();
        }


    }


}
