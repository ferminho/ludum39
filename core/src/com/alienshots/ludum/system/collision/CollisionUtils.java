package com.alienshots.ludum.system.collision;

import com.alienshots.ludum.SoundManager;
import com.alienshots.ludum.asset.texture.GameScreenAtlas;
import com.alienshots.ludum.component.PlayerComponent;
import com.alienshots.ludum.component.PositionComponent;
import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.ashley.core.Entity;

public class CollisionUtils {

    private static final ComponentMapper<PositionComponent> positionMapper = ComponentMapper.getFor(PositionComponent.class);;

    public static void resetPlayer(Entity player) {
        GameScreenAtlas.AtlasCoordinates playerCoords = positionMapper.get(player).getCoords();
        SoundManager.instance.play(SoundManager.SFX_DIE);
        playerCoords.setLevel(1);
        playerCoords.setColumn(1);
        playerCoords.setVerticalPosition(GameScreenAtlas.VerticalPosition.LOW);
        positionMapper.get(player).setRegion(GameScreenAtlas.instance.getScreenTexture(PlayerComponent.class, playerCoords));
    }
}
