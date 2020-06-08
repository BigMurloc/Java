package sample.classes;

import javafx.scene.input.KeyCode;

public enum Movement {
    //TODO zmiana enumow to zla praktyka
    UP(KeyCode.W),
    DOWN(KeyCode.S),
    LEFT(KeyCode.A),
    RIGHT(KeyCode.D);

    private KeyCode key;

    Movement(KeyCode key) {
        this.key = key;
    }

    public void setKey(KeyCode key) {
        this.key = key;
    }

    public KeyCode getKey() {
        return key;
    }
}
