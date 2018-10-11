package sample;

import javafx.scene.input.KeyCode;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

import java.util.Set;


public class Character implements IRender {
    private final int width = 10;
    private final int height = 10;
    Rectangle character = new Rectangle(width, height, Color.BLACK);
    Movement movement = new Movement();
    Velocity velocity = new Velocity(1.75, 50);


    public Character(double XPosition, double YPosition) {
        character.setX(XPosition);
        character.setY(YPosition);
    }

    public void render(Pane gameScreen) {
        gameScreen.getChildren().add(character);
        character.toFront();
    }

    public void moveX(Map map, long deltaTime) {
        movement.moveX(map, deltaTime, character, velocity.getVelocityX());
    }

    public void moveY(Map map, long deltaTime) {
        movement.moveY(map, deltaTime, character, velocity.getVelocityY());
    }


    public void readInput(Set<KeyCode> keysDown) {

        if (keysDown.contains(KeyCode.S)) {
            velocity.setVelocityYPos();
        }
        if (keysDown.contains(KeyCode.W)) {
            velocity.setVelocityYNeg();
        }
        if (keysDown.contains(KeyCode.A)) {
            velocity.setVelocityXNeg();
        }
        if (keysDown.contains(KeyCode.D)) {
            velocity.setVelocityXPos();
        }
        if (keysDown.contains(KeyCode.SHIFT)) {
            velocity.setRunning();
        }


    }


    public void resetSpeed() {
        velocity.clearVelocities();
    }

}
