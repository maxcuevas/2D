package sample;

import com.sun.org.apache.xpath.internal.operations.Bool;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

public class Map implements Render {

    ArrayList<ArrayList<Boolean>> map;
    private double width = 10;
    private double height = 10;
    private double XPos = 0;
    private double YPos = 50;
    private double mapWidth = 150;
    private double mapHeight = 150;

    public Map() {
        this.map = createMap();
    }

    private ArrayList<ArrayList<Boolean>> createMap() {

        ArrayList<ArrayList<Boolean>> mapLayout = new ArrayList<>();
        for (int row = 0; row < mapHeight; row += height) {
            ArrayList<Boolean> mapRow = new ArrayList<>();
            for (int column = 0; column < mapWidth; column += width) {
                if ((int)(row/10)%2 == 0){
                    mapRow.add(true);
                    mapRow.add(false);
                }
                else {
                    mapRow.add(false);
                    mapRow.add(true);
                }
            }
            mapLayout.add(mapRow);
        }

        return mapLayout;
    }


    public void render(GraphicsContext gameScreen, long deltaTime) {

        double XOffset = XPos;
        double YOffset = YPos;

        for (ArrayList<Boolean> row : map) {
            for (Boolean item : row) {

                if (item) {
                    gameScreen.setFill(Color.GREEN);
                } else {
                    gameScreen.setFill(Color.YELLOW);
                }
                XOffset += width;
                gameScreen.fillRect(XOffset, YOffset, width, height);
            }
            YOffset += height;
            XOffset = XPos;
        }


    }

}
