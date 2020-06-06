package sample.Controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import sample.Class.Difficulty;

import java.io.IOException;

public class optionsController {

    private static int iterator = 1;

    @FXML
    private Pane paneOptions;


    @FXML
    private Button difficulty;

    @FXML
    private Label label = new menuController().getSnakeLabel();

    @FXML
    void difficultyClicked(ActionEvent event) {
        menuController menu = new menuController();
        gameController game = new gameController();
        switch(iterator % 3){
            case 0:
                difficulty.setText("EASY");
                menu.setColor(Color.GREEN);
                game.setSpeed(Difficulty.EASY.getSpeed());
                iterator++;
                break;
            case 1:
                difficulty.setText("NORMAL");
                menu.setColor(Color.BLUE);
                game.setSpeed(Difficulty.MEDIUM.getSpeed());
                iterator++;
                break;
            case 2:
                difficulty.setText("HARD");
                menu.setColor(Color.RED);
                menu.setTest(2);
                game.setSpeed(Difficulty.HARD.getSpeed());
                iterator++;
                break;
        }
    }

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
