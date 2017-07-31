package com.alienshots.ludum.system.collision;

import com.alienshots.ludum.component.*;
import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;

import static com.alienshots.ludum.asset.texture.GameScreenAtlas.AtlasCoordinates;

/**
 * Checks collisions using the player's time reference
 */
public class PlayerCollisionSystem extends IteratingSystem {

    private final Entity player;
    private final ComponentMapper<PositionComponent> positionMapper;
    private final ComponentMapper<CollisionComponent> collisionMapper;
    private final ComponentMapper<DisplayComponent> displayMapper;
    private final ComponentMapper<SawComponent> sawMapper;

    public PlayerCollisionSystem(Entity player) {
        super(Family.all(HazardComponent.class).get());

        this.player = player;
        this.positionMapper = ComponentMapper.getFor(PositionComponent.class);
        this.collisionMapper = ComponentMapper.getFor(CollisionComponent.class);
        this.displayMapper = ComponentMapper.getFor(DisplayComponent.class);
        this.sawMapper = ComponentMapper.getFor(SawComponent.class);
    }

    @Override
    protected void processEntity(Entity hazard, float deltaTime) {
        AtlasCoordinates playerCoords = positionMapper.get(player).getCoords();
        if (sawMapper.has(hazard)
                && displayMapper.get(hazard).isVisible()
                && positionMapper.get(hazard).getCoords().getColumn() > 1
                && playerCoords.getLevel() == 1) {
            if (sawCollides(hazard)) {
                playerCoords.setColumn(1);
            }
        }
    }

    private boolean sawCollides(Entity saw) {
        AtlasCoordinates playerCoords = positionMapper.get(player).getCoords();
        AtlasCoordinates playerPreviousCoords = collisionMapper.get(player).getPrevPosInPlayerTimeRef();
        AtlasCoordinates sawCoords = positionMapper.get(saw).getCoords();

        return (playerCoords.getColumn() - sawCoords.getColumn())
                + (playerPreviousCoords.getColumn() - sawCoords.getColumn()) == 1;
    }
}
