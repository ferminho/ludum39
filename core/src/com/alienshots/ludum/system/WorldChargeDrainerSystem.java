package com.alienshots.ludum.system;

import com.alienshots.ludum.SoundManager;
import com.alienshots.ludum.component.WorldChargeComponent;
import com.alienshots.ludum.component.WorldComponent;
import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;

public class WorldChargeDrainerSystem extends IteratingSystem implements MovementSystem {

    private final ComponentMapper<WorldChargeComponent> worldChargeMapper;

    public WorldChargeDrainerSystem() {
        super(Family.all(WorldComponent.class).get());

        worldChargeMapper = ComponentMapper.getFor(WorldChargeComponent.class);
    }

    @Override
    protected void processEntity(Entity world, float deltaTime) {
        WorldChargeComponent worldCharge = worldChargeMapper.get(world);
        if (worldCharge.getChargeLevel() > 0f) {
            worldCharge.setChargeLevel(worldCharge.getChargeLevel() - WorldChargeComponent.CHARGE_LOSS_PER_TICK);
            if (worldCharge.getChargeLevel() < 0f) {
                worldCharge.setChargeLevel(0f);
                SoundManager.instance.play(SoundManager.SFX_SWITCH_OFF, 1f);
            }
            float chargeRatio = worldCharge.getChargeLevel() / WorldChargeComponent.MAX_CHARGE;
            if (chargeRatio > .5f)
                SoundManager.instance.setPitch(1f);
            else
                SoundManager.instance.setPitch(chargeRatio * 2f);
        }
    }
}
