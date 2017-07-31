package com.alienshots.ludum.system.collision;

import com.alienshots.ludum.component.*;
import com.alienshots.ludum.system.MovementSystem;
import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;

import static com.alienshots.ludum.asset.texture.GameScreenAtlas.AtlasCoordinates;
import static com.alienshots.ludum.asset.texture.GameScreenAtlas.VerticalPosition;
import static com.alienshots.ludum.system.collision.CollisionUtils.resetPlayer;

/**
 * Checks collisions using the game's global time reference (class is tagged with MovementSystem)
 */
public class HazardCollisionSystem extends IteratingSystem implements MovementSystem {

    private final Entity player;
    private final ComponentMapper<DisplayComponent> displayMapper;
    private final ComponentMapper<PositionComponent> positionMapper;
    private final ComponentMapper<CollisionComponent> collisionMapper;
    private final ComponentMapper<SawComponent> sawMapper;
    private final ComponentMapper<DropComponent> dropMapper;

    public HazardCollisionSystem(Entity player) {
        super(Family.all(HazardComponent.class).get());

        this.player = player;
        this.displayMapper = ComponentMapper.getFor(DisplayComponent.class);
        this.positionMapper = ComponentMapper.getFor(PositionComponent.class);
        this.collisionMapper = ComponentMapper.getFor(CollisionComponent.class);
        this.sawMapper = ComponentMapper.getFor(SawComponent.class);
        this.dropMapper = ComponentMapper.getFor(DropComponent.class);
    }

    @Override
    protected void processEntity(Entity hazard, float deltaTime) {

        if (!displayMapper.get(hazard).isVisible()) return;

        CollisionComponent playerCollisionComponent = collisionMapper.get(player);
        AtlasCoordinates playerCoords = positionMapper.get(player).getCoords();
        if ((sawMapper.has(hazard) && sawCollides(hazard))
                || (dropMapper.has(hazard) && dropCollides(hazard))) {
            resetPlayer(player);
        }
        playerCollisionComponent.setPrevPosInGameTimeRef(playerCoords);
    }

    private boolean sawCollides(Entity saw) {
        AtlasCoordinates playerCoords = positionMapper.get(player).getCoords();
        int playerColumnX2 = playerCoords.getColumn() * 2;
        int sawColumn = positionMapper.get(saw).getCoords().getColumn();
        int prevSawColumn = collisionMapper.get(saw).getPrevPosInGameTimeRef().getColumn();

        if (playerCoords.getLevel() != 1 || playerCoords.getVerticalPosition() != VerticalPosition.LOW)
            return false;

        boolean collisionFromRight = playerColumnX2 == prevSawColumn && playerColumnX2 == (sawColumn + 2);
        boolean collisionFromLeft = playerColumnX2 == (sawColumn - 1) && playerColumnX2 == (prevSawColumn + 1) ||
                                    sawColumn == 1 && playerColumnX2 == (prevSawColumn + 1);
        return collisionFromLeft || collisionFromRight;
    }

    private boolean dropCollides(Entity drop) {
        AtlasCoordinates playerCoords = positionMapper.get(player).getCoords();
        AtlasCoordinates dropCoords = positionMapper.get(drop).getCoords();

        if (playerCoords.getLevel() != 2) return false;

        return playerCoords.getColumn() - 1 == dropCoords.getColumn()
                && playerCoords.getVerticalPosition().ordinal() + 1 == dropCoords.getVerticalPosition().ordinal();
    }
}
