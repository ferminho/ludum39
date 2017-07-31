package com.alienshots.ludum.system;

import com.alienshots.ludum.Time;
import com.alienshots.ludum.component.DisplayComponent;
import com.alienshots.ludum.component.FlyingBatteryComponent;
import com.alienshots.ludum.component.PlayerComponent;
import com.alienshots.ludum.component.PositionComponent;
import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;

public class FlyingBatteryMovementSystem extends IteratingSystem {

    private static final int FLYING_TIME_MS = 750;

    private final ComponentMapper<FlyingBatteryComponent> batteryMapper;
    private final ComponentMapper<DisplayComponent> displayMapper;
    private Time.SpanTimer flyingTimer;

    public FlyingBatteryMovementSystem() {
        super(Family.all(PositionComponent.class, DisplayComponent.class, FlyingBatteryComponent.class)
                .exclude(PlayerComponent.class).get());
        batteryMapper = ComponentMapper.getFor(FlyingBatteryComponent.class);
        displayMapper = ComponentMapper.getFor(DisplayComponent.class);
    }

    @Override
    protected void processEntity(Entity battery, float deltaTime) {
        DisplayComponent displayComponent = displayMapper.get(battery);
        FlyingBatteryComponent flyingBatteryComponent = batteryMapper.get(battery);

        if (flyingBatteryComponent.isFlying()) {
            if (flyingTimer != null) {
                flyingTimer.update();
                if (flyingTimer.isFinished()) {
                    displayComponent.setVisible(false);
                    flyingBatteryComponent.setFlying(false);
                    flyingTimer = null;
                    //TODO: add battery to generator level
                }
            } else {
                flyingTimer = new Time.SpanTimer(FLYING_TIME_MS);
                displayComponent.setVisible(true);
            }
        } else {
            displayComponent.setVisible(false);
        }
    }
}
