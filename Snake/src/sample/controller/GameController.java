package sample.controller;
//Game Logic inspired by https://github.com/Gaspared/snake/blob/master/Main.java
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
import sample.classes.Difficulty;
import sample.classes.Food;
import sample.classes.Movement;
import sample.classes.SnakeBody;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.ResourceBundle;
//TODO przetlumaczyc wszystko co po polsku na angielski (!)
//TODO maxspeed = 20;
public class GameController implements Initializable {
    private Canvas canvas;
    private GraphicsContext gc;
    private List<SnakeBody> snake = new ArrayList<>();
    //food, buff, debuff variables
    private Food food = new Food(0,0);
    private Food buff = new Food(0,0);
    private Food debuff = new Food(10,10);
    private int foodColor = 0;
    private int chanceForBuff = 4;    // 4 -> 1/4 -> 25% | 1/x
    //Movement
    private static Movement direction = Movement.LEFT; //TODO change from enum to simple class
    //Enums
    private static Difficulty difficulty = Difficulty.MEDIUM; //need access from diffrent class
    //int
    private final int width = 600;   //these values are values of a fixed screen
    private final int height = 400; //changing them wouldn't affect the screen itself, only the board.
    private int speed = difficulty.getDiffculty();
    private int pointCounter;
    private int size = 20;
    //booleans
    private boolean gameOver = false;
    private boolean isBuff = false;
    private boolean isDebuff = false;
    //random
    Random rand = new Random();


    public static void setDifficulty(Difficulty difficulty) { //changing game difficulty
        GameController.difficulty = difficulty;
    }

    @FXML
    private Pane paneGame;


    @FXML
    void previousScene(KeyEvent event) throws IOException, InterruptedException { //changing to the previous scene, not universal
        if(event.getCode() == KeyCode.ESCAPE){
            gameOver = true;
            Thread.sleep(400);
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
            newFood(); // generating new food coordinates along with chance to spawn a buff
            this.canvas = new Canvas(width, height);  // initialization of canvas
            this.gc = this.canvas.getGraphicsContext2D(); //initialization of drawing method
            paneGame.getChildren().add(canvas); //adding canvas to scene

            new AnimationTimer() {  //game timer
                long lastTick = 0;

                @Override
                public void handle(long now) {
                    if(gameOver){ //if the game is over - stop the game
                        gc.setFill(Color.RED);
                        gc.fillText("GAME OVER", width/2, height/2);
                        stop();
                    }
                    if (lastTick == 0) {
                        lastTick = now;
                        tick(gc);
                        return;
                    }
                    if (now - lastTick > 1000000000 / speed) { //if the difference between now and then is big enough - do tick
                        lastTick = now;
                        tick(gc);
                    }
                }
            }.start();

            snake.add(new SnakeBody(size/2, size/2)); //snake's initial body
            snake.add(new SnakeBody(size/2, size/2));
            snake.add(new SnakeBody(size/2, size/2));


        }
        catch(Exception e){
            e.printStackTrace();
        }
    }

