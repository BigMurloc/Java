package sample.Class;

import javafx.scene.input.KeyCode;

public enum Movement {
    UP(KeyCode.W),
    DOWN(KeyCode.S),
    LEFT(KeyCode.A),
    RIGHT(KeyCode.D);

    private KeyCode key;

    Movement(KeyCode key) {
        this.key = key;
    }

    public  KeyCode getKey() {
        return key;
    }

}
