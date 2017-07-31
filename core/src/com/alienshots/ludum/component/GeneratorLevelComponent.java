package com.alienshots.ludum.component;

import com.badlogic.ashley.core.Component;

public class GeneratorLevelComponent implements Component {
    public static final int MAX_LEVEL = 3;

    private int level;

    public GeneratorLevelComponent(int level) {
        this.level = level;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }
}
