package sample.Controller;
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
import sample.Class.Difficulty;
import sample.Class.Food;
import sample.Class.Movement;
import sample.Class.SnakeBody;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.ResourceBundle;
//TODO przetlumaczyc wszystko co po polsku na angielski (!)
public class gameController implements Initializable {

    private  Canvas canvas;
    private  GraphicsContext gc;
    private  List<SnakeBody> snake = new ArrayList<>();
    //food, buff, debuff variables
    private  Food food = new Food(0,0);
    private  Food buff = new Food(0,0);
    private  Food debuff = new Food(10,10);
    private  int foodColor = 0;
    private  int chanceForBuff = 4;    // 4 -> 1/4 -> 25% | 1/x
    //Enums
    private static Movement direction = Movement.LEFT;
    private static Difficulty difficulty = Difficulty.MEDIUM;
    //int
    private static int speed = difficulty.getDiffculty();
    private static int currentSpeed;
    private static int pointCounter;
    private static int size = 20;
    //booleans
    private static boolean gameOver = false;
    private  boolean isBuff = false;
    private  boolean isDebuff = false;
    //random
    static Random rand = new Random();



    public static void setDifficulty(Difficulty difficulty) { //zmiana poziomu trudnosci
        gameController.difficulty = difficulty;
    }

    public static void setDefault(){ //metoda do ustawiania domyslnych wartosci w trakcie inicjalizacji (brak generowal bledy w postaci stackowania sie speeda);
        currentSpeed = speed;
        pointCounter = 0;
        gameOver = false;
    }

    @FXML
    private Pane paneGame;


