package com.alienshots.ludum.system;

import com.alienshots.ludum.SoundManager;
import com.alienshots.ludum.asset.texture.GameScreenAtlas;
import com.alienshots.ludum.component.BatteryItemComponent;
import com.alienshots.ludum.component.PlayerComponent;
import com.alienshots.ludum.component.PlayerEventComponent;
import com.alienshots.ludum.component.PositionComponent;
import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;

import static com.alienshots.ludum.asset.texture.GameScreenAtlas.AtlasCoordinates;
import static com.alienshots.ludum.asset.texture.GameScreenAtlas.VerticalPosition;

/**
 * We should use proper events (if possible) instead of this approach
 */
public class PlayerWatchDogSystem extends IteratingSystem {

    private final ComponentMapper<PlayerEventComponent> playerEventMapper;
    private final ComponentMapper<PositionComponent> positionMapper;
    private final ComponentMapper<BatteryItemComponent> batteryItemMapper;

    public PlayerWatchDogSystem() {
        super(Family.all(PlayerComponent.class, PlayerEventComponent.class).get());

        playerEventMapper = ComponentMapper.getFor(PlayerEventComponent.class);
        positionMapper = ComponentMapper.getFor(PositionComponent.class);
        batteryItemMapper = ComponentMapper.getFor(BatteryItemComponent.class);
    }

    @Override
    protected void processEntity(Entity player, float deltaTime) {
        if (playerEventMapper.get(player).isUserDied()) {
            resetPlayer(player);
        }
        player.remove(PlayerEventComponent.class);
    }

    public void resetPlayer(Entity player) {
        SoundManager.instance.play(SoundManager.SFX_DIE);
        if (batteryItemMapper.get(player).isCarryingBattery()) {
            resetToLowerLevel(player);
        } else {
            resetToUpperLevel(player);
        }

        positionMapper.get(player)
                .setRegion(GameScreenAtlas.instance.getScreenTexture(PlayerComponent.class,
                        positionMapper.get(player).getCoords()));
    }

    private void resetToLowerLevel(Entity player) {
        AtlasCoordinates playerCoords = positionMapper.get(player).getCoords();
        playerCoords.setLevel(1);
        playerCoords.setColumn(1);
        playerCoords.setVerticalPosition(VerticalPosition.LOW);
    }

    private void resetToUpperLevel(Entity player) {
        AtlasCoordinates playerCoords = positionMapper.get(player).getCoords();
        playerCoords.setLevel(4);
        playerCoords.setColumn(7);
        playerCoords.setVerticalPosition(VerticalPosition.LOW);
    }

}
