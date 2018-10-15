package sample;

import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;

import java.util.ArrayList;

public class Map implements IRender {

    public ArrayList<ArrayList<Obstruction>> map;
    private final double width = 15;
    private final double height = 15;
    private double mapSquareWidthCount = 5;
    private double mapSquareHeightCount = 5;


    public Map() {
        this.map = createMap();
    }

    private ArrayList<ArrayList<Obstruction>> createMap() {

        ArrayList<ArrayList<Obstruction>> mapLayout = new ArrayList<>();
        for (int row = 0; row < mapSquareHeightCount; row++) {
            ArrayList<Obstruction> mapRow = new ArrayList<>();
            for (int column = 0; column < mapSquareWidthCount; column++) {
                mapRow.add(new MapTile(true, 100 + width * row, 100 + height * column, width, height, Color.GRAY));
            }
            mapLayout.add(mapRow);
        }

        return mapLayout;
    }


    public void render(Pane gameScreen) {
        for (ArrayList<Obstruction> row : map) {
            for (Obstruction item : row) {
                item.getNode().setTranslateX(item.getBounds().getX());
                item.getNode().setTranslateY(item.getBounds().getY());
                gameScreen.getChildren().add(item.getNode());
            }
        }
    }

    @Override
    public void update() {

    }

}
