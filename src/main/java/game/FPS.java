package game;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class FPS {

    private long timeStart;
    private long timeEnd;
    private final long xPos = 20;
    private final long yPos = 20;
    public final boolean isCapped = true;
    private final double fps60 = 1000 / 60;

    public FPS() {
        timeStart = System.currentTimeMillis();
        timeEnd = System.currentTimeMillis();
    }

    public void setTimeStart() {

        timeStart = System.currentTimeMillis();
    }

    public void setTimeEnd() {
        timeEnd = System.currentTimeMillis();
        delayTime();
    }

    private void delayTime() {
        if (isCapped) {
            try {
                Thread.sleep(getWaitTime());
                timeEnd = System.currentTimeMillis();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }
    }

    private long getWaitTime() {
        return (long) (getElapsedTime() > fps60 ? 0 : fps60 - getElapsedTime());
    }

    public long getElapsedTime() {
        return timeEnd - timeStart;
    }


    public void renderFPS(GraphicsContext graphicsContext) {
        graphicsContext.setFill(Color.BLACK);
        graphicsContext.fillText("game.FPS: " + getFramePerSecond(), xPos, yPos);
    }

    private int getFramePerSecond() {
        return (int) (1e3 / getElapsedTime());
    }


}
