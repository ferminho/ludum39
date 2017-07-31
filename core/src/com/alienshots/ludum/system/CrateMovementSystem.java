package com.alienshots.ludum.system;

import com.alienshots.ludum.Time;
import com.alienshots.ludum.asset.texture.GameScreenAtlas;
import com.alienshots.ludum.component.*;
import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;

import java.util.ArrayList;
import java.util.List;

import static com.alienshots.ludum.Time.Timer;
import static com.alienshots.ludum.asset.texture.GameScreenAtlas.AtlasCoordinates;

public class CrateMovementSystem extends IteratingSystem implements MovementSystem {

    private final ComponentMapper<PositionComponent> positionMapper;
    private final ComponentMapper<DisplayComponent> displayMapper;
    private final ComponentMapper<CrateDirectionComponent> directionMapper;
    private final ComponentMapper<CollisionComponent> collisionMapper;
    private final List<Entity> crateReserve;
    private final Timer delayBetweenCrates;

    public CrateMovementSystem() {
        super(Family.all(CrateComponent.class, PositionComponent.class, DisplayComponent.class).get());

        positionMapper = ComponentMapper.getFor(PositionComponent.class);
        displayMapper = ComponentMapper.getFor(DisplayComponent.class);
        directionMapper = ComponentMapper.getFor(CrateDirectionComponent.class);
        collisionMapper = ComponentMapper.getFor(CollisionComponent.class);
        crateReserve = new ArrayList<>();
        delayBetweenCrates = Time.newTimer(3);
    }

    @Override
    protected void processEntity(Entity crate, float deltaTime) {
        DisplayComponent displayComponent = displayMapper.get(crate);

        collisionMapper.get(crate).setPrevPosInGameTimeRef(positionMapper.get(crate).getCoords());

        if (displayComponent.isVisible()) {
            moveCrate(crate);
        } else if (delayBetweenCrates.isTicking()) {
            crateReserve.add(crate);
        }
    }

    @Override
    public void update(float deltaTime) {
        super.update(deltaTime);

        crateReserve.stream().findAny().ifPresent(this::throwNewCrate);
        crateReserve.clear();
    }

    private void moveCrate(Entity crate) {
        AtlasCoordinates coords = positionMapper.get(crate).getCoords();
        CrateDirectionComponent direction = directionMapper.get(crate);

        if (direction.getDirection() == CrateDirectionComponent.Direction.LEFT) {
            if (coords.getColumn() % 2 != 0) // just changed direction, move to "even space"
                coords.setColumn(coords.getColumn() + 1);
            coords.setColumn(coords.getColumn() - 2);
            if (coords.getColumn() == 0) {
                coords.setColumn(16);
                displayMapper.get(crate).setVisible(false);
            }
        } else {
            if (coords.getColumn() % 2 == 0) // just changed direction, move to "odd space"
                coords.setColumn(coords.getColumn() - 1);
            coords.setColumn(coords.getColumn() + 2);
            if (coords.getColumn() == 17) {
                coords.setColumn(1);
                displayMapper.get(crate).setVisible(false);
            }
        }
        positionMapper.get(crate).setRegion(GameScreenAtlas.instance.getScreenTexture(CrateComponent.class, coords));
    }

    private void throwNewCrate(Entity crate) {
        AtlasCoordinates coords = positionMapper.get(crate).getCoords();
        CrateDirectionComponent direction = directionMapper.get(crate);

        if (direction.getDirection() == CrateDirectionComponent.Direction.LEFT) {
            positionMapper.get(crate).getCoords().setColumn(16);
        } else {
            positionMapper.get(crate).getCoords().setColumn(1);
        }
        displayMapper.get(crate).setVisible(true);
        positionMapper.get(crate).setRegion(GameScreenAtlas.instance.getScreenTexture(CrateComponent.class, coords));
    }
}
