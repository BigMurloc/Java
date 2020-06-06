package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage stage) throws Exception{
        FXMLLoader menuController = new FXMLLoader(getClass().getResource("fxml/menuController.fxml"));
        Pane pane = menuController.load();

        Canvas canvas = new Canvas(300,300);
        GraphicsContext gc = canvas.getGraphicsContext2D();
        pane.getChildren().add(canvas);

        Scene scene  = new Scene(pane);
        stage.setScene(scene);
        stage.setTitle("Snake");
        stage.show();


    }



    public static void main(String[] args) {
        launch(args);
    }
}
//TODO make snake appear on the screen
//TODO make food appear on the screen
//TODO make method for eating food and iterating snake's body