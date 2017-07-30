package com.alienshots.ludum.system;

import com.alienshots.ludum.asset.texture.GameScreenAtlas;
import com.alienshots.ludum.component.PlayerComponent;
import com.alienshots.ludum.component.PositionComponent;
import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;

public class PlayerControlSystem extends IteratingSystem {

    private final ComponentMapper<PositionComponent> positionMapper;

    public PlayerControlSystem() {
        super(Family.all(PlayerComponent.class).get());

        positionMapper = ComponentMapper.getFor(PositionComponent.class);
    }

    @Override
    protected void processEntity(Entity entity, float deltaTime) {
        PositionComponent positionComponent = positionMapper.get(entity);

        if (Gdx.input.isKeyPressed(Input.Keys.A))
            positionComponent.setRegion(GameScreenAtlas.instance.getScreenTexture(PlayerComponent.class, new GameScreenAtlas.AtlasCoordinates(1, 1, GameScreenAtlas.VerticalPosition.LOW)));
        if (Gdx.input.isKeyPressed(Input.Keys.D))
            positionComponent.setRegion(GameScreenAtlas.instance.getScreenTexture(PlayerComponent.class, new GameScreenAtlas.AtlasCoordinates(1, 2, GameScreenAtlas.VerticalPosition.LOW)));
    }
}
