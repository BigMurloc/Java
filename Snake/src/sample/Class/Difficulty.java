package sample.Class;

public enum Difficulty {
    EASY(1),
    MEDIUM(5),
    HARD(55);

    private int speed;

    Difficulty(int speed) {
        this.speed = speed;
    }

    public int getSpeed() {
        return speed;
    }
}
