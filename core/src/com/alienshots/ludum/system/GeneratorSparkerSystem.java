package com.alienshots.ludum.system;

import com.alienshots.ludum.Time;
import com.alienshots.ludum.asset.texture.GameScreenAtlas;
import com.alienshots.ludum.component.DecoSparkComponent;
import com.alienshots.ludum.component.DisplayComponent;
import com.alienshots.ludum.component.GeneratorLevelComponent;
import com.alienshots.ludum.component.PositionComponent;
import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;

public class GeneratorSparkerSystem extends IteratingSystem {

    private static final int ANIM_INTERVAL_MS = 400;
    private final ComponentMapper<GeneratorLevelComponent> generatorLevelMapper;
    private final ComponentMapper<PositionComponent> positionMapper;
    private final ComponentMapper<DisplayComponent> displayMapper;
    private Time.SpanTimer timer = new Time.SpanTimer(ANIM_INTERVAL_MS);

    public GeneratorSparkerSystem() {
        super(Family.all(DecoSparkComponent.class).get());

        generatorLevelMapper = ComponentMapper.getFor(GeneratorLevelComponent.class);
        positionMapper = ComponentMapper.getFor(PositionComponent.class);
        displayMapper = ComponentMapper.getFor(DisplayComponent.class);
    }

    @Override
    protected void processEntity(Entity generator, float deltaTime) {
        DisplayComponent displayComponent = displayMapper.get(generator);
        GeneratorLevelComponent generatorLevelComponent = generatorLevelMapper.get(generator);
        PositionComponent positionComponent = positionMapper.get(generator);
        GameScreenAtlas.AtlasCoordinates coords = positionComponent.getCoords();

        int column = coords.getColumn();
        boolean visible = false;
        if (generatorLevelComponent.getLevel() == GeneratorLevelComponent.MAX_LEVEL) {
            visible = true;
            timer.update();
            if (timer.isFinished()) {
                column ++;
                if (column == 6) column = 1;
                timer = new Time.SpanTimer(ANIM_INTERVAL_MS);
            }
        } else {
            column = 1;
        }
        coords.setColumn(column);
        displayComponent.setVisible(visible);
        positionComponent.setRegion(GameScreenAtlas.instance.getScreenTexture(DecoSparkComponent.class, coords));

    }
}
