package com.alienshots.ludum.system.collision;

import com.alienshots.ludum.component.*;
import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;

import static com.alienshots.ludum.asset.texture.GameScreenAtlas.AtlasCoordinates;
import static com.alienshots.ludum.system.collision.CollisionUtils.resetPlayer;

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
    private final ComponentMapper<CrateComponent> crateMapper;

    public PlayerCollisionSystem(Entity player) {
        super(Family.all(HazardComponent.class).get());

        this.player = player;
        this.positionMapper = ComponentMapper.getFor(PositionComponent.class);
        this.collisionMapper = ComponentMapper.getFor(CollisionComponent.class);
        this.displayMapper = ComponentMapper.getFor(DisplayComponent.class);
        this.sawMapper = ComponentMapper.getFor(SawComponent.class);
        this.dropMapper = ComponentMapper.getFor(DropComponent.class);
        this.crateMapper = ComponentMapper.getFor(CrateComponent.class);
    }

    @Override
    protected void processEntity(Entity hazard, float deltaTime) {

        if (!displayMapper.get(hazard).isVisible()) return;

        if ((sawMapper.has(hazard) && sawCollides(hazard))
                || (dropMapper.has(hazard) && dropCollides(hazard))
                || (crateMapper.has(hazard) && crateCollides(hazard))) {
            resetPlayer(player);
        }
    }

    private boolean sawCollides(Entity saw) {
        AtlasCoordinates playerCoords = positionMapper.get(player).getCoords();
        int playerColumnX2 = playerCoords.getColumn() * 2;
        AtlasCoordinates playerPreviousCoords = collisionMapper.get(player).getPrevPosInPlayerTimeRef();
        int prevPlayerColumnX2 = playerPreviousCoords.getColumn() * 2;
        int sawColumn = positionMapper.get(saw).getCoords().getColumn();

        if (playerCoords.getLevel() != 1 || sawColumn < 3) return false;

        if (prevPlayerColumnX2 == 2) return false; // not killing player if getting down from battery packs
        if (playerColumnX2 == prevPlayerColumnX2) return false;
        boolean crossesLeft = prevPlayerColumnX2 > sawColumn && playerColumnX2 <= sawColumn;
        boolean crossesRight = prevPlayerColumnX2 <= sawColumn && playerColumnX2 > sawColumn;
        return crossesLeft || crossesRight;
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

    private boolean crateCollides(Entity crate) {
        AtlasCoordinates playerCoords = positionMapper.get(player).getCoords();
        int playerColumnX2 = playerCoords.getColumn() * 2;
        AtlasCoordinates playerPreviousCoords = collisionMapper.get(player).getPrevPosInPlayerTimeRef();
        int prevPlayerColumnX2 = playerPreviousCoords.getColumn() * 2;
        int crateColumn = positionMapper.get(crate).getCoords().getColumn();

        if (playerCoords.getLevel() != 3) return false;

        if (prevPlayerColumnX2 == 2) return false; // not killing player if getting down from battery packs
        if (playerColumnX2 == prevPlayerColumnX2) return false;
        boolean crossesLeft = prevPlayerColumnX2 > crateColumn && playerColumnX2 <= crateColumn;
        boolean crossesRight = prevPlayerColumnX2 <= crateColumn && playerColumnX2 > crateColumn;
        return crossesLeft || crossesRight;
    }
}
