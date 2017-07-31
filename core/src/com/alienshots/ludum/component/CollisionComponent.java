package com.alienshots.ludum.component;

import com.badlogic.ashley.core.Component;

import static com.alienshots.ludum.asset.texture.GameScreenAtlas.AtlasCoordinates;

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

    public AtlasCoordinates getPrevPosInGameTimeRef() {
        return prevPosInGameTimeRef;
    }

    public AtlasCoordinates getPrevPosInPlayerTimeRef() {
        return prevPosInPlayerTimeRef;
    }
}
