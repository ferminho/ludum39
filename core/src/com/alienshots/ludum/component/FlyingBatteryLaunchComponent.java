package com.alienshots.ludum.component;

import com.badlogic.ashley.core.Component;

public class FlyingBatteryLaunchComponent implements Component {
    private boolean toBeLaunched;

    public boolean isToBeLaunched() {
        return toBeLaunched;
    }

    public void setToBeLaunched(boolean toBeLaunched) {
        this.toBeLaunched = toBeLaunched;
    }
}
