package com.alienshots.ludum.system;

import com.alienshots.ludum.Time;
import com.alienshots.ludum.asset.texture.GameScreenAtlas;
import com.alienshots.ludum.asset.texture.GameScreenAtlas.AtlasCoordinates;
import com.alienshots.ludum.component.DisplayComponent;
import com.alienshots.ludum.component.LeverComponent;
import com.alienshots.ludum.component.LeverStateComponent;
import com.alienshots.ludum.component.PositionComponent;
import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;

public class LeverMovementSystem extends IteratingSystem {

    private static final int BLINK_INTERVAL_MS = 350;
    private final ComponentMapper<LeverStateComponent> leverStateMapper;
    private final ComponentMapper<DisplayComponent> displayMapper;
    private final ComponentMapper<PositionComponent> positionMapper;
    private Time.BlinkingTimer blinkingTimer;

    public LeverMovementSystem() {
        super(Family.all(LeverComponent.class).get());
        leverStateMapper = ComponentMapper.getFor(LeverStateComponent.class);
        displayMapper = ComponentMapper.getFor(DisplayComponent.class);
        positionMapper = ComponentMapper.getFor(PositionComponent.class);
    }

    @Override
    protected void processEntity(Entity lever, float deltaTime) {
        DisplayComponent displayComponent = displayMapper.get(lever);
        PositionComponent positionComponent = positionMapper.get(lever);
        AtlasCoordinates coords = positionComponent.getCoords();
        LeverStateComponent leverState = leverStateMapper.get(lever);

        if (leverState.isChargePosition()) {
            coords.setColumn(1);
            displayComponent.setVisible(true);
            blinkingTimer = null;
        } else {
            coords.setColumn(2);
            if (blinkingTimer == null) {
                blinkingTimer = new Time.BlinkingTimer(BLINK_INTERVAL_MS, 0, true);
            }
            blinkingTimer.update();
            displayComponent.setVisible(blinkingTimer.isBlinkState());
        }
        positionComponent.setRegion(GameScreenAtlas.instance.getScreenTexture(LeverComponent.class, coords));
    }
}
