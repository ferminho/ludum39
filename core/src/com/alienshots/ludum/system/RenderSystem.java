package com.alienshots.ludum.system;

import com.alienshots.ludum.component.DisplayComponent;
import com.alienshots.ludum.component.PositionComponent;
import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

import java.util.ArrayList;
import java.util.List;

public class RenderSystem extends IteratingSystem {

    private final Camera camera;
    private final Batch batch;
    private final List<Entity> entities;
    private final ComponentMapper<PositionComponent> positionMapper;
    private final ComponentMapper<DisplayComponent> displayMapper;

    public RenderSystem(Camera camera) {
        super(Family.all(PositionComponent.class, DisplayComponent.class).get());
        this.camera = camera;
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

        batch.setProjectionMatrix(camera.combined);
        batch.begin();
        entities.stream().filter(e -> displayMapper.get(e).isVisible())
                         .map(e -> positionMapper.get(e).getRegion())
                         .forEach(region -> {
                             batch.draw(region, region.getRegionX(),
                                     camera.viewportHeight - region.getRegionY() - region.getRegionHeight());
                         });
        batch.end();
        entities.clear();
    }
}
