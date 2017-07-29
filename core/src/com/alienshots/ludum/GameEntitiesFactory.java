package com.alienshots.ludum;

import com.alienshots.ludum.asset.texture.GameScreenAtlas;
import com.alienshots.ludum.component.PlayerComponent;
import com.alienshots.ludum.component.TextureComponent;
import com.badlogic.ashley.core.Entity;

import static com.alienshots.ludum.asset.texture.GameScreenAtlas.ScreenTexture;

public class GameEntitiesFactory {

    public static final GameEntitiesFactory instance = new GameEntitiesFactory();

    public Entity createPlayer() {
        Entity player = new Entity();
        TextureComponent textureComponent = new TextureComponent();
        textureComponent.region = GameScreenAtlas.instance.getScreenTexture(ScreenTexture.PLAYER_L1_1);

        player.add(new PlayerComponent());
        player.add(textureComponent);
        return player;
    }
}
