package game.Camera;

import game.Entity.Player;
import game.Map.Map;
import game.Map.Obstruction;
import javafx.scene.Node;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.StrokeType;

import java.util.ArrayList;
import java.util.List;

public class Camera {

    private static double offsetX;
    private static double offsetY;
    private double oldPlayerPosY;
    private double oldPlayerPosX;
    private static double deltaX;
    private static double deltaY;
    private static double gameScreenWidth;
    private static double gameScreenHeight;
    private Rectangle border;
    private Pane gameScreen;

    private ObstructionDrawer obstructionDrawer;

    public Camera(Pane gameScreen, Player player, ObstructionDrawer obstructionDrawer) {
        this.gameScreen = gameScreen;
        setGameScreenSizes(gameScreen);
        setOffsets(player.getView());

        oldPlayerPosX = player.getView().getTranslateX();
        oldPlayerPosY = player.getView().getTranslateY();

        border = createBorder();
//        gameScreen.getChildren().add(border);
        this.obstructionDrawer = obstructionDrawer;
    }

    private void setGameScreenSizes(Pane gameScreen) {
        gameScreenWidth = gameScreen.getWidth();
        gameScreenHeight = gameScreen.getHeight();
    }

    private void setOffsets(Node player) {
        offsetX = getOffset(gameScreenWidth, player.getTranslateX());
        offsetY = getOffset(gameScreenHeight, player.getTranslateY());
    }

    private Rectangle createBorder() {
        Rectangle border = new Rectangle(0, 0, gameScreenWidth, gameScreenHeight);
        border.setFill(Color.TRANSPARENT);
        border.setStrokeType(StrokeType.INSIDE);
        border.setStroke(Color.WHITE);
        border.setStrokeWidth(20);
        border.setMouseTransparent(true);

        return border;
    }


    private double getOffset(double gameScreenSize, double playerPosition) {
        return (gameScreenSize / 2) - playerPosition;
    }

    public void updateCamera(Player player, Map map, boolean mapChange) {
        getPlayerDeltas(player);


        List<Rectangle> mapTiles = getMapTiles(map);
        mapTiles.addAll(getMapItems(map));
        Rectangle playerImage = getPlayer(player);

        deltaY = oldPlayerPosY - playerImage.getY();
        deltaX = oldPlayerPosX - playerImage.getX();

        obstructionDrawer.draw(mapTiles, gameScreenWidth,
                gameScreenHeight, offsetY + deltaY,
                offsetX + deltaX);

        gameScreen.getChildren().clear();
        gameScreen.getChildren().addAll(mapTiles);
        gameScreen.getChildren().add(playerImage);
        fixPlayerToCenter(playerImage);

//        gameScreen.getChildren().add(border);


//        border.toFront();

        offsetX += deltaX;
        offsetY += deltaY;
        oldPlayerPosY = playerImage.getY();
        oldPlayerPosX = playerImage.getX();
    }

    private Rectangle getPlayer(Player player) {

        double x = player.getX();
        double y = player.getY();
        double width = 10;
        double height = 10;
        Rectangle playerbounds = new Rectangle(x, y, width, height);
        playerbounds.setFill(Color.BLACK);

        return playerbounds;
    }

    private List<Rectangle> getMapItems(Map map) {
        List<Rectangle> items = new ArrayList<>();

        for (Obstruction mapItems : map.getMapItems()) {
            double x = mapItems.getBounds().x;
            double y = mapItems.getBounds().y;
            double width = mapItems.getBounds().width;
            double height = mapItems.getBounds().height;
            Rectangle rectangle = new Rectangle(x, y, width, height);
            rectangle.setFill(Color.GRAY);
            items.add(rectangle);
        }

        return items;
    }

    private List<Rectangle> getMapTiles(Map map) {
        List<Rectangle> mapTiles = new ArrayList<>();

        for (Obstruction mapTile : map.getMapTiles()) {
            double x = mapTile.getBounds().x;
            double y = mapTile.getBounds().y;
            double width = mapTile.getBounds().width;
            double height = mapTile.getBounds().height;
            Rectangle rectangle = new Rectangle(x, y, width, height);
            rectangle.setFill(Color.GREEN);
            mapTiles.add(rectangle);
        }

        return mapTiles;
    }


    private void getPlayerDeltas(Player player) {
        deltaX = -player.getX();
        deltaY = -player.getY();
    }

    private void fixPlayerToCenter(Rectangle player) {
        player.setTranslateX(gameScreenWidth / 2);
        player.setTranslateY(gameScreenHeight / 2);
    }


}
