package com.alienshots.ludum.system;

import com.alienshots.ludum.asset.texture.GameScreenAtlas;
import com.alienshots.ludum.component.CollisionComponent;
import com.alienshots.ludum.component.PlayerComponent;
import com.alienshots.ludum.component.PositionComponent;
import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;

import static com.alienshots.ludum.asset.texture.GameScreenAtlas.AtlasCoordinates;
import static com.alienshots.ludum.asset.texture.GameScreenAtlas.VerticalPosition;

public class PlayerControlSystem extends IteratingSystem {

    private final ComponentMapper<PlayerComponent> playerMapper;
    private final ComponentMapper<PositionComponent> positionMapper;
    private final ComponentMapper<CollisionComponent> collisionMapper;

    public PlayerControlSystem() {
        super(Family.all(PlayerComponent.class).get());

        playerMapper = ComponentMapper.getFor(PlayerComponent.class);
        positionMapper = ComponentMapper.getFor(PositionComponent.class);
        collisionMapper = ComponentMapper.getFor(CollisionComponent.class);
    }

    @Override
    protected void processEntity(Entity player, float deltaTime) {
        PlayerComponent playerComponent = playerMapper.get(player);
        PositionComponent positionComponent = positionMapper.get(player);
        AtlasCoordinates coords = positionComponent.getCoords();

        collisionMapper.get(player).setPrevPosInPlayerTimeRef(coords);

        if (playerComponent.isJumping() && playerComponent.jumpIsOver()) {
            coords.setVerticalPosition(VerticalPosition.LOW);
            playerComponent.stopJump();
        }

        if (Gdx.input.isKeyJustPressed(Input.Keys.A) && canMoveLeft(coords)) {
            coords.setColumn(coords.getColumn() - 1);
        } else if (Gdx.input.isKeyJustPressed(Input.Keys.D) && canMoveRight(coords)) {
            coords.setColumn(coords.getColumn() + 1);
        } else if (Gdx.input.isKeyJustPressed(Input.Keys.W) && canJump(coords)) {
            playerComponent.startJump();
            coords.setVerticalPosition(VerticalPosition.HIGH);
        }
        positionComponent.setRegion(GameScreenAtlas.instance.getScreenTexture(PlayerComponent.class, coords));
    }

    private boolean canMoveLeft(AtlasCoordinates coords) {
        return coords.getColumn() > 1 && coords.getVerticalPosition() == VerticalPosition.LOW;
    }

    private boolean canMoveRight(AtlasCoordinates coords) {
        return ((coords.getColumn() < 8 && coords.getLevel() < 4) || (coords.getColumn() < 6))
                && coords.getVerticalPosition() == VerticalPosition.LOW;
    }

    private boolean canJump(AtlasCoordinates coords) {
        return coords.getColumn() > 1 && coords.getColumn() < 8 && coords.getVerticalPosition() == VerticalPosition.LOW;
    }


}
