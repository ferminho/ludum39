package com.alienshots.ludum.component;

import com.badlogic.ashley.core.Component;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FlyingBatteryLaunchComponent implements Component {
    private boolean toBeLaunched;
}
