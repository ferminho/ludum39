package com.alienshots.ludum.component;

import com.badlogic.ashley.core.Component;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import lombok.*;

import static com.alienshots.ludum.asset.texture.GameScreenAtlas.AtlasCoordinates;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PositionComponent implements Component {
    private AtlasCoordinates coords;
    private TextureRegion region;
}
