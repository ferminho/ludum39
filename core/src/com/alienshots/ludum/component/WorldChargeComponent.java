package com.alienshots.ludum.component;

import com.badlogic.ashley.core.Component;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class WorldChargeComponent implements Component {
    public static final float MAX_CHARGE = 100f;
    public static final float CHARGE_LOSS_PER_TICK = .75f;
    public WorldChargeComponent() { chargeLevel = MAX_CHARGE; }
    private float chargeLevel;
}
