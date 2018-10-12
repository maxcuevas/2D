package sample;

import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Shape;

import java.util.ArrayList;

public class Map implements IRender {

    public ArrayList<ArrayList<Obstruction>> map;
    private final double width = 10;
    private final double height = 10;
    private double XPos = 0;
    private double YPos = 50;
    private double mapSquareWidthCount = 10;
    private double mapSquareHeightCount = 10;


    public Map() {
        this.map = createMap();
    }

    private ArrayList<ArrayList<Obstruction>> createMap() {

        ArrayList<ArrayList<Obstruction>> mapLayout = new ArrayList<>();
        for (int row = 0; row < mapSquareHeightCount; row++) {
            ArrayList<Obstruction> mapRow = new ArrayList<>();
            for (int column = 0; column < mapSquareWidthCount; column++) {
                if (row % 2 == 0) {
                    mapRow.add(new MapTile(width, height, Color.YELLOW));
                    mapRow.add(new MapTile(width, height, Color.GREEN));
                }
                else {
                    mapRow.add(new MapTile(width, height, Color.GREEN));
                    mapRow.add(new MapTile(width, height, Color.YELLOW));
                }
            }
            mapLayout.add(mapRow);
        }

        return mapLayout;
    }



    public void render(Pane gameScreen) {


        double XOffset = XPos;
        double YOffset = YPos + 100;

        for (ArrayList<Obstruction> row : map) {
            for (Obstruction item : row) {
                XOffset += width;
                item.getNode().setTranslateX(XOffset);
                item.getNode().setTranslateY(YOffset);
                if (((Shape) (item.getNode())).getFill().equals(Color.GREEN)) {
                    gameScreen.getChildren().add(item.getNode());
                } else {
                    gameScreen.getChildren().add(item.getNode());
                }
            }
            YOffset += height;
            XOffset = XPos;
        }

        Stone stone = new Stone(false, 10, Color.GRAY);

        stone.getNode().setTranslateX(20);
        stone.getNode().setTranslateY(20);

        gameScreen.getChildren().add(stone.getNode());


        Wood wood = new Wood(false, 1, 10, Color.BROWN);

        wood.getNode().setTranslateX(50);
        wood.getNode().setTranslateY(20);

        gameScreen.getChildren().add(wood.getNode());



    }

}
