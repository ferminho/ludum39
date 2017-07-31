package com.alienshots.ludum.component;

import com.badlogic.ashley.core.Component;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class LifeComponent implements Component {
    private int lives;
}
