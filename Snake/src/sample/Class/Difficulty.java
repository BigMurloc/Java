package sample.Class;

public enum Difficulty {
    EASY(1),
    MEDIUM(5),
    HARD(10);

    private int diffculty;

    Difficulty(int diffculty) {
        this.diffculty = diffculty;
    }

    public int getDiffculty() {
        return diffculty;
    }
}
