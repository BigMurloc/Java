package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage stage) throws Exception{
        FXMLLoader menuController = new FXMLLoader(getClass().getResource("fxml/menuController.fxml"));  // pobranie fxml
        Pane pane = menuController.load();   //  załadowanie fxmla do pane
        Scene scene  = new Scene(pane); // ustawienie sceny z pane
        stage.setScene(scene); // ustawienie sceny w stage
        stage.setTitle("Snake"); // tytuł okienka
        stage.setResizable(false); // brak mozliwosci zmiany  rozmiaru w trakcie gry
        stage.show(); //pokazanie stage
    }



    public static void main(String[] args) {
        launch(args);
    }
}
