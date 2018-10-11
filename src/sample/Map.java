package sample;

import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Shape;

import java.util.ArrayList;

public class Map implements IRender {

    public ArrayList<ArrayList<MapTile>> map;
    private final double width = 10;
    private final double height = 10;
    private double XPos = 0;
    private double YPos = 50;
    private double mapSquareWidthCount = 10;
    private double mapSquareHeightCount = 10;


    public Map() {
        this.map = createMap();
    }

    private ArrayList<ArrayList<MapTile>> createMap() {

        ArrayList<ArrayList<MapTile>> mapLayout = new ArrayList<>();
        for (int row = 0; row < mapSquareHeightCount; row++) {
            ArrayList<MapTile> mapRow = new ArrayList<>();
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

        for (ArrayList<MapTile> row : map) {
            for (MapTile item : row) {
                XOffset += width;
                item.mapTile.setTranslateX(XOffset);
                item.mapTile.setTranslateY(YOffset);
                if (((Shape) (item.mapTile)).getFill().equals(Color.GREEN)) {
                    gameScreen.getChildren().add(item.mapTile);
                } else {
                    gameScreen.getChildren().add(item.mapTile);
                }
            }
            YOffset += height;
            XOffset = XPos;
        }

        Stone stone = new Stone();

        stone.stone.setCenterX(20);
        stone.stone.setCenterY(20);

        gameScreen.getChildren().add(stone.stone);



    }

}
