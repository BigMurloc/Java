package sample.Controller;

import javafx.animation.AnimationTimer;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import sample.Class.Difficulty;
import sample.Class.Movement;
import sample.Class.SnakeBody;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.ResourceBundle;

public class gameController implements Initializable {

    private static Canvas canvas;
    private static GraphicsContext gc;
    private static List<SnakeBody> snake = new ArrayList<>();
    private static int foodColor = 0;
    private static int width = 20;
    private static int height = 20;
    private static int size = 25;
    private static Movement direction = Movement.LEFT;
    private boolean gameOver = false;
    private static int speed = Difficulty.MEDIUM.getDiffculty();
    static Random rand = new Random();

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
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            gameOver = false;
            this.canvas = new Canvas(600, 400);
            this.gc = this.canvas.getGraphicsContext2D();
            paneGame.getChildren().add(canvas);

            new AnimationTimer() {
                long lastTick = 0;

                @Override
                public void handle(long now) {
                    if (lastTick == 0) {
                        lastTick = now;
                        tick(gc);
                        return;
                    }
                    if (now - lastTick > 1000000000 / speed) {
                        lastTick = now;
                        tick(gc);
                    }
                    if(gameOver){
                        gc.setFill(Color.RED);
                        gc.fillText("GAME OVER", 100, 250);
                        stop();
                    }

                }
            }.start();

            snake.add(new SnakeBody(width/2, height/2));
            snake.add(new SnakeBody(width/2, height/2));
            snake.add(new SnakeBody(width/2, height/2));


        }
        catch(Exception e){
            e.printStackTrace();
        }
    }

    //tick
    private void tick(GraphicsContext gc){
        Scene scene = paneGame.getScene();
        scene.addEventFilter(KeyEvent.KEY_PRESSED, key -> {
            if( key.getCode() == Movement.UP.getKey()){
                direction = Movement.UP;
            }
            if( key.getCode() == Movement.DOWN.getKey()){
                direction = Movement.DOWN;
            }
            if( key.getCode() == Movement.LEFT.getKey()){
                direction = Movement.LEFT;
            }
            if( key.getCode() == Movement.RIGHT.getKey()){
                direction = Movement.RIGHT;
            }

        });
        if(gameOver){
        gc.setFill(Color.RED);
        gc.fillText("GAME OVER", 100, 250);
        return;
    }

        for (int i = snake.size() -1; i >=1; i--){
        snake.get(i).setX(snake.get(i-1).getX());
        snake.get(i).setY(snake.get(i-1).getY());
    }

        switch(direction){
        case UP:
            snake.get(0).setY(snake.get(0).getY()-1);
            if (snake.get(0).getY() < 0){
                gameOver = true;
                snake.clear();
            }
            break;
        case DOWN:
            snake.get(0).setY(snake.get(0).getY()+1);
            if (snake.get(0).getY() > height){
                gameOver = true;
                snake.clear();
            }
            break;
        case LEFT:
            snake.get(0).setX(snake.get(0).getX()-1);
            if (snake.get(0).getX() < 0){
                gameOver = true;
                snake.clear();
            }
            break;
        case RIGHT:
            snake.get(0).setX(snake.get(0).getX()+1);
            if (snake.get(0).getX() > width){

            }
            break;
    }

    //eat food

    //self destroy
        for (int i =1; i < snake.size(); i++){
        if (snake.get(0).getX() == snake.get(i).getX() && snake.get(0).getY() == snake.get(i).getY()){
            gameOver = true;
            snake.clear();
        }
    }

    //fill background
        gc.setFill(Color.BLACK);
        gc.fillRect(0, 0, 600, 400);

    //score
        gc.setFill(Color.WHITE);
        gc.fillText("Score: " + (speed-6), 10, 30);

    Color cc = Color.WHITE;

        switch (foodColor) {
        case 0:
            cc = Color.PURPLE;
            break;
        case 1:
            cc = Color.LIGHTBLUE;
            break;
        case 2:
            cc = Color.YELLOW;
            break;
        case 3:
            cc = Color.PINK;
            break;
        case 4:
            cc = Color.ORANGE;
            break;
    }

        gc.setFill(cc);
//        gc.fillOval( * cornersize, foodY * cornersize, cornersize, cornersize);

    // snake
        for (SnakeBody c : snake) {
        gc.setFill(Color.LIGHTGREEN);
        gc.fillRect(c.getX() * size, c.getY() * size, size - 1, size - 1);
        gc.setFill(Color.GREEN);
        gc.fillRect(c.getX() * size, c.getY() * size, size - 2, size - 2);

    }


}

}

//TODO separate blocks
//TODO add control over snake
//TODO terminate Animation  Timer
