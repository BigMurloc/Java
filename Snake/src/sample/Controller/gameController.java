package sample.Controller;

import javafx.animation.AnimationTimer;
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
    private static int x;
    private static int y;
    private static int size = 25;
    private static int speed = 5;

    public static void setSpeed(int speed) {
        gameController.speed = speed;
    }

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
        if (event.getCode() == KeyCode.K) {
            rysuj(gc);
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        this.canvas = new Canvas(600,400);
        paneGame.getChildren().add(canvas);
        this.gc = this.canvas.getGraphicsContext2D();
        this.x=14;
        this.y=14;
        snake.add(new SnakeBody(size*3,size));
        snake.add(new SnakeBody(size*2, size));
        snake.add(new SnakeBody(size*1,size));


        new AnimationTimer(){
            long lastTick = 0;

            public void handle(long now){
                if (lastTick == 0){
                    lastTick = now;
                    rysuj(gc);
                }
                if(now - lastTick > 1000000000/speed){
                    lastTick = now;
                    rysuj(gc);
                }
            }


        }.start();

    }

    public void rysuj(GraphicsContext gc){
        gc.setFill(Color.BLACK);
        gc.fillRect(0,0,600,400); //wszystko na czarno
        gc.setFill(Color.GREEN);

        for(SnakeBody snakeBodyFragment : snake){
            snakeBodyFragment.setX(snakeBodyFragment.getX()+size);
            gc.fillRect(snakeBodyFragment.getX(), snakeBodyFragment.getY(), size, size);
            snakeBodyFragment.setY(snakeBodyFragment.getY()+size);
            gc.fillRect(snakeBodyFragment.getX(), snakeBodyFragment.getY(), size, size);
        }

        System.out.println(x);
        System.out.println(y);
    }


}

//TODO separate blocks
//TODO add control over snake
//TODO terminate Animation  Timer
