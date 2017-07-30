package com.alienshots.ludum.system;

import com.alienshots.ludum.Time;
import com.alienshots.ludum.asset.texture.GameScreenAtlas;
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

    private final ComponentMapper<PositionComponent> positionMapper;
    private Jump jump;

    public PlayerControlSystem() {
        super(Family.all(PlayerComponent.class).get());

        positionMapper = ComponentMapper.getFor(PositionComponent.class);
        jump = new Jump();
    }

    @Override
    protected void processEntity(Entity player, float deltaTime) {
        PositionComponent positionComponent = positionMapper.get(player);
        AtlasCoordinates coords = positionComponent.getCoords();

        if (jump != Jump.NOT_JUMPING && jump.jumpIsOver()) {
            coords.setVerticalPosition(VerticalPosition.LOW);
            jump = Jump.NOT_JUMPING;
        }

        if (Gdx.input.isKeyJustPressed(Input.Keys.A) && canMoveLeft(coords)) {
            coords.setColumn(coords.getColumn() - 1);
        } else if (Gdx.input.isKeyJustPressed(Input.Keys.D) && canMoveRight(coords)) {
            coords.setColumn(coords.getColumn() + 1);
        } else if (Gdx.input.isKeyJustPressed(Input.Keys.W) && canJump(coords)) {
            jump = new Jump();
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

    private static class Jump {
        private static final int JUMP_DURATION_IN_MS = 1000;
        private static final Jump NOT_JUMPING = new Jump();

        int jumpElapsedInMs = 0;

        boolean jumpIsOver() {
            jumpElapsedInMs += Gdx.graphics.getDeltaTime() * 1000;

            if (jumpElapsedInMs >= JUMP_DURATION_IN_MS) {
                jumpElapsedInMs -= jumpElapsedInMs;
                return true;
            } else return false;
        }
    }
}
