package game.Camera;

import game.Entity.Player;
import game.Map.Map;
import game.Map.Obstruction;
import javafx.geometry.Rectangle2D;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.StrokeType;

import java.util.ArrayList;
import java.util.List;

public class Camera {

    private static double offsetX;
    private static double offsetY;
    private static double deltaX;
    private static double deltaY;
    private static double gameScreenWidth;
    private static double gameScreenHeight;
    private double oldPlayerPosY;
    private double oldPlayerPosX;
    private Rectangle border;
    private Canvas gameScreen;

    private ObstructionDrawer obstructionDrawer;

    public Camera(Canvas gameScreen, Player player, ObstructionDrawer obstructionDrawer) {
        this.gameScreen = gameScreen;
        setGameScreenSizes(gameScreen);
        setOffsets(player);

        oldPlayerPosX = 0.0;
        oldPlayerPosY = 0.0;

        border = createBorder();
//        gameScreen.getChildren().add(border);
        this.obstructionDrawer = obstructionDrawer;
    }

    private void setGameScreenSizes(Canvas gameScreen) {
        gameScreenWidth = gameScreen.getWidth();
        gameScreenHeight = gameScreen.getHeight();
    }

    private void setOffsets(Player player) {
        offsetX = getOffset(gameScreenWidth, player.getX());
        offsetY = getOffset(gameScreenHeight, player.getY());
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

    public void updateCamera(Player player, Map map) {
//        getPlayerDeltas(player);


//        List<Rectangle2D> mapTiles = getMapTiles(map);
//        mapTiles.addAll(getMapItems(map));
        Rectangle2D playerImage = getPlayer(player);

        deltaY = oldPlayerPosY - playerImage.getMinY();
        deltaX = oldPlayerPosX - playerImage.getMinX();

        List<Rectangle2D> mapTilesToDraw = obstructionDrawer.draw(getMapTiles(map), gameScreenWidth,
                gameScreenHeight, offsetY + deltaY,
                offsetX + deltaX);
        List<Rectangle2D> mapItemsToDraw = obstructionDrawer.draw(getMapItems(map), gameScreenWidth,
                gameScreenHeight, offsetY + deltaY,
                offsetX + deltaX);

        GraphicsContext graphicsContext = gameScreen.getGraphicsContext2D();

        graphicsContext.setFill(Color.WHITE);
        graphicsContext.fillRect(0, 0, 500, 500);
        graphicsContext.setFill(Color.GREEN);

        for (Rectangle2D tile : mapTilesToDraw) {
            graphicsContext.fillRect(tile.getMinX(), tile.getMinY(), tile.getWidth(), tile.getHeight());
        }

        graphicsContext.setFill(Color.GRAY);

        for (Rectangle2D tile : mapItemsToDraw) {
            graphicsContext.fillRect(tile.getMinX(), tile.getMinY(), tile.getWidth(), tile.getHeight());
        }

        graphicsContext.setFill(Color.BLACK);

        graphicsContext.fillRect(gameScreen.getWidth() / 2, gameScreen.getHeight() / 2, 10, 10);

//        fixPlayerToCenter(playerImage);

//        border.toFront();

        offsetX += deltaX;
        offsetY += deltaY;
        oldPlayerPosY = playerImage.getMinY();
        oldPlayerPosX = playerImage.getMinX();



    }

    private Rectangle2D getPlayer(Player player) {

        double x = player.getX();
        double y = player.getY();
        double width = 10;
        double height = 10;
        Rectangle2D playerbounds = new Rectangle2D(x, y, width, height);

        return playerbounds;
    }

    private List<Rectangle2D> getMapItems(Map map) {
        List<Rectangle2D> items = new ArrayList<>();

        for (Obstruction mapItems : map.getMapItems()) {
            double x = mapItems.getBounds().x;
            double y = mapItems.getBounds().y;
            double width = mapItems.getBounds().width;
            double height = mapItems.getBounds().height;
            Rectangle2D rectangle = new Rectangle2D(x, y, width, height);
            items.add(rectangle);
        }

        return items;
    }

    private List<Rectangle2D> getMapTiles(Map map) {
        List<Rectangle2D> mapTiles = new ArrayList<>();

        for (Obstruction mapTile : map.getMapTiles()) {
            double x = mapTile.getBounds().x;
            double y = mapTile.getBounds().y;
            double width = mapTile.getBounds().width;
            double height = mapTile.getBounds().height;
            Rectangle2D rectangle = new Rectangle2D(x, y, width, height);
            mapTiles.add(rectangle);
        }

        return mapTiles;
    }


}
