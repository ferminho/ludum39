package com.alienshots.ludum.component;

import com.badlogic.ashley.core.Component;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import static com.alienshots.ludum.asset.texture.GameScreenAtlas.AtlasCoordinates;

@Builder
@Getter
@Setter
public class PositionComponent implements Component {
    private AtlasCoordinates coords;
    private TextureRegion region;
}
