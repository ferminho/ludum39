package com.alienshots.ludum.component;

import com.badlogic.ashley.core.Component;

public class SawDirectionComponent implements Component {
    public enum Direction { LEFT, RIGHT };
    private Direction direction;

    public SawDirectionComponent(Direction direction) {
        this.setDirection(direction);
    }

    public Direction getDirection() {
        return direction;
    }

    public void setDirection(Direction direction) {
        this.direction = direction;
    }
}
