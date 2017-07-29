package com.alienshots.ludum.system;

import com.alienshots.ludum.asset.texture.GameScreenAtlas;
import com.alienshots.ludum.component.PlayerComponent;
import com.alienshots.ludum.component.TextureComponent;
import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;

public class PlayerControlSystem extends IteratingSystem {

    private final ComponentMapper<TextureComponent> textureMapper;

    public PlayerControlSystem() {
        super(Family.all(PlayerComponent.class).get());

        textureMapper = ComponentMapper.getFor(TextureComponent.class);
    }

    @Override
    protected void processEntity(Entity entity, float deltaTime) {
        TextureComponent textureComponent = textureMapper.get(entity);

        if (Gdx.input.isKeyPressed(Input.Keys.A))
            textureComponent.region = GameScreenAtlas.instance.getScreenTexture(GameScreenAtlas.ScreenTexture.PLAYER_L1_1);
        if (Gdx.input.isKeyPressed(Input.Keys.D))
            textureComponent.region = GameScreenAtlas.instance.getScreenTexture(GameScreenAtlas.ScreenTexture.PLAYER_L1_2);
    }
}
