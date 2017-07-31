package com.alienshots.ludum.system.ui;

import com.alienshots.ludum.asset.texture.GameScreenAtlas;
import com.alienshots.ludum.component.*;
import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;

import static com.alienshots.ludum.asset.texture.GameScreenAtlas.AtlasCoordinates;

public class GeneratorLevelIndicatorUpdateSystem extends IteratingSystem {

    private final ComponentMapper<GeneratorLevelComponent> generatorLevelMapper;
    private final ComponentMapper<PositionComponent> positionMapper;
    private final ComponentMapper<DisplayComponent> displayMapper;

    public GeneratorLevelIndicatorUpdateSystem() {
        super(Family.all(GeneratorComponent.class).get());
        generatorLevelMapper = ComponentMapper.getFor(GeneratorLevelComponent.class);
        positionMapper = ComponentMapper.getFor(PositionComponent.class);
        displayMapper = ComponentMapper.getFor(DisplayComponent.class);
    }

    @Override
    protected void processEntity(Entity generator, float deltaTime) {
        DisplayComponent displayComponent = displayMapper.get(generator);
        GeneratorLevelComponent generatorLevelComponent = generatorLevelMapper.get(generator);
        PositionComponent positionComponent = positionMapper.get(generator);
        AtlasCoordinates coords = positionComponent.getCoords();

        int column = 1;
        boolean visible = true;
        if (generatorLevelComponent.getLevel() == 0) {
            visible = false;
        } else {
            column = generatorLevelComponent.getLevel();
        }
        displayComponent.setVisible(visible);
        coords.setColumn(column);
        positionComponent.setRegion(GameScreenAtlas.instance.getScreenTexture(GeneratorLevelComponent.class, coords));
    }
}
