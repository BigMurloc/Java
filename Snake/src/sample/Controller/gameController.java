package sample.Controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import sample.Class.SnakeBody;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class gameController implements Initializable {

    private static Canvas canvas;
    private static GraphicsContext gc;
    private static List<SnakeBody> snake = new ArrayList<>();
    private static int foodX;
    private static int foodY;


    @FXML
    private Pane paneGame;

    @FXML
    void previousScene(KeyEvent event) throws IOException {
        if(event.getCode() == KeyCode.ESCAPE){
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/sample/fxml/menuController.fxml"));
            loader.load();
            Stage stage = (Stage) paneGame.getScene().getWindow();
            Scene scene = new Scene(loader.getRoot());
            stage.setScene(scene);
        }
        if(event.getCode() == KeyCode.K){

        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        this.canvas = new Canvas(300,300);
        paneGame.getChildren().add(canvas);
        tick();
    }

    public void tick(){
        gc = canvas.getGraphicsContext2D();
        gc.setFill(Color.GREEN);
        gc.fillRect(20,20,20,20);
    }

}