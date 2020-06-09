package sample.controller;

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
import sample.classes.Difficulty;
import sample.classes.Movement;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class OptionsController implements Initializable {

    private static int iterator = 1; //iterator po ktorym wybieram trudnosc
    private boolean isOccupied = false; // zabezpieczenie przed problemem wyboru dwoch klawiszy na raz
    private static String text = "NORMAL"; //domyslny poziom trudnosc(jego wartosc srring aby button mial okreslona wartosc textFielda
    private static String upKeyString = "W"; //domyslne przyciski
    private static String downKeyString = "S";
    private static String rightKeyString = "D";
    private static String leftKeyString = "A";
    private static List<String> keys = new ArrayList<String>();   //lista do filtrowania po przyciskach, lepiej gdyby byl to typ, ktorego nie pamietam do ew. przepisania

    @FXML
    private Button difficultyButton;

    @FXML
    private Button upKey;

    @FXML
    private Button downKey;


    @FXML
    private Button difficulty;

    @FXML
    private Button leftKey;

    @FXML
    private Button rightKey;


//KEY CLICKED
    @FXML
    void upKeyClicked(ActionEvent event) {
        if(!isOccupied) {
            upKey.setText("Press any button");
            isOccupied = true;
        }
    }

    @FXML
    void downKeyClicked(ActionEvent event) {
        if(!isOccupied) {
            downKey.setText("Press any button");
            isOccupied = true;
        }
    }

    @FXML
    void rightKeyClicked(ActionEvent event) {
        if(!isOccupied) {
            rightKey.setText("Press any button");
            isOccupied = true;
        }
    }

    @FXML
    void leftKeyClicked(ActionEvent event) {
        if(!isOccupied) {
            leftKey.setText("Press any button");
            isOccupied = true;
        }
    }
    //KEY PRESSED
    @FXML
    void upKeyPressed(KeyEvent event) {
        if(isOccupied && !(keys.contains(event.getCode().toString())) ) {
            if (upKey.getText().equalsIgnoreCase("Press any button")) {
                Movement.UP.setKey(event.getCode());
                keys.remove(upKeyString);
                upKeyString = event.getCode().toString();
                keys.add(upKeyString);
                setMovementKeyString();
                isOccupied = false;
            }
        }
    }

    @FXML
    void downKeyPressed(KeyEvent event) {
        if(isOccupied && !(keys.contains(event.getCode().toString())) ) {
            if (downKey.getText().equalsIgnoreCase("Press any button")) {
                Movement.DOWN.setKey(event.getCode());
                keys.remove(downKeyString);
                downKeyString = event.getCode().toString();
                keys.add(downKeyString);
                setMovementKeyString();
                isOccupied = false;
            }
        }
    }

    @FXML
    void leftKeyPressed(KeyEvent event) {
        if(isOccupied && !(keys.contains(event.getCode().toString())) ) {
            if (leftKey.getText().equalsIgnoreCase("Press any button")) {
                Movement.LEFT.setKey(event.getCode());
                keys.remove(leftKeyString);
                leftKeyString = event.getCode().toString();
                keys.add(leftKeyString);
                setMovementKeyString();
                isOccupied = false;
            }
        }
    }

    @FXML
    void rightKeyPressed(KeyEvent event) {
        if(isOccupied && !(keys.contains(event.getCode().toString())) ) {
            if (rightKey.getText().equalsIgnoreCase("Press any button")) {
                Movement.RIGHT.setKey(event.getCode());
                keys.remove(rightKeyString);
                rightKeyString = event.getCode().toString();
                keys.add(rightKeyString);
                setMovementKeyString();
                isOccupied = false;
            }
        }
    }
//METHODS
    private void setDifficultyButtonString(){
        difficultyButton.setText(text);
    }

    private void setMovementKeyString(){
        upKey.setText(upKeyString);
        downKey.setText(downKeyString);
        leftKey.setText(leftKeyString);
        rightKey.setText(rightKeyString);
    }

    @FXML
    void difficultyButtonClicked(ActionEvent event) {
            iterator++;
            switch(iterator % 3){
            case 0:
                text = "EASY";
                MenuController.setColor(Color.GREEN);
                GameController.setDifficulty(Difficulty.EASY);
                break;
            case 1:
                text = "NORMAL";
                MenuController.setColor(Color.BLUE);
                GameController.setDifficulty(Difficulty.MEDIUM);
                break;
            case 2:
                text = "HARD";
                MenuController.setColor(Color.RED);
                GameController.setDifficulty(Difficulty.HARD);
                break;
        }
        setDifficultyButtonString();
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
        setDifficultyButtonString();
        setMovementKeyString();
        keys.clear();
        keys.add(upKeyString);
        keys.add(downKeyString);
        keys.add(leftKeyString);
        keys.add(rightKeyString);
    }

}