package com.alienshots.ludum.component;

import com.badlogic.ashley.core.Component;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FlyingBatteryComponent implements Component {
    private boolean flying;
}
