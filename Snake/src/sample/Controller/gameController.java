package sample.Controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.IOException;

public class gameController {

    @FXML
    private Pane paneGame;

    @FXML
    void previousScene(KeyEvent event) throws IOException {
        if(event.getCode() == KeyCode.ESCAPE){
            FXMLLoader loader = new FXMLLoader(getClass().getResource("fxml/menuController.fxml"));
            Pane pane = loader.load();
            Stage stage = (Stage) paneGame.getScene().getWindow();
            Scene scene = new Scene(loader.getRoot());
            stage.setScene(scene);
        }

    }

}
