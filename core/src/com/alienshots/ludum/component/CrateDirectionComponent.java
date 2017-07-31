package com.alienshots.ludum.component;

import com.badlogic.ashley.core.Component;

public class CrateDirectionComponent implements Component {
    public Direction getDirection() {
        return direction;
    }

    public CrateDirectionComponent(Direction direction) {
        this.direction = direction;
    }

    public void setDirection(Direction direction) {
        this.direction = direction;
    }

    public enum Direction { LEFT, RIGHT };
    private Direction direction;
}
