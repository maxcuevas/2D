package sample;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;

import java.util.HashSet;
import java.util.Random;
import java.util.Set;

public class testRunnable implements Runnable {

    public GraphicsContext graphicsContext;
    private double timePerFame = 16;
    private double width;
    private double height;
    Block character;
    public static Set<String> keysDown = new HashSet<>();

    public testRunnable(Canvas canvas) {

        this.graphicsContext = canvas.getGraphicsContext2D();
        height = canvas.getHeight();
        width = canvas.getWidth();
        character = new Block();
    }

    @Override
    public void run() {


        while (true) {


            if (keysDown.contains("s")) {

                character.render(graphicsContext);

            }


//            long timeBefore = System.currentTimeMillis();

            FPS.setTimeStart();

            Random random = new Random();
            int max = 10;
            int min = 5;

            try {
                Thread.sleep(random.nextInt((max - min) + 1) + min);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

//            long timeLater = System.currentTimeMillis();

//            double elapsedTime = timeLater - timeBefore;

//            long waitTime = (long) (timePerFame - elapsedTime);

//            try {
//                Thread.sleep(waitTime);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }

//            long timeNow = System.currentTimeMillis();

//            int framePerSecond = (int) (1 / ((timeNow - timeBefore) * 1e-3));


            graphicsContext.setFill(Color.WHITE);
            graphicsContext.fillRect(0, 0, width, height);

            FPS.setTimeEnd();
            FPS.renderFPS(graphicsContext);
//            graphicsContext.setFill(Color.BLACK);
//            graphicsContext.fillText("FPS: " + framePerSecond, 20, 20);



        }
    }
}
