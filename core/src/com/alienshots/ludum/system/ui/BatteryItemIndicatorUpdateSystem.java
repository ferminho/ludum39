package com.alienshots.ludum.system.ui;

import com.alienshots.ludum.Time;
import com.alienshots.ludum.component.*;
import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;

import static com.alienshots.ludum.asset.texture.GameScreenAtlas.AtlasCoordinates;

public class BatteryItemIndicatorUpdateSystem extends IteratingSystem {

    private enum CarryingState {
        NO_BATTERY, JUST_PICKED_UP, CARRYING
    }

    private final static int BLINK_RATE_MS = 250;
    private final static int TOTAL_BLINKS = 6;
    private final ComponentMapper<BatteryItemComponent> batteryItemMapper;
    private final ComponentMapper<PositionComponent> positionMapper;
    private final ComponentMapper<DisplayComponent> displayMapper;
    private CarryingState carryingState = CarryingState.NO_BATTERY;
    private Time.BlinkingTimer blinkingTimer;

    public BatteryItemIndicatorUpdateSystem() {
        super(Family.all(PositionComponent.class, DisplayComponent.class, BatteryItemComponent.class)
                .exclude(PlayerComponent.class).get());
        batteryItemMapper = ComponentMapper.getFor(BatteryItemComponent.class);
        positionMapper = ComponentMapper.getFor(PositionComponent.class);
        displayMapper = ComponentMapper.getFor(DisplayComponent.class);
    }

    @Override
    protected void processEntity(Entity battery, float deltaTime) {
        DisplayComponent displayComponent = displayMapper.get(battery);
        BatteryItemComponent batteryComponent = batteryItemMapper.get(battery);
        PositionComponent positionComponent = positionMapper.get(battery);
        AtlasCoordinates coords = positionComponent.getCoords();

        boolean visible = false;
        if (batteryComponent.isCarryingBattery()) {
            if (carryingState == CarryingState.NO_BATTERY) {
                carryingState = CarryingState.JUST_PICKED_UP;
                blinkingTimer = new Time.BlinkingTimer(500, TOTAL_BLINKS, true);
                visible = blinkingTimer.isBlinkState();
            } else if (carryingState == CarryingState.JUST_PICKED_UP) {
                blinkingTimer.update();
                if (blinkingTimer.isFinished()) {
                    carryingState = CarryingState.CARRYING;
                    visible = true;
                } else {
                    visible = blinkingTimer.isBlinkState();
                }
            } else {
                visible = true;
            }
        } else {
            carryingState = CarryingState.NO_BATTERY;
        }
        displayComponent.setVisible(visible);
    }
}
