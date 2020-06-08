package sample.Class;

public enum Difficulty {
    EASY(3, 3, 1),
    MEDIUM(5, 2, 2),
    HARD(7, 1, 3);

    private int diffculty;
    private int speedChangeBuff;
    private int speedChangeDebuff;

    Difficulty(int diffculty, int speedChangeBuff, int speedChangeDebuff) {
        this.diffculty = diffculty;
        this.speedChangeBuff = speedChangeBuff;
        this.speedChangeDebuff = speedChangeDebuff;
    }

    public int getSpeedChangeBuff() {
        return speedChangeBuff;
    }

    public int getSpeedChangeDebuff() {
        return speedChangeDebuff;
    }

    public int getDiffculty() {
        return diffculty;
    }
}
