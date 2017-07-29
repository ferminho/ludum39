package com.alienshots.ludum;

import com.alienshots.ludum.component.TextureComponent;
import com.badlogic.ashley.core.Entity;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;

public class World {

    private final TextureAtlas textureAtlas;

    public World(TextureAtlas textureAtlas) {
        this.textureAtlas = textureAtlas;
    }

    public Entity createPlayer() {
        Entity player = new Entity();
        TextureComponent texture = new TextureComponent();
        texture.region = textureAtlas.findRegion("PlayerStart");

        player.add(texture);
        return player;
    }
}
