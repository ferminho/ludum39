package com.alienshots.ludum.system.collision;

import com.alienshots.ludum.asset.texture.GameScreenAtlas;
import com.alienshots.ludum.component.*;
import com.alienshots.ludum.system.MovementSystem;
import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;

import static com.alienshots.ludum.asset.texture.GameScreenAtlas.AtlasCoordinates;

/**
 * Checks collisions using the game's global time reference (class is tagged with MovementSystem)
 */
public class HazardsCollisionSystem extends IteratingSystem implements MovementSystem {

    private final Entity player;
    private final ComponentMapper<PositionComponent> positionMapper;
    private final ComponentMapper<CollisionComponent> collisionMapper;
    private final ComponentMapper<SawComponent> sawMapper;

    public HazardsCollisionSystem(Entity player) {
        super(Family.all(HazardComponent.class).get());

        this.player = player;
        this.positionMapper = ComponentMapper.getFor(PositionComponent.class);
        this.collisionMapper = ComponentMapper.getFor(CollisionComponent.class);
        this.sawMapper = ComponentMapper.getFor(SawComponent.class);
    }

    @Override
    protected void processEntity(Entity hazard, float deltaTime) {
        CollisionComponent playerCollisionComponent = collisionMapper.get(player);
        AtlasCoordinates playerCoords = positionMapper.get(player).getCoords();
        if (sawMapper.has(hazard) && playerCoords.getLevel() == 1) {
            if (sawCollides(hazard)) {
                playerCoords.setColumn(1);
            }
        }
        playerCollisionComponent.setPrevPosInGameTimeRef(playerCoords);
    }

    private boolean sawCollides(Entity saw) {
        AtlasCoordinates playerCoords = positionMapper.get(player).getCoords();
        int playerColumnX2 = playerCoords.getColumn() * 2;
        int sawColumn = positionMapper.get(saw).getCoords().getColumn();
        int prevSawColumn = collisionMapper.get(saw).getPrevPosInGameTimeRef().getColumn();

        if (playerCoords.getVerticalPosition() != GameScreenAtlas.VerticalPosition.LOW)
            return false;

        boolean collisionFromRight = playerColumnX2 == prevSawColumn && playerColumnX2 == (sawColumn + 2);
        boolean collisionFromLeft = playerColumnX2 == (sawColumn - 1) && playerColumnX2 == (prevSawColumn + 1) ||
                                    sawColumn == 1 && playerColumnX2 == (prevSawColumn + 1);
        return collisionFromLeft || collisionFromRight;
    }
}
