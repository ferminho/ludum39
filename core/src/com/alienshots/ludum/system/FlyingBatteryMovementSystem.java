package com.alienshots.ludum.system;

import com.alienshots.ludum.Time;
import com.alienshots.ludum.component.DisplayComponent;
import com.alienshots.ludum.component.FlyingBatteryComponent;
import com.alienshots.ludum.component.FlyingBatteryLaunchComponent;
import com.alienshots.ludum.component.GeneratorLevelComponent;
import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;

public class FlyingBatteryMovementSystem extends IteratingSystem {

    private static final int FLYING_TIME_MS = 750;

    private final ComponentMapper<FlyingBatteryLaunchComponent> launchMapper;
    private final ComponentMapper<DisplayComponent> displayMapper;
    private final ComponentMapper<GeneratorLevelComponent> generatorLevelMapper;
    private Time.SpanTimer flyingTimer;

    public FlyingBatteryMovementSystem() {
        super(Family.all(FlyingBatteryComponent.class).get());
        launchMapper = ComponentMapper.getFor(FlyingBatteryLaunchComponent.class);
        displayMapper = ComponentMapper.getFor(DisplayComponent.class);
        generatorLevelMapper = ComponentMapper.getFor(GeneratorLevelComponent.class);
    }

    @Override
    protected void processEntity(Entity battery, float deltaTime) {
        DisplayComponent displayComponent = displayMapper.get(battery);
        FlyingBatteryLaunchComponent launchComponent = launchMapper.get(battery);
        GeneratorLevelComponent levelComponent = generatorLevelMapper.get(battery);

        boolean visible = false;
        if (launchComponent.isToBeLaunched()) {
            launchComponent.setToBeLaunched(false);
            flyingTimer = new Time.SpanTimer(FLYING_TIME_MS);
            visible = true;
        } else if (flyingTimer != null) {
            flyingTimer.update();
            if (flyingTimer.isFinished()) {
                flyingTimer = null;
                levelComponent.setLevel(levelComponent.getLevel() + 1);
                visible = false;
            } else {
                visible = true;
            }
        }
        displayComponent.setVisible(visible);
    }
}
