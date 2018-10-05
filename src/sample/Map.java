package sample;

import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

import java.util.ArrayList;

public class Map implements Render {

    ArrayList<ArrayList<Rectangle>> map;
    private final double width = 10;
    private final double height = 10;
    private double XPos = 0;
    private double YPos = 50;
    private double mapSquareWidthCount = 10;
    private double mapSquareHeightCount = 10;


    public Map() {
        this.map = createMap();
    }

    private ArrayList<ArrayList<Rectangle>> createMap() {

        ArrayList<ArrayList<Rectangle>> mapLayout = new ArrayList<>();
        for (int row = 0; row < mapSquareHeightCount; row++) {
            ArrayList<Rectangle> mapRow = new ArrayList<>();
            for (int column = 0; column < mapSquareWidthCount; column++) {
                if (row % 2 == 0) {
                    mapRow.add(new Rectangle(width, height, Color.YELLOW));
                    mapRow.add(new Rectangle(width, height, Color.GREEN));
                }
                else {
                    mapRow.add(new Rectangle(width, height, Color.GREEN));
                    mapRow.add(new Rectangle(width, height, Color.YELLOW));
                }
            }
            mapLayout.add(mapRow);
        }

        return mapLayout;
    }


    public void render(Pane gameScreen) {


        double XOffset = XPos;
        double YOffset = YPos + 100;

        for (ArrayList<Rectangle> row : map) {
            for (Rectangle item : row) {
                XOffset += width;
                item.setX(XOffset);
                item.setY(YOffset);
                if (item.getFill().equals(Color.GREEN)) {
                    gameScreen.getChildren().add(item);
                } else {
                    gameScreen.getChildren().add(item);
                }
            }
            YOffset += height;
            XOffset = XPos;
        }


    }

}
