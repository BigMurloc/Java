package sample.Controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import sample.Class.Difficulty;

import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class optionsController implements Initializable {

    private static int iterator = 1;
    private static String text = "NORMAL";

    @FXML
    private Button difficultyButton;

    @FXML
    private Button upKey;

    @FXML
    private Button downKey;

    @FXML
    private Button leftKey;

    @FXML
    private Button rightKey;

    @FXML
    void upKeyClicked(ActionEvent event) {
        upKey.setText("Press any button to change up key.");
    }

    @FXML
    void downKeyClicked(ActionEvent event) {
        downKey.setText("Press any button to change down key.");
    }

    @FXML
    void leftKeyClicked(ActionEvent event) {
        leftKey.setText("Press any button to change left key.");
    }


    @FXML
    void rightKeyClicked(ActionEvent event) {
        rightKey.setText("Press any button to change right key.");
    }

    @FXML
    void keyPressedEvent(KeyEvent event)  {
//        if(upKey.getText().equalsIgnoreCase("Press any button to change up key.")){
////            gameController.setUpKey(((KeyCode)event.getCode()));
////            gameController.setTest(event.getCode().toString());
//            System.out.println("w");
//        }
        System.out.println("W");

//        if(downKey.getText().equalsIgnoreCase("Press any button to change down key.")){
//            gameController.setDownKey(event.getCode());
//        }
//        if(leftKey.getText().equalsIgnoreCase("Press any button to change left key.")){
//            gameController.setLeftKey(event.getCode());
//        }
//        if(rightKey.getText().equalsIgnoreCase("Press any button to change down key.")){
//            gameController.setRightKey(event.getCode());
//        }




    }

    @FXML
    void difficultyButtonClicked(ActionEvent event) {
            iterator++;
            switch(iterator % 3){
            case 0:
                text = "EASY";
                menuController.setColor(Color.GREEN);
                gameController.setSpeed(Difficulty.EASY.getDiffculty());
                break;
            case 1:
                text = "NORMAL";
                menuController.setColor(Color.BLUE);
                gameController.setSpeed(Difficulty.MEDIUM.getDiffculty());
                break;
            case 2:
                text = "HARD";
                menuController.setColor(Color.RED);
                gameController.setSpeed(Difficulty.HARD.getDiffculty());
                break;
        }
        difficultyButton.setText(text);
    }

    @FXML
    private Pane paneOptions;

    @FXML
    void previousScene(KeyEvent event) throws IOException {
        if (event.getCode() == KeyCode.ESCAPE) {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/sample/fxml/menuController.fxml"));
            loader.load();
            Stage stage = (Stage) paneOptions.getScene().getWindow();
            Scene scene = new Scene(loader.getRoot());
            stage.setScene(scene);
        }

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        difficultyButton.setText(text);
    }

}


//TODO add option save difficulty assignment