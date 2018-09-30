package sample;

import javafx.scene.canvas.GraphicsContext;

public interface Render {

    void render(GraphicsContext gameScreen, long deltaTime);
}
