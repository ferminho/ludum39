package com.alienshots.ludum.system.collision;

import com.alienshots.ludum.asset.texture.GameScreenAtlas;
import com.alienshots.ludum.component.BatteryItemComponent;
import com.alienshots.ludum.component.PlayerComponent;
import com.alienshots.ludum.component.PositionComponent;
import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.ashley.core.Entity;

public class CollisionUtils {

    private static final ComponentMapper<PositionComponent> positionMapper = ComponentMapper.getFor(PositionComponent.class);;
    private static final ComponentMapper<BatteryItemComponent> batteryItemMapper = ComponentMapper.getFor(BatteryItemComponent.class);;

    public static void resetPlayer(Entity player) {
        if (batteryItemMapper.get(player).isCarryingBattery()) {
            resetToLowerLevel(player);
        } else {
            resetToUpperLevel(player);
        }

        positionMapper.get(player)
                      .setRegion(GameScreenAtlas.instance.getScreenTexture(PlayerComponent.class,
                                                                           positionMapper.get(player).getCoords()));
    }

    private static void resetToLowerLevel(Entity player) {
        GameScreenAtlas.AtlasCoordinates playerCoords = positionMapper.get(player).getCoords();
        playerCoords.setLevel(1);
        playerCoords.setColumn(1);
        playerCoords.setVerticalPosition(GameScreenAtlas.VerticalPosition.LOW);
    }

    private static void resetToUpperLevel(Entity player) {
        GameScreenAtlas.AtlasCoordinates playerCoords = positionMapper.get(player).getCoords();
        playerCoords.setLevel(4);
        playerCoords.setColumn(7);
        playerCoords.setVerticalPosition(GameScreenAtlas.VerticalPosition.LOW);
    }
}
