package com.alienshots.ludum.system;

import static com.alienshots.ludum.Time.BlinkingTimer;
import com.alienshots.ludum.asset.texture.GameScreenAtlas;
import com.alienshots.ludum.component.*;
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

    private static final int BLINK_INTERVAL_MS = 300;

    private final ComponentMapper<PlayerEventComponent> playerEventMapper;
    private final ComponentMapper<PositionComponent> positionMapper;
    private final ComponentMapper<DisplayComponent> displayMapper;
    private final ComponentMapper<BatteryItemComponent> batteryItemMapper;

    private BlinkingTimer blinkingTimer = null;

    public PlayerWatchDogSystem() {
        super(Family.all(PlayerComponent.class, PlayerEventComponent.class, DisplayComponent.class).get());

        playerEventMapper = ComponentMapper.getFor(PlayerEventComponent.class);
        positionMapper = ComponentMapper.getFor(PositionComponent.class);
        displayMapper = ComponentMapper.getFor(DisplayComponent.class);
        batteryItemMapper = ComponentMapper.getFor(BatteryItemComponent.class);
    }

    @Override
    protected void processEntity(Entity player, float deltaTime) {
        if (playerEventMapper.get(player).isUserDied()) {
            killTheFool(player);
        }
    }

    private void killTheFool(Entity player) {
        if (blinkingTimer != null && blinkingTimer.isFinished()) {
            displayMapper.get(player).setVisible(true);
            blinkingTimer = null;
            resetPlayer(player);
            player.remove(PlayerEventComponent.class);
        } else {
            if (blinkingTimer == null) {
                blinkingTimer = new BlinkingTimer(BLINK_INTERVAL_MS, 6, false);
            }
            blinkingTimer.update();
            displayMapper.get(player).setVisible(blinkingTimer.isBlinkState());
        }
    }

    private void resetPlayer(Entity player) {
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
