package com.alienshots.ludum.system;

import com.alienshots.ludum.component.BatteryItemComponent;
import com.alienshots.ludum.component.FlyingBatteryComponent;
import com.alienshots.ludum.component.PlayerComponent;
import com.alienshots.ludum.component.PositionComponent;
import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;

import static com.alienshots.ludum.asset.texture.GameScreenAtlas.AtlasCoordinates;
import static com.alienshots.ludum.asset.texture.GameScreenAtlas.VerticalPosition;

public class PlayerEventsSystem extends IteratingSystem {

    private final ComponentMapper<PositionComponent> positionMapper;
    private final ComponentMapper<BatteryItemComponent> batteryMapper;
    private final ComponentMapper<FlyingBatteryComponent> flyingBatteryMapper;

    public PlayerEventsSystem() {
        super(Family.all(PlayerComponent.class).get());

        positionMapper = ComponentMapper.getFor(PositionComponent.class);
        batteryMapper = ComponentMapper.getFor(BatteryItemComponent.class);
        flyingBatteryMapper = ComponentMapper.getFor(FlyingBatteryComponent.class);
    }

    @Override
    protected void processEntity(Entity player, float deltaTime) {
        PositionComponent positionComponent = positionMapper.get(player);
        AtlasCoordinates coords = positionComponent.getCoords();
        BatteryItemComponent battery = batteryMapper.get(player);
        FlyingBatteryComponent flyingBattery = flyingBatteryMapper.get(player);

        if (coords.getLevel() == 1 && coords.getColumn() == 1) {
            battery.setCarryingBattery(true);
        }
        if (battery.isCarryingBattery() && coords.getLevel() == 4 &&
                coords.getColumn() == 7 && coords.getVerticalPosition() == VerticalPosition.HIGH) {
            flyingBattery.setFlying(true);
            battery.setCarryingBattery(false);
        }
    }
}
