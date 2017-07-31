package com.alienshots.ludum.component;

import com.badlogic.ashley.core.Component;
import lombok.Getter;

import static com.alienshots.ludum.asset.texture.GameScreenAtlas.AtlasCoordinates;

@Getter
public class CollisionComponent implements Component {

    private AtlasCoordinates prevPosInGameTimeRef = null;
    private AtlasCoordinates prevPosInPlayerTimeRef = null;

    public void setPrevPosInGameTimeRef(AtlasCoordinates previousPosition) {
        this.prevPosInGameTimeRef = new AtlasCoordinates(previousPosition.getLevel(),
                previousPosition.getColumn(), previousPosition.getVerticalPosition());
    }

    public void setPrevPosInPlayerTimeRef(AtlasCoordinates previousPosition) {
        this.prevPosInPlayerTimeRef = new AtlasCoordinates(previousPosition.getLevel(),
                previousPosition.getColumn(), previousPosition.getVerticalPosition());
    }
}
