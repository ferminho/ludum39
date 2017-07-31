package com.alienshots.ludum.component;

import com.badlogic.ashley.core.Component;

public class LeverStateComponent implements Component {
    private boolean chargePosition;

    public LeverStateComponent(boolean chargePosition) {
        this.chargePosition = chargePosition;
    }

    public boolean isChargePosition() {
        return chargePosition;
    }

    public void setChargePosition(boolean chargePosition) {
        this.chargePosition = chargePosition;
    }
}
