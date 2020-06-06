package sample.Controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import sample.Class.Difficulty;

import java.io.IOException;

public class optionsController {

    private int iterator = 1;

    @FXML
    private Button difficultyButton;

    @FXML
    void difficultyButtonClicked(ActionEvent event) {
            iterator++;
            switch(iterator % 3){
            case 0:
                difficultyButton.setText("EASY");
                menuController.setColor(Color.GREEN);
                gameController.setSpeed(Difficulty.EASY.getDiffculty());
                break;
            case 1:
                difficultyButton.setText("NORMAL");
                menuController.setColor(Color.BLUE);
                gameController.setSpeed(Difficulty.MEDIUM.getDiffculty());
                break;
            case 2:
                difficultyButton.setText("HARD");
                menuController.setColor(Color.RED);
                gameController.setSpeed(Difficulty.HARD.getDiffculty());
                break;
        }
    }

    @FXML
    private Pane paneOptions;

    @FXML
    void previousScene(KeyEvent event) throws IOException {
        System.out.println(event.getCode());
        if (event.getCode() == KeyCode.ESCAPE) {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/sample/fxml/menuController.fxml"));
            loader.load();
            Stage stage = (Stage) paneOptions.getScene().getWindow();
            Scene scene = new Scene(loader.getRoot());
            stage.setScene(scene);
        }

    }
}
