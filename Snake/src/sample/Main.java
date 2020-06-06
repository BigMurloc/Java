package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage stage) throws Exception{
        FXMLLoader menuController = new FXMLLoader(getClass().getResource("fxml/menuController.fxml"));
        Pane pane = menuController.load();
        Scene scene  = new Scene(pane);
        stage.setScene(scene);
        stage.setTitle("Snake");
        stage.setResizable(false);
        stage.show();
    }



    public static void main(String[] args) {
        launch(args);
    }
}
