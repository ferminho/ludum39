package com.alienshots.ludum;

import com.alienshots.ludum.asset.texture.GameScreenAtlas;
import com.alienshots.ludum.component.SawComponent;
import com.alienshots.ludum.component.DisplayComponent;
import com.alienshots.ludum.component.PlayerComponent;
import com.alienshots.ludum.component.PositionComponent;
import com.badlogic.ashley.core.Entity;

import static com.alienshots.ludum.asset.texture.GameScreenAtlas.*;

public class GameEntitiesFactory {

    public static final GameEntitiesFactory instance = new GameEntitiesFactory();

    public Entity createPlayer() {
        Entity player = new Entity();
        AtlasCoordinates initialCoords = new AtlasCoordinates(1, 1, VerticalPosition.LOW);

        player.add(new PlayerComponent());
        player.add(new DisplayComponent(true));
        player.add(PositionComponent.builder()
                                    .coords(initialCoords)
                                    .region(GameScreenAtlas.instance.getScreenTexture(PlayerComponent.class, initialCoords))
                                    .build());
        return player;
    }

    public Entity createSaw() {
        Entity saw = new Entity();
        AtlasCoordinates initialCoords = new AtlasCoordinates(1, 7, VerticalPosition.LOW);

        saw.add(new SawComponent());
        saw.add(new DisplayComponent(false));
        saw.add(PositionComponent.builder()
                                 .coords(initialCoords)
                                 .region(GameScreenAtlas.instance.getScreenTexture(SawComponent.class, initialCoords))
                                 .build());
        return saw;
    }
}
