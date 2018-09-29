package sample;

import javafx.scene.canvas.GraphicsContext;

import java.util.Set;


public class Block {


    private double x;
    private double y;
    private int w = 10;
    private int h = 10;

    private int velocityX = 0;
    private int velocityY = 0;
    private int baseVelocity = 50;

    public Block() {
        x = 10;
        y = 10;
    }


    public void render(GraphicsContext gameScreen, long deltaTime) {
        x += velocityX * (deltaTime * 1e-3);
        y += velocityY * (deltaTime * 1e-3);
        gameScreen.fillRect(x, y, w, h);
    }


    public void setVelocityYPos() {
        this.velocityY = baseVelocity;
    }

    public void setVelocityXPos() {
        this.velocityX = baseVelocity;
    }

    public void setVelocityYNeg() {
        this.velocityY = -baseVelocity;
    }

    public void setVelocityXNeg() {
        this.velocityX = -baseVelocity;
    }

    public void resetVelocityY() {
        this.velocityY = 0;
    }

    public void resetVelocityX() {
        this.velocityX = 0;
    }

    public void clearVelocities(){
        resetVelocityX();
        resetVelocityY();
    }

    public void readInput(Set<String> keysDown) {

        if (keysDown.contains("s")) {
            setVelocityYPos();
        }
        if (keysDown.contains("w")) {
            setVelocityYNeg();
        }
        if (keysDown.contains("a")) {
            setVelocityXNeg();
        }
        if (keysDown.contains("d")) {
            setVelocityXPos();
        }
    }
}
