package sample;

import javafx.scene.Node;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;

import java.util.Set;


public class Character implements Render {
    private final int width = 10;
    private final int height = 10;
    Rectangle character = new Rectangle(width, height, Color.BLACK);


    private int velocityX = 0;
    private int velocityY = 0;
    private double sprintFactor = 1.75;
    private int baseVelocity = 50;

    public Character(double XPosition, double YPosition) {
        character.setX(XPosition);
        character.setY(YPosition);
    }

    public boolean checkCollision(Pane gameScreen) {

        for (Node node : gameScreen.getChildren()) {

            Shape shape = (Shape) node;
            Shape characterShape = (Shape) character;
            if (!characterShape.equals(shape)) {

                Shape myShape = Shape.intersect(characterShape, shape);

                if (character.getBoundsInParent().intersects(shape.getBoundsInParent())) {
                    int a = 0;
                    return true;
                }
            }
        }
        return false;
    }


    public void render(Pane gameScreen) {

        gameScreen.getChildren().add(character);
        character.toFront();

    }

    public void moveX(Pane gameScreen, long deltaTime) {
        double proposedMove = velocityX * (deltaTime * 1e-3);
        checkProposedMoveX(gameScreen, proposedMove);
        //then maybe we can just hit he wall but not go into it??
        character.toFront();
    }

    private void checkProposedMoveX(Pane gameScreen, double proposedMove) {
        character.setX(character.getX() + proposedMove);
        for (Node node : gameScreen.getChildren()) {
            if (isCollision(node)) {
                character.setX(character.getX() - proposedMove);
                return;
            }
        }
    }

    private boolean isCollision(Node node) {
        return !character.equals(node) && character.getBoundsInParent().intersects(node.getBoundsInParent());
    }

    public void moveY(Pane gameScreen, long deltaTime) {
        double proposedMove = velocityY * (deltaTime * 1e-3);
        checkProposedMoveY(gameScreen, proposedMove);
        character.toFront();
    }


    private void checkProposedMoveY(Pane gameScreen, double proposedMove) {
        character.setY(character.getY() + proposedMove);
        for (Node node : gameScreen.getChildren()) {
            if (isCollision(node)) {
                character.setY(character.getY() - proposedMove);
                return;
            }
        }
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
