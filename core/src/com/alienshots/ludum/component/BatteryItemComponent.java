package com.alienshots.ludum.component;

import com.badlogic.ashley.core.Component;

public class BatteryItemComponent implements Component {
    public BatteryItemComponent(boolean carryingBattery) {
        this.carryingBattery = carryingBattery;
    }

    private boolean carryingBattery;

    public boolean isCarryingBattery() {
        return carryingBattery;
    }

    public void setCarryingBattery(boolean carryingBattery) {
        this.carryingBattery = carryingBattery;
    }
}
