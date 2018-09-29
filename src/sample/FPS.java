package sample;


import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class FPS {

    private static long timeStart;
    private static long timeEnd;
    private static final long xPos = 20;
    private static final long yPos = 20;
    public static final boolean frameCapped = true;

    private static final double frameCount = 60;

    public static void setTimeStart() {

        timeStart = System.currentTimeMillis();
    }

    public static void setTimeEnd() {
        timeEnd = System.currentTimeMillis();

    }

    private static long getTimeWait(){

        return (long) (getTimePerFrame() - getElapsedTime());

    }

    private static double getElapsedTime(){
        return timeEnd - timeStart;
    }

    private static double getTimePerFrame(){
        return 1/frameCount;
    }





    public static void renderFPS(GraphicsContext graphicsContext) {
        graphicsContext.setFill(Color.BLACK);
        graphicsContext.fillText("FPS: " + getFramePerSecond(), xPos, yPos);

    }

    private static int getFramePerSecond() {

        if (frameCapped){
            setTimeEnd();

        }

        return (int) (1 / ((timeEnd - timeStart) * 1e-3));
    }


}
