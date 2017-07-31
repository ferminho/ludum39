package com.alienshots.ludum.component;

import com.alienshots.ludum.Time;
import com.badlogic.ashley.core.Component;

public class DropComponent implements Component {
    private final Time.Timer appearTimer;

    public DropComponent(Time.Timer appearTimer) {
        this.appearTimer = appearTimer;
    }

    public Time.Timer getAppearTimer() {
        return appearTimer;
    }
}