    @FXML
    void previousScene(KeyEvent event) throws IOException, InterruptedException { //przejscie do poprzedniej sceny (metoda nie jest uniwersalna)
        if(event.getCode() == KeyCode.ESCAPE){
            gameOver = true;
            snake.clear();
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
            newFood(); //wygenerowanie zmiennych wartosci X, Y na food (oraz szansy na pojawienie sie buffa)
            setDefault(); // ustawienie domyslnych wartosci
            this.canvas = new Canvas(600, 400);  //inicjalizacja plotna
            this.gc = this.canvas.getGraphicsContext2D(); //inicjalizacja "rysika"
            paneGame.getChildren().add(canvas); //dodanie plotna do sceny

            new AnimationTimer() {  //timer w ktorym wykonuje sie gra
                long lastTick = 0;  //ostatni tick

                @Override
                public void handle(long now) {
                    if (lastTick == 0) {
                        lastTick = now; //ustawienie ostatniego  ticka na ten moment jesli tick jest == 0( na sammy poczatku)
                        tick(gc); //wywolanie ticka
                        return;
                    }
                    if (now - lastTick > 1000000000 / currentSpeed) { //jesli roznica pomiedzy czasem teraz a ostatniego ticka jest wystarczajaco duza to wykonuje sie if
                        lastTick = now;                               // im wiekszy mianownik tym mniejsza liczba do porownania w zwiazku z czym tick nastepuje szybciej
                        tick(gc);
                    }
                    if(gameOver){ //jesli gra skonczona to funkcja ma sie zatrzymac (komenda stop())
                        gc.setFill(Color.RED);
                        gc.fillText("GAME OVER", 100, 250);
                        stop();
                    }


                }
            }.start(); //rozpoczenie timera

            snake.add(new SnakeBody(size/2, size/2)); //dodanie pooczątkowych bloczków snake'a
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
        scene.addEventFilter(KeyEvent.KEY_PRESSED, key -> {   //w zaleznosci od kliknietego przycisku ustalonego z enuma Movement - zmiana kierunku poruszania sie snake'a
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

        for (int i = snake.size() -1 ; i>=1; i--){ // poruszanie sie snake'a oprocz glowy (podazanie za glowa) - najlepsze wyjasnienie rysunkiem imo
            System.out.println(i);
        snake.get(i).setX(snake.get(i-1).getX());
        snake.get(i).setY(snake.get(i-1).getY());

    }

        switch(direction){ //zmiana kierunku poruszania sie snake w zaleznosci od wcisnietego klawisza -> iterowanie po X/Y //najlatwiej wyjasnnic rysunkiem
        case UP:
            snake.get(0).setY(snake.get(0).getY()-1);
            if (snake.get(0).getY() < 0){
                gameOver = true;
                snake.clear();
            }
            break;
        case DOWN:
            snake.get(0).setY(snake.get(0).getY()+1);
            if (snake.get(0).getY() > (400/size)-1){
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
            if (snake.get(0).getX() > (600/size)-1){
                gameOver = true;
                snake.clear();
            }
            break;
    }

    //eat food
        if(food.getX() == snake.get(0).getX() && food.getY() == snake.get(0).getY()){ // -1 -1 zeby namalowac bloczek snake'a poza gra
            snake.add(new SnakeBody(-1,-1));                                   // na krotki moment pojawai sie blok "poza" wezem i potem go dodaje do niege (w sensie graficznym)
            newFood();
        }

    //eat buff
    if(buff.getX() == snake.get(0).getX() && buff.getY() == snake.get(0).getY()){ // ustalenie -1 -1 zeby namalowac poza gra
        buff.setX(-1);
        buff.setY(-1);
        currentSpeed -= difficulty.getSpeedChangeBuff();
    }
    //eat debuff
    if(debuff.getX() == snake.get(0).getX() && debuff.getY() == snake.get(0).getY()){ // ustalenie -1 -1 zeby namalowac poza gra
        debuff.setX(-1);
        debuff.setY(-1);
        currentSpeed += difficulty.getSpeedChangeDebuff();
    }

    //self destroy
        for (int i =1; i < snake.size(); i++){
        if (snake.get(0).getX() == snake.get(i).getX() && snake.get(0).getY() == snake.get(i).getY()){
            gameOver = true;
            snake.clear(); //zeby wymazac snake i jego obiekty z listy
        }
    }

    //fill background
        gc.setFill(Color.BLACK);
        gc.fillRect(0, 0, 600, 400);

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
        gc.fillRect(snakeFragment.getX() * size, snakeFragment.getY() * size, size - 1, size - 1); //pokolorowanie borderow
        gc.setFill(Color.PURPLE);
        gc.fillRect(snakeFragment.getX() * size, snakeFragment.getY() * size, size - 2, size - 2); // pokolorowanie snake'a (nalozenie na duzy kwadrat, mniejszego)

    }


}
//new food
    public void newFood(){
        start: while(true){
            food.setX(rand.nextInt((600/size)-1)); //-1 so fruit is in window bounds
            food.setY(rand.nextInt((400/size)-1)); //-1 so fruit is in winndow bounds
            pointCounter++;
            if(rand.nextInt(chanceForBuff) == 0){
                isBuff = true;
                newBuff();
            }
            if(rand.nextInt(chanceForBuff) == 1){
                isDebuff = true;
                newDebuff();
            }

            for (SnakeBody snakeFragment : snake){ //jesli snake pojawi sie na owocku lub owocek na snake'u
                if(snakeFragment.getX() == food.getX() && snakeFragment.getY() == food.getY()){
                    continue start; //wroc do start i zrob na nowo
                }
            }

            foodColor = rand.nextInt(5);
            currentSpeed++;
            break;
        }
    }

    private void newBuff(){
        start: while(true){
            buff.setX(rand.nextInt((600/size)-1)); //-1 so fruit is in window bounds
            buff.setY(rand.nextInt((400/size)-1)); //-1 so fruit is in winndow bounds

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
            debuff.setX(rand.nextInt((600/size)-1)); //-1 so fruit is in window bounds
            debuff.setY(rand.nextInt((400/size)-1)); //-1 so fruit is in winndow bounds

            for (SnakeBody snakeFragment : snake){
                if(snakeFragment.getX() == debuff.getX() && snakeFragment.getY() == debuff.getY()){
                    continue start;
                }
            }
            break;
        }
    }

}

