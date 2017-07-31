package com.alienshots.ludum.system;

import com.alienshots.ludum.Time;
import com.alienshots.ludum.asset.texture.GameScreenAtlas;
import com.alienshots.ludum.component.DisplayComponent;
import com.alienshots.ludum.component.PositionComponent;
import com.alienshots.ludum.component.SawComponent;
import com.alienshots.ludum.component.SawDirectionComponent;
import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;

import java.util.ArrayList;
import java.util.List;

import static com.alienshots.ludum.Time.Timer;
import static com.alienshots.ludum.asset.texture.GameScreenAtlas.AtlasCoordinates;

public class SawMovementSystem extends IteratingSystem implements MovementSystem {

    private final ComponentMapper<PositionComponent> positionMapper;
    private final ComponentMapper<DisplayComponent> displayMapper;
    private final ComponentMapper<SawDirectionComponent> directionMapper;
    private final List<Entity> sawReserve;
    private final Timer delayBetweenSaws;

    public SawMovementSystem() {
        super(Family.all(SawComponent.class, PositionComponent.class, DisplayComponent.class).get());

        positionMapper = ComponentMapper.getFor(PositionComponent.class);
        displayMapper = ComponentMapper.getFor(DisplayComponent.class);
        directionMapper = ComponentMapper.getFor(SawDirectionComponent.class);
        sawReserve = new ArrayList<>();
        delayBetweenSaws = Time.newTimer(2);
    }

    @Override
    protected void processEntity(Entity saw, float deltaTime) {
        DisplayComponent displayComponent = displayMapper.get(saw);
        if (displayComponent.isVisible()) {
            moveSaw(saw);
        } else if (delayBetweenSaws.isTicking()) {
            sawReserve.add(saw);
        }
    }

    @Override
    public void update(float deltaTime) {
        super.update(deltaTime);

        sawReserve.stream().findAny().ifPresent(this::throwNewSaw);
        sawReserve.clear();
    }

    private void moveSaw(Entity saw) {
        AtlasCoordinates coords = positionMapper.get(saw).getCoords();
        SawDirectionComponent direction = directionMapper.get(saw);

        if (direction.getDirection() == SawDirectionComponent.Direction.LEFT) {
            if (coords.getColumn() % 2 == 0) // just changed direction, move to "odd space"
                coords.setColumn(coords.getColumn() + 1);
            coords.setColumn(coords.getColumn() - 2);
            if (coords.getColumn() == -1) {
                coords.setColumn(13);
                displayMapper.get(saw).setVisible(false);
            }
        } else {
            if (coords.getColumn() % 2 != 0) // just changed direction, move to "even space"
                coords.setColumn(coords.getColumn() - 1);
            coords.setColumn(coords.getColumn() + 2);
            if (coords.getColumn() == 16) {
                coords.setColumn(2);
                displayMapper.get(saw).setVisible(false);
            }
        }
        positionMapper.get(saw).setRegion(GameScreenAtlas.instance.getScreenTexture(SawComponent.class, coords));
    }

    private void throwNewSaw(Entity saw) {
        AtlasCoordinates coords = positionMapper.get(saw).getCoords();
        SawDirectionComponent direction = directionMapper.get(saw);

        if (direction.getDirection() == SawDirectionComponent.Direction.LEFT) {
            positionMapper.get(saw).getCoords().setColumn(13);
        } else {
            positionMapper.get(saw).getCoords().setColumn(2);
        }
        displayMapper.get(saw).setVisible(true);
        positionMapper.get(saw).setRegion(GameScreenAtlas.instance.getScreenTexture(SawComponent.class, coords));
    }
}
