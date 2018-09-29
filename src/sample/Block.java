package sample;

import javafx.scene.canvas.GraphicsContext;

public class Block {


    private int x;
    private int y;
    private int w = 10;
    private int h = 10;

    private int unitsPerSecond = 0;

    public Block() {

        x = 10;
        y = 10;

    }


    public void render(GraphicsContext gameScreen){


        gameScreen.fillRect(x,y,w,h);
    }


}
