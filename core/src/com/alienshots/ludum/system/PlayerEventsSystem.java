package com.alienshots.ludum.system;

import com.alienshots.ludum.component.*;
import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;

import static com.alienshots.ludum.asset.texture.GameScreenAtlas.AtlasCoordinates;
import static com.alienshots.ludum.asset.texture.GameScreenAtlas.VerticalPosition;

public class PlayerEventsSystem extends IteratingSystem {

    private final ComponentMapper<PositionComponent> positionMapper;
    private final ComponentMapper<BatteryItemComponent> batteryMapper;
    private final ComponentMapper<FlyingBatteryLaunchComponent> batteryLaunchMapper;
    private final ComponentMapper<GeneratorLevelComponent> generatorLevelMapper;
    private final ComponentMapper<LeverStateComponent> leverStateMapper;

    public PlayerEventsSystem() {
        super(Family.all(PlayerComponent.class).get());

        positionMapper = ComponentMapper.getFor(PositionComponent.class);
        batteryMapper = ComponentMapper.getFor(BatteryItemComponent.class);
        batteryLaunchMapper = ComponentMapper.getFor(FlyingBatteryLaunchComponent.class);
        generatorLevelMapper = ComponentMapper.getFor(GeneratorLevelComponent.class);
        leverStateMapper = ComponentMapper.getFor(LeverStateComponent.class);
    }

    @Override
    protected void processEntity(Entity player, float deltaTime) {
        PositionComponent positionComponent = positionMapper.get(player);
        AtlasCoordinates coords = positionComponent.getCoords();
        BatteryItemComponent battery = batteryMapper.get(player);
        FlyingBatteryLaunchComponent batteryLauncher = batteryLaunchMapper.get(player);
        GeneratorLevelComponent generatorLevel = generatorLevelMapper.get(player);
        LeverStateComponent leverState = leverStateMapper.get(player);

        if (coords.getLevel() == 1 && coords.getColumn() == 1) {
            battery.setCarryingBattery(true);
        }
        if (coords.getLevel() == 4) {
            if (battery.isCarryingBattery() && coords.getColumn() == 7 &&
                    generatorLevel.getLevel() < GeneratorLevelComponent.MAX_LEVEL &&
                    coords.getVerticalPosition() == VerticalPosition.HIGH) {
                batteryLauncher.setToBeLaunched(true);
                battery.setCarryingBattery(false);
            } else if (coords.getColumn() == 1 && !leverState.isChargePosition()) {
                leverState.setChargePosition(true);
            }
        }
    }
}
