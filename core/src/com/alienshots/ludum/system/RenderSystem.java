package com.alienshots.ludum.system;

import com.alienshots.ludum.asset.texture.GameScreenAtlas;
import com.alienshots.ludum.component.PositionComponent;
import com.alienshots.ludum.component.TextureComponent;
import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class RenderSystem extends IteratingSystem {

    private final Batch batch;
    private final List<Entity> entities;
//    private final ComponentMapper<PositionComponent> positionMapper;
    private final ComponentMapper<TextureComponent> textureMapper;

    public RenderSystem() {
//        super(Family.all(PositionComponent.class, TextureComponent.class).get());
        super(Family.all(TextureComponent.class).get());

        this.batch = new SpriteBatch();
        this.entities = new ArrayList<>();
//        this.positionMapper = ComponentMapper.getFor(PositionComponent.class);
        this.textureMapper = ComponentMapper.getFor(TextureComponent.class);
    }

    @Override
    protected void processEntity(Entity entity, float deltaTime) {
        entities.add(entity);
    }

//    private GameScreenAtlas a = new GameScreenAtlas();

    @Override
    public void update(float deltaTime) {
        super.update(deltaTime);

//        entities.forEach(e -> {
//            if (Math.floor(System.currentTimeMillis() / 1000) % 2 == 0) {
//                textureMapper.get(e).region = a.getScreenTexture(GameScreenAtlas.ScreenTexture.PLAYER_L1_1);
//            } else {
//                textureMapper.get(e).region = a.getScreenTexture(GameScreenAtlas.ScreenTexture.PLAYER_L1_2);
//            }
//        });

        batch.begin();
        entities.stream().map(e -> textureMapper.get(e).region).filter(Objects::nonNull).forEach(region -> {
            batch.draw(region, region.getRegionX(), region.getRegionY());
        });
        batch.end();
        entities.clear();
    }
}
