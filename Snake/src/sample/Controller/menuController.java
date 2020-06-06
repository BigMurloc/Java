package sample.Controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import sample.Class.Sound;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class menuController implements Initializable {

    @FXML
    private Label snakeLabel;

    @FXML
    private Button optionsButton;

    @FXML
    private Button exitButton;

    @FXML
    private Button startButton;

    @FXML
    void exitButtonClicked(ActionEvent event) {
        System.exit(0);
    }

    @FXML
    void optionsButtonClicked(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/sample/fxml/optionsController.fxml"));
        loader.load();
        Stage stage = (Stage) startButton.getScene().getWindow();
        Scene scene = new Scene(loader.getRoot());
        stage.setScene(scene);
    }

    @FXML
    void startButtonClicked(ActionEvent event) throws IOException {                                     //https://stackoverflow.com/questions/34863425/javafx-scene-builder-how-switch-scene
        Parent gameParent = FXMLLoader.load(getClass().getResource("/sample/fxml/gameController.fxml"));
        Scene gameScene = new Scene(gameParent);
        Stage window = (Stage) (((Node) event.getSource()).getScene().getWindow());
        window.setScene(gameScene);
        window.show();
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        if(!Sound.isPlaying()){
            Sound.play();
        }
    }
}