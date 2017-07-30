package com.alienshots.ludum.system;

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

public class PlayerControlSystem extends IteratingSystem {

    private final ComponentMapper<PositionComponent> positionMapper;

    public PlayerControlSystem() {
        super(Family.all(PlayerComponent.class).get());

        positionMapper = ComponentMapper.getFor(PositionComponent.class);
    }

    @Override
    protected void processEntity(Entity player, float deltaTime) {
        PositionComponent positionComponent = positionMapper.get(player);
        AtlasCoordinates coords = positionComponent.getCoords();

        if (Gdx.input.isKeyJustPressed(Input.Keys.A) && canMoveLeft(coords)) {
            coords.setColumn(coords.getColumn() - 1);
        } else if (Gdx.input.isKeyJustPressed(Input.Keys.D) && canMoveRight(coords)) {
            coords.setColumn(coords.getColumn() + 1);
        }
        positionComponent.setRegion(GameScreenAtlas.instance.getScreenTexture(PlayerComponent.class, coords));
    }

    private boolean canMoveLeft(AtlasCoordinates coords) {
        return coords.getColumn() > 0;
    }

    private boolean canMoveRight(AtlasCoordinates coords) {
        return (coords.getColumn() < 7 && coords.getLevel() < 4) || (coords.getColumn() < 6);
    }
}
