package com.alienshots.ludum.component;

import com.badlogic.ashley.core.Component;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class SawDirectionComponent implements Component {
    public enum Direction { LEFT, RIGHT };
    private Direction direction;
}
