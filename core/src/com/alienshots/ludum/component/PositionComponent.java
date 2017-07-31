package com.alienshots.ludum.component;

import com.badlogic.ashley.core.Component;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

import static com.alienshots.ludum.asset.texture.GameScreenAtlas.AtlasCoordinates;

public class PositionComponent implements Component {
    private AtlasCoordinates coords;
    private TextureRegion region;

    public PositionComponent(AtlasCoordinates coords, TextureRegion region) {
        this.coords = coords;
        this.region = region;
    }

    public AtlasCoordinates getCoords() {
        return coords;
    }

    public void setCoords(AtlasCoordinates coords) {
        this.coords = coords;
    }

    public TextureRegion getRegion() {
        return region;
    }

    public void setRegion(TextureRegion region) {
        this.region = region;
    }
}
