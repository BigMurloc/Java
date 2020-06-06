package sample.Controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import sample.Class.SnakeBody;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class gameController {

    private static List<SnakeBody> snake = new ArrayList<>();
    private static int foodX;
    private static int foodY;


    @FXML
    private Pane paneGame;

    @FXML
    void previousScene(KeyEvent event) throws IOException {
        if(event.getCode() == KeyCode.ESCAPE){
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/sample/fxml/menuController.fxml"));
            Pane pane = loader.load();
            Stage stage = (Stage) paneGame.getScene().getWindow();
            Scene scene = new Scene(loader.getRoot());
            stage.setScene(scene);
            Canvas canvas = new Canvas(50, 50);
            GraphicsContext graphicsContext = canvas.getGraphicsContext2D();
            pane.getChildren().add(canvas);
        }

    }

}


//TODO make snake appear on the screen
//TODO make food appear on the screen
//TODO make method for eating food and iterating snake's body