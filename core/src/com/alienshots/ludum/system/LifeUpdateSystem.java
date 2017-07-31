package com.alienshots.ludum.system;

import com.alienshots.ludum.Time;
import com.alienshots.ludum.asset.texture.GameScreenAtlas;
import com.alienshots.ludum.component.DisplayComponent;
import com.alienshots.ludum.component.DropComponent;
import com.alienshots.ludum.component.LifeComponent;
import com.alienshots.ludum.component.PositionComponent;
import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;

import static com.alienshots.ludum.asset.texture.GameScreenAtlas.AtlasCoordinates;

public class LifeUpdateSystem extends IteratingSystem {

    private final ComponentMapper<LifeComponent> lifeMapper;
    private final ComponentMapper<PositionComponent> positionMapper;
    private final ComponentMapper<DisplayComponent> displayMapper;

    public LifeUpdateSystem() {
        super(Family.all(PositionComponent.class, DisplayComponent.class, LifeComponent.class).get());
        lifeMapper = ComponentMapper.getFor(LifeComponent.class);
        positionMapper = ComponentMapper.getFor(PositionComponent.class);
        displayMapper = ComponentMapper.getFor(DisplayComponent.class);
    }

    @Override
    protected void processEntity(Entity life, float deltaTime) {
        DisplayComponent displayComponent = displayMapper.get(life);
        LifeComponent lifeComponent = lifeMapper.get(life);
        PositionComponent positionComponent = positionMapper.get(life);
        AtlasCoordinates coords = positionComponent.getCoords();

        int column = 1;
        boolean visible = true;
        if (lifeComponent.getLives() == 0) {
            visible = false;
        } else {
            column = Math.min(4, lifeComponent.getLives()); // we can only represent 4
        }
        displayComponent.setVisible(visible);
        coords.setColumn(column);
    }
}
