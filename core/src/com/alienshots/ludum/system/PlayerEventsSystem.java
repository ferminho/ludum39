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
    private final ComponentMapper<SawDirectionComponent> sawDirectionMapper;
    private final ComponentMapper<CrateDirectionComponent> crateDirectionMapper;

    public PlayerEventsSystem() {
        super(Family.all(PlayerComponent.class).get());

        positionMapper = ComponentMapper.getFor(PositionComponent.class);
        batteryMapper = ComponentMapper.getFor(BatteryItemComponent.class);
        batteryLaunchMapper = ComponentMapper.getFor(FlyingBatteryLaunchComponent.class);
        generatorLevelMapper = ComponentMapper.getFor(GeneratorLevelComponent.class);
        leverStateMapper = ComponentMapper.getFor(LeverStateComponent.class);
        sawDirectionMapper = ComponentMapper.getFor(SawDirectionComponent.class);
        crateDirectionMapper = ComponentMapper.getFor(CrateDirectionComponent.class);
    }

    @Override
    protected void processEntity(Entity player, float deltaTime) {
        PositionComponent positionComponent = positionMapper.get(player);
        AtlasCoordinates coords = positionComponent.getCoords();
        BatteryItemComponent battery = batteryMapper.get(player);
        FlyingBatteryLaunchComponent batteryLauncher = batteryLaunchMapper.get(player);
        GeneratorLevelComponent generatorLevel = generatorLevelMapper.get(player);
        LeverStateComponent leverState = leverStateMapper.get(player);
        SawDirectionComponent sawDirection = sawDirectionMapper.get(player);
        CrateDirectionComponent crateDirection = crateDirectionMapper.get(player);

        if (coords.getLevel() == 1) {
            if (coords.getColumn() == 1) {
                // PICK UP BATTERY
                battery.setCarryingBattery(true);
                // SAWS MOVING TO THE RIGHT
                sawDirection.setDirection(SawDirectionComponent.Direction.LEFT);
            } else if (coords.getColumn() == 8) {
                // SAWS MOVING TO THE LEFT
                sawDirection.setDirection(SawDirectionComponent.Direction.RIGHT);
            }
        } else if (coords.getLevel() == 2) {
            if (coords.getColumn() == 1 && coords.getVerticalPosition() == VerticalPosition.HIGH) {
                // CRATES MOVING TO THE RIGHT
                crateDirection.setDirection(CrateDirectionComponent.Direction.RIGHT);
            }
        } else if (coords.getLevel() == 4) {
            if (coords.getColumn() == 1) {
                if (!leverState.isChargePosition()) {
                    // PUSH LEVER
                    leverState.setChargePosition(true);
                }
            } else if (coords.getColumn() == 7) {
                if (coords.getVerticalPosition() == VerticalPosition.HIGH) {
                    if (battery.isCarryingBattery() && coords.getColumn() == 7 &&
                            generatorLevel.getLevel() < GeneratorLevelComponent.MAX_LEVEL) {
                        // THROW BATTERY
                        batteryLauncher.setToBeLaunched(true);
                        battery.setCarryingBattery(false);
                    }
                } else if (coords.getVerticalPosition() == VerticalPosition.LOW) {
                    // CRATES MOVING TO THE LEFT
                    crateDirection.setDirection(CrateDirectionComponent.Direction.LEFT);
                }
            }
        }
    }
}