    //tick
    private void tick(GraphicsContext gc){
        Scene scene = paneGame.getScene();
        scene.addEventFilter(KeyEvent.KEY_PRESSED, key -> {   //changing the direction of the snake depending on which key is pressed
            if( key.getCode() == Movement.UP.getKey() && direction != Movement.DOWN ){
                direction = Movement.UP;
            }
            if( key.getCode() == Movement.DOWN.getKey() && direction != Movement.UP ){
                direction = Movement.DOWN;
            }
            if( key.getCode() == Movement.LEFT.getKey() && direction != Movement.RIGHT ){
                direction = Movement.LEFT;
            }
            if( key.getCode() == Movement.RIGHT.getKey() && direction != Movement.LEFT ){
                direction = Movement.RIGHT;
            }

        });
        if(gameOver){
        gc.setFill(Color.RED);
        gc.fillText("GAME OVER", width/2, height/2);
        return;
    }

        for (int i = snake.size() -1 ; i>=1; i--){ // moving the body of the snake (without the head)
        snake.get(i).setX(snake.get(i-1).getX());
        snake.get(i).setY(snake.get(i-1).getY());

    }

        switch(direction){ //changing the XY of the snake's head depending on the current direction
        case UP:
            snake.get(0).setY(snake.get(0).getY()-1);
            if (snake.get(0).getY() < 0){
                gameOver = true;
            }
            break;
        case DOWN:
            snake.get(0).setY(snake.get(0).getY()+1);
            if (snake.get(0).getY() > (height/size)-1){
                gameOver = true;
            }
            break;
        case LEFT:
            snake.get(0).setX(snake.get(0).getX()-1);
            if (snake.get(0).getX() < 0){
                gameOver = true;
            }
            break;
        case RIGHT:
            snake.get(0).setX(snake.get(0).getX()+1);
            if (snake.get(0).getX() > (width/size)-1){
                gameOver = true;
            }
            break;
    }

    //eat food
        if(food.getX() == snake.get(0).getX() && food.getY() == snake.get(0).getY()){ // -1 -1 so the snake's body fragment is out of the screen
            snake.add(new SnakeBody(-1,-1));                                   //(in first tick the fragment wouldn't be attached to snake's body)
            newFood();
        }

    //eat buff
    if(buff.getX() == snake.get(0).getX() && buff.getY() == snake.get(0).getY()){ // -1 -1 so you can't access the buff while it shouldn't have spawned
        buff.setX(-1);
        buff.setY(-1);
        speed -= difficulty.getSpeedChangeBuff();
    }
    //eat debuff
    if(debuff.getX() == snake.get(0).getX() && debuff.getY() == snake.get(0).getY()){ // -1 -1 so you can't access the debuff when it shouldn't have spawned
        debuff.setX(-1);
        debuff.setY(-1);
        speed += difficulty.getSpeedChangeDebuff();
    }

    //self destroy
        for (int i =1; i < snake.size(); i++){
        if (snake.get(0).getX() == snake.get(i).getX() && snake.get(0).getY() == snake.get(i).getY()){
            gameOver = true;
            snake.clear(); //to erase snake from
        }
    }

    //fill background
        gc.setFill(Color.BLACK);
        gc.fillRect(0, 0, width, height);

    //score
        gc.setFill(Color.WHITE);
        gc.fillText("Score: " + (pointCounter), 10, 30);
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

    //buff
        if(isBuff) {
            gc.setFill(Color.GREEN);
            gc.fillOval(buff.getX() * size, buff.getY() * size, size, size);
        }

    //debuf
        if(isDebuff) {
            gc.setFill(Color.RED);
            gc.fillOval(debuff.getX() * size, debuff.getY() * size, size, size);
        }


    //fruit
        gc.setFill(cc);
        gc.fillOval(food.getX() * size, food.getY() * size, size, size);

    // snake
        for (SnakeBody snakeFragment : snake) {
        gc.setFill(Color.MEDIUMPURPLE);
        gc.fillRect(snakeFragment.getX() * size, snakeFragment.getY() * size, size - 1, size - 1); //pokolorowanie borderowgc.rotate(45);
        gc.setFill(Color.PURPLE);
        gc.fillRect(snakeFragment.getX() * size, snakeFragment.getY() * size, size - 2, size - 2); // pokolorowanie snake'a (nalozenie na duzy kwadrat, mniejszego)
    }


}
//new food
    public void newFood(){
        start: while(true){
            food.setX(rand.nextInt((width/size)-1)); //-1 so fruit is in window bounds
            food.setY(rand.nextInt((height/size)-1)); //-1 so fruit is in winndow bounds
            pointCounter++;
            if(rand.nextInt(chanceForBuff) == 0){
                isBuff = true;
                newBuff();
            }
            if(rand.nextInt(chanceForBuff) == 1){
                isDebuff = true;
                newDebuff();
            }

            for (SnakeBody snakeFragment : snake){ //if the snake meets the fruit or the fruit spawns within snake body
                if(snakeFragment.getX() == food.getX() && snakeFragment.getY() == food.getY()){
                    continue start; //go back to start and do again
                }
            }

            foodColor = rand.nextInt(5);
            speed++;
            break;
        }
    }

    private void newBuff(){
        start: while(true){
            buff.setX(rand.nextInt((width/size)-1)); //-1 so fruit is in window bounds
            buff.setY(rand.nextInt((height/size)-1)); //-1 so fruit is in winndow bounds

            for (SnakeBody snakeFragment : snake){
                if(snakeFragment.getX() == buff.getX() && snakeFragment.getY() == buff.getY()){
                    continue start;
                }
            }
            break;
        }
    }

// debuff
    private void newDebuff(){
        start: while(true){
            debuff.setX(rand.nextInt((width/size)-1)); //-1 so fruit is in window bounds
            debuff.setY(rand.nextInt((height/size)-1)); //-1 so fruit is in winndow bounds

            for (SnakeBody snakeFragment : snake){
                if(snakeFragment.getX() == debuff.getX() && snakeFragment.getY() == debuff.getY()){
                    continue start;
                }
            }
            break;
        }
    }

}