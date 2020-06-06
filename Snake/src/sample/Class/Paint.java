package sample.Class;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;


public class Paint {
    public static void paint(GraphicsContext gc) {
        gc.setFill(Color.GREEN);
        gc.fillRect(50,50,50,50);
        gc.setFill(Color.GREEN);
        gc.fillRect(20,20,20,20);
    }

}
