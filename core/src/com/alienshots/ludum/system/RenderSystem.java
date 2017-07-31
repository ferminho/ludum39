package com.alienshots.ludum.system;

import com.alienshots.ludum.component.DisplayComponent;
import com.alienshots.ludum.component.PositionComponent;
import com.alienshots.ludum.component.WorldChargeComponent;
import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

import java.util.ArrayList;
import java.util.List;

public class RenderSystem extends IteratingSystem {

    private final Camera camera;
    private final Batch batch;
    private final List<Entity> entities;
    private final ComponentMapper<PositionComponent> positionMapper;
    private final ComponentMapper<DisplayComponent> displayMapper;
    private final WorldChargeComponent charge;

    public RenderSystem(Camera camera, Entity world) {
        super(Family.all(PositionComponent.class, DisplayComponent.class).get());

        this.camera = camera;
        this.batch = new SpriteBatch();
        this.entities = new ArrayList<>();
        this.positionMapper = ComponentMapper.getFor(PositionComponent.class);
        this.displayMapper = ComponentMapper.getFor(DisplayComponent.class);
        charge = world.getComponent(WorldChargeComponent.class);
    }

    @Override
    protected void processEntity(Entity entity, float deltaTime) {
        entities.add(entity);
    }

    @Override
    public void update(float deltaTime) {
        super.update(deltaTime);
        float alpha = charge.getChargeLevel() / WorldChargeComponent.MAX_CHARGE;

        batch.setProjectionMatrix(camera.combined);
        batch.begin();
        batch.setColor(0f, 0f, 0f, alpha);
        entities.stream().filter(e -> displayMapper.get(e).isVisible())
                         .map(e -> positionMapper.get(e).getRegion())
                         .forEach(region -> {
                             if (region == null)
                                 System.out.println("NO REGION TO DRAW");
                             else
                                 batch.draw(region, region.getRegionX(), flippedY(region));
                         });
        batch.end();
        entities.clear();
    }

    private float flippedY(TextureRegion region) {
        return camera.viewportHeight - region.getRegionY() - region.getRegionHeight();
    }
}
