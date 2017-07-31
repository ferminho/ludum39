package com.alienshots.ludum.component;

import com.badlogic.ashley.core.Component;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class GeneratorLevelComponent implements Component {
    public static final int MAX_LEVEL = 1;

    private int level;
}
