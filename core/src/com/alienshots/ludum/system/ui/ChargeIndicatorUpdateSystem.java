package com.alienshots.ludum.system.ui;

import com.alienshots.ludum.asset.texture.GameScreenAtlas;
import com.alienshots.ludum.component.*;
import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;

import static com.alienshots.ludum.asset.texture.GameScreenAtlas.AtlasCoordinates;

public class ChargeIndicatorUpdateSystem extends IteratingSystem {

    private final ComponentMapper<WorldChargeComponent> chargeMapper;
    private final ComponentMapper<PositionComponent> positionMapper;
    private final ComponentMapper<DisplayComponent> displayMapper;

    public ChargeIndicatorUpdateSystem() {
        super(Family.all(ChargeIndicatorComponent.class).get());
        chargeMapper = ComponentMapper.getFor(WorldChargeComponent.class);
        positionMapper = ComponentMapper.getFor(PositionComponent.class);
        displayMapper = ComponentMapper.getFor(DisplayComponent.class);
    }

    @Override
    protected void processEntity(Entity world, float deltaTime) {
        DisplayComponent displayComponent = displayMapper.get(world);
        WorldChargeComponent chargeComponent = chargeMapper.get(world);
        PositionComponent positionComponent = positionMapper.get(world);
        AtlasCoordinates coords = positionComponent.getCoords();

        int column = 10;
        boolean visible = true;
        if (chargeComponent.getChargeLevel() > 0f) {
            int chargeLevel = (int) Math.ceil(chargeComponent.getChargeLevel());
            column = (chargeLevel + 9) / 10;
        } else {
            visible = false;
        }

        positionComponent.setRegion(GameScreenAtlas.instance.getScreenTexture(ChargeIndicatorComponent.class, coords));
        displayComponent.setVisible(visible);
        coords.setColumn(column);
    }
}
