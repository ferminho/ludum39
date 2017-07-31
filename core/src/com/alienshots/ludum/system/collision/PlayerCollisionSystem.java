package com.alienshots.ludum.system.collision;

import com.alienshots.ludum.component.*;
import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;

import static com.alienshots.ludum.asset.texture.GameScreenAtlas.AtlasCoordinates;
import static com.alienshots.ludum.asset.texture.GameScreenAtlas.VerticalPosition;

/**
 * Checks collisions using the player's time reference
 */
public class PlayerCollisionSystem extends IteratingSystem {

    private final Entity player;
    private final ComponentMapper<PositionComponent> positionMapper;
    private final ComponentMapper<CollisionComponent> collisionMapper;
    private final ComponentMapper<DisplayComponent> displayMapper;
    private final ComponentMapper<SawComponent> sawMapper;
    private final ComponentMapper<DropComponent> dropMapper;

    public PlayerCollisionSystem(Entity player) {
        super(Family.all(HazardComponent.class).get());

        this.player = player;
        this.positionMapper = ComponentMapper.getFor(PositionComponent.class);
        this.collisionMapper = ComponentMapper.getFor(CollisionComponent.class);
        this.displayMapper = ComponentMapper.getFor(DisplayComponent.class);
        this.sawMapper = ComponentMapper.getFor(SawComponent.class);
        this.dropMapper = ComponentMapper.getFor(DropComponent.class);
    }

    @Override
    protected void processEntity(Entity hazard, float deltaTime) {

        if (!displayMapper.get(hazard).isVisible()) return;

        AtlasCoordinates playerCoords = positionMapper.get(player).getCoords();
        if ((sawMapper.has(hazard) && sawCollides(hazard))
                ||(dropMapper.has(hazard) && dropCollides(hazard))) {
            resetPlayer();
        }
    }

    private boolean sawCollides(Entity saw) {
        AtlasCoordinates playerCoords = positionMapper.get(player).getCoords();
        AtlasCoordinates playerPreviousCoords = collisionMapper.get(player).getPrevPosInPlayerTimeRef();
        AtlasCoordinates sawCoords = positionMapper.get(saw).getCoords();

        if (playerCoords.getLevel() != 1 || sawCoords.getColumn() < 2) return false;

        return (playerCoords.getColumn() - sawCoords.getColumn())
                + (playerPreviousCoords.getColumn() - sawCoords.getColumn()) == 1;
    }

    private boolean dropCollides(Entity drop) {
        AtlasCoordinates playerCoords = positionMapper.get(player).getCoords();
        AtlasCoordinates playerPreviousCoords = collisionMapper.get(player).getPrevPosInPlayerTimeRef();
        AtlasCoordinates dropCoords = positionMapper.get(drop).getCoords();

        if (playerCoords.getLevel() != 2) return false;

        return (playerCoords.getColumn() - 1 - dropCoords.getColumn())
                + (playerPreviousCoords.getColumn() - 1 - dropCoords.getColumn()) == -1
                && playerCoords.getVerticalPosition().ordinal() + 1 == dropCoords.getVerticalPosition().ordinal();
    }

    private void resetPlayer() {
        AtlasCoordinates playerCoords = positionMapper.get(player).getCoords();
        playerCoords.setLevel(1);
        playerCoords.setColumn(1);
        playerCoords.setVerticalPosition(VerticalPosition.LOW);
    }
}
