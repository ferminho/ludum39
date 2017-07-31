package com.alienshots.ludum.component;

import com.badlogic.ashley.core.Component;

public class WorldChargeComponent implements Component {
    public static final float MAX_CHARGE = 100f;
    public static final float CHARGE_LOSS_PER_TICK = .5f;
    public WorldChargeComponent() { setChargeLevel(MAX_CHARGE); }
    private float chargeLevel;

    public WorldChargeComponent(float chargeLevel) {
        this.setChargeLevel(chargeLevel);
    }
    
    public float getChargeLevel() {
        return chargeLevel;
    }

    public void setChargeLevel(float chargeLevel) {
        this.chargeLevel = chargeLevel;
    }
}
