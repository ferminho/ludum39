package com.alienshots.ludum.system;

import com.alienshots.ludum.Time;
import com.alienshots.ludum.asset.texture.GameScreenAtlas;
import com.alienshots.ludum.component.DisplayComponent;
import com.alienshots.ludum.component.DropComponent;
import com.alienshots.ludum.component.PositionComponent;
import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;

import static com.alienshots.ludum.asset.texture.GameScreenAtlas.AtlasCoordinates;

public class DropMovementSystem extends IteratingSystem implements MovementSystem {

    private static final int RESTART_DELAY_TICKS = 2;

    private final ComponentMapper<DropComponent> dropMapper;
    private final ComponentMapper<PositionComponent> positionMapper;
    private final ComponentMapper<DisplayComponent> displayMapper;

    public DropMovementSystem() {
        super(Family.all(PositionComponent.class, DisplayComponent.class, DropComponent.class).get());
        dropMapper = ComponentMapper.getFor(DropComponent.class);
        positionMapper = ComponentMapper.getFor(PositionComponent.class);
        displayMapper = ComponentMapper.getFor(DisplayComponent.class);
    }

    @Override
    protected void processEntity(Entity drop, float deltaTime) {
        DisplayComponent displayComponent = displayMapper.get(drop);
        if (displayComponent.isVisible()) {
            moveDrop(drop);
        } else if (dropMapper.get(drop).getAppearTimer().isTicking()) {
            displayComponent.setVisible(true);
        }
    }

    private void moveDrop(Entity drop) {
        PositionComponent position = positionMapper.get(drop);
        AtlasCoordinates coords = position.getCoords();
        GameScreenAtlas.VerticalPosition vPos = coords.getVerticalPosition();

        switch (vPos) {
            case HIGH:
                coords.setVerticalPosition(GameScreenAtlas.VerticalPosition.MEDIUM);
                break;
            case MEDIUM:
                coords.setVerticalPosition(GameScreenAtlas.VerticalPosition.LOW);
                break;
            case LOW:
                resetDrop(drop);
                break;
        }
        position.setRegion(GameScreenAtlas.instance.getScreenTexture(DropComponent.class, coords));
    }

    private void resetDrop(Entity drop) {
        displayMapper.get(drop).setVisible(false);
        AtlasCoordinates coords = positionMapper.get(drop).getCoords();
        coords.setVerticalPosition(GameScreenAtlas.VerticalPosition.HIGH);
        Time.Timer timer = dropMapper.get(drop).getAppearTimer();
        timer.setTickRate(RESTART_DELAY_TICKS);
        timer.reset();
    }
}
