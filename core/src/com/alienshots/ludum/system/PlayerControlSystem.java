package com.alienshots.ludum.system;

import com.alienshots.ludum.asset.texture.GameScreenAtlas;
import com.alienshots.ludum.component.PlayerComponent;
import com.alienshots.ludum.component.PositionComponent;
import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;
import com.badlogic.gdx.Gdx;

import static com.alienshots.ludum.asset.texture.GameScreenAtlas.AtlasCoordinates;
import static com.alienshots.ludum.asset.texture.GameScreenAtlas.VerticalPosition;
import static com.badlogic.gdx.Input.Keys;

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

        if (pressedLeft() && canMoveLeft(coords)) {
            coords.setColumn(coords.getColumn() - 1);
        } else if (pressedRight() && canMoveRight(coords)) {
            coords.setColumn(coords.getColumn() + 1);
        } else if (pressedUp()) {
            tryToMoveUp(coords);
        } else if (pressedDown()) {
            tryToMoveDown(coords);
        } else if (pressedJump() && canJump(coords)) {
            jump = new Jump();
            coords.setVerticalPosition(VerticalPosition.HIGH);
        }
        positionComponent.setRegion(GameScreenAtlas.instance.getScreenTexture(PlayerComponent.class, coords));
    }

    private boolean pressedLeft() { return isKeyPressed(Keys.A) || isKeyPressed(Keys.LEFT); }
    private boolean pressedRight() { return isKeyPressed(Keys.D) || isKeyPressed(Keys.RIGHT); }
    private boolean pressedUp() { return isKeyPressed(Keys.W) || isKeyPressed(Keys.UP); }
    private boolean pressedDown() { return isKeyPressed(Keys.S) || isKeyPressed(Keys.DOWN); }
    private boolean pressedJump() { return isKeyPressed(Keys.K) || isKeyPressed(Keys.SPACE); }
    private boolean isKeyPressed(int key) { return Gdx.input.isKeyJustPressed(key); }

    private boolean canMoveLeft(AtlasCoordinates coords) {
        if (coords.getVerticalPosition() != VerticalPosition.LOW)
            return false;
        return coords.getColumn() > 1;
    }

    private boolean canMoveRight(AtlasCoordinates coords) {
        if (coords.getVerticalPosition() != VerticalPosition.LOW)
            return false;
        if (coords.getLevel() < 3)
            return coords.getColumn() < 8;
        return coords.getColumn() < 7;
    }

    private void tryToMoveUp(AtlasCoordinates coords) {
        if (coords.getLevel() == 1 && coords.getColumn() == 8) {
            moveUp(coords, 8);
        } else if (coords.getLevel() == 2 && coords.getColumn() == 1) {
            moveUp(coords, 1);
        } else if (coords.getLevel() == 3 && coords.getColumn() == 7) {
            moveUp(coords, 7);
        }
    }

    private void moveUp(AtlasCoordinates coords, int upperFloorDestinationColumn) {
        if (coords.getVerticalPosition() == VerticalPosition.LOW) {
            coords.setVerticalPosition(VerticalPosition.HIGH);
        } else {
            coords.setLevel(coords.getLevel() + 1);
            coords.setColumn(upperFloorDestinationColumn);
            coords.setVerticalPosition(VerticalPosition.LOW);
        }
    }

    private void tryToMoveDown(AtlasCoordinates coords) {
        if (coords.getVerticalPosition() == VerticalPosition.LOW) {
            if (coords.getLevel() == 2 && coords.getColumn() == 8) {
                moveAFloorDown(coords, 8);
            } else if (coords.getLevel() == 3 && coords.getColumn() == 1) {
                moveAFloorDown(coords, 1);
            } else if (coords.getLevel() == 4 && coords.getColumn() == 7) {
                moveAFloorDown(coords, 7);
            }
        } else if ((coords.getLevel() == 1 && coords.getColumn() == 8) ||
                (coords.getLevel() == 2 && coords.getColumn() == 1) ||
                (coords.getLevel() == 3 && coords.getColumn() == 7)) {
            coords.setVerticalPosition(VerticalPosition.LOW);
        }
    }

    private void moveAFloorDown(AtlasCoordinates coords, int lowerFloorDestinationColumn) {
        coords.setLevel(coords.getLevel() - 1);
        coords.setColumn(lowerFloorDestinationColumn);
        coords.setVerticalPosition(VerticalPosition.HIGH);
    }

    private boolean canJump(AtlasCoordinates coords) {
        if (coords.getVerticalPosition() != VerticalPosition.LOW)
            return false;
        if (coords.getLevel() == 1)
            return coords.getColumn() != 1 && coords.getColumn() != 8;
        if (coords.getLevel() == 2)
            return coords.getColumn() != 1;
        if (coords.getLevel() == 3)
            return coords.getColumn() != 7;
        if (coords.getLevel() == 4)
            return coords.getColumn() != 1;
        return false;
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
