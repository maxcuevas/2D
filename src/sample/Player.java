package sample;

import javafx.scene.Node;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

import java.awt.geom.Rectangle2D;
import java.util.Set;


public class Player implements IRender {
    private final int width = 10;
    private final int height = 10;
    private Rectangle2D.Double bounds;
    private Node view = new Rectangle(width, height, Color.BLACK);
    Movement movement = new Movement();
    Velocity velocity = new Velocity(1.75, 80);


    public Player(double XPosition, double YPosition) {
        bounds = new Rectangle2D.Double(XPosition, YPosition, width, height);
    }

    public void render(Pane gameScreen) {
        gameScreen.getChildren().add(view);
    }

    @Override
    public void update() {
        view.toFront();
    }

    public void moveX(Map map, long deltaTime) {
        double newPositionX = movement.getNewPositionX(map, deltaTime, bounds, velocity.getVelocityX());
        bounds.setRect(newPositionX, bounds.getY(), bounds.getWidth(), bounds.getHeight());
    }

    public void moveY(Map map, long deltaTime) {
        double newPositionY = movement.getNewPositionY(map, deltaTime, bounds, velocity.getVelocityY());
        bounds.setRect(bounds.getX(), newPositionY, bounds.getWidth(), bounds.getHeight());
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

    public double getX() {
        return bounds.getX();
    }

    public double getY() {
        return bounds.getY();
    }


    public Node getView() {
        return view;
    }

}
