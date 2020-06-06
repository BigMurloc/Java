package sample.Controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.IOException;

public class optionsController {

    @FXML
    private Pane paneOptions;

    @FXML
    private Button baton;

//    @FXML
//    void batonClicked(ActionEvent event) {
//        baton.setText("DZIALA!");
//    }

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
