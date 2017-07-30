package com.alienshots.ludum.system;

import com.alienshots.ludum.component.DisplayComponent;
import com.alienshots.ludum.component.PositionComponent;
import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import java.util.ArrayList;
import java.util.List;

public class RenderSystem extends IteratingSystem {

    private final Batch batch;
    private final List<Entity> entities;
    private final ComponentMapper<PositionComponent> positionMapper;
    private final ComponentMapper<DisplayComponent> displayMapper;

    public RenderSystem() {
        super(Family.all(PositionComponent.class, DisplayComponent.class).get());

        this.batch = new SpriteBatch();
        this.entities = new ArrayList<>();
        this.positionMapper = ComponentMapper.getFor(PositionComponent.class);
        this.displayMapper = ComponentMapper.getFor(DisplayComponent.class);
    }

    @Override
    protected void processEntity(Entity entity, float deltaTime) {
        entities.add(entity);
    }

    @Override
    public void update(float deltaTime) {
        super.update(deltaTime);

        batch.begin();
        entities.stream().filter(e -> displayMapper.get(e).isVisible())
                         .map(e -> positionMapper.get(e).getRegion())
                         .forEach(region -> {
                             batch.draw(region, region.getRegionX(), region.getRegionY());
                         });
        batch.end();
        entities.clear();
    }
}
