package com.alienshots.ludum.asset.texture;

import com.alienshots.ludum.component.SawComponent;
import com.alienshots.ludum.component.PlayerComponent;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.HashMap;
import java.util.Map;

public class GameScreenAtlas {

    public static final GameScreenAtlas instance = new GameScreenAtlas();

    private final Map<Class<?>, String> regionNameTemplateLookup = new HashMap<>();

    private Texture sourceImage;
    private TextureAtlas textureAtlas;

    public GameScreenAtlas() {
        sourceImage = new Texture("sprites.png");
        textureAtlas = new TextureAtlas();

        initNameTemplateLookup();
        initAtlasRegions();
    }

    private void initNameTemplateLookup() {
        regionNameTemplateLookup.put(PlayerComponent.class, PlayerComponent.class.getName() + "%s_%s_%s");
        regionNameTemplateLookup.put(SawComponent.class, SawComponent.class.getName() + "%s_%s_%s");
    }

    private void initAtlasRegions() {
        initPlayerRegions();
        initSawRegions();
    }

    private void initPlayerRegions() {
        String baseName = PlayerComponent.class.getName();
        textureAtlas.addRegion(baseName + "1_1_DOWN", sourceImage, 192, 571, 30, 37);
        textureAtlas.addRegion(baseName + "1_2_DOWN", sourceImage, 329, 603, 26, 37);
        textureAtlas.addRegion(baseName + "1_3_DOWN", sourceImage, 455, 603, 26, 37);
        textureAtlas.addRegion(baseName + "1_4_DOWN", sourceImage, 581, 603, 26, 37);
        textureAtlas.addRegion(baseName + "1_5_DOWN", sourceImage, 707, 603, 26, 37);
        textureAtlas.addRegion(baseName + "1_6_DOWN", sourceImage, 832, 603, 26, 37);
        textureAtlas.addRegion(baseName + "1_7_DOWN", sourceImage, 958, 603, 26, 37);
        textureAtlas.addRegion(baseName + "1_8_DOWN", sourceImage, 1050, 594, 25, 36);
        textureAtlas.addRegion(baseName + "1_2_UP", sourceImage, 328, 544, 28, 30);
        textureAtlas.addRegion(baseName + "1_3_UP", sourceImage, 454, 543, 28, 30);
        textureAtlas.addRegion(baseName + "1_4_UP", sourceImage, 580, 540, 28, 30);
        textureAtlas.addRegion(baseName + "1_5_UP", sourceImage, 706, 540, 28, 30);
        textureAtlas.addRegion(baseName + "1_6_UP", sourceImage, 831, 547, 28, 30);
        textureAtlas.addRegion(baseName + "1_7_UP", sourceImage, 957, 538, 28, 30);
        textureAtlas.addRegion(baseName + "1_8_UP", sourceImage, 1052, 538, 25, 36);
    }

    private void initSawRegions() {
        String baseName = SawComponent.class.getName();
        textureAtlas.addRegion(baseName + "1_1_DOWN", sourceImage, 205, 632, 30, 27);
        textureAtlas.addRegion(baseName + "1_2_DOWN", sourceImage, 356, 632, 30, 27);
        textureAtlas.addRegion(baseName + "1_3_DOWN", sourceImage, 482, 632, 30, 27);
        textureAtlas.addRegion(baseName + "1_4_DOWN", sourceImage, 608, 632, 30, 27);
        textureAtlas.addRegion(baseName + "1_5_DOWN", sourceImage, 734, 632, 30, 27);
        textureAtlas.addRegion(baseName + "1_6_DOWN", sourceImage, 859, 632, 30, 27);
        textureAtlas.addRegion(baseName + "1_7_DOWN", sourceImage, 985, 632, 30, 27);
    }

    public TextureRegion getScreenTexture(Class<?> tagClass, AtlasCoordinates coords) {
        String regionName = String.format(regionNameTemplateLookup.get(tagClass),
                                          coords.getLevel(), coords.getColumn(), coords.getUpDown());
        return textureAtlas.findRegion(regionName);
    }

    public void dispose() {
        sourceImage.dispose();
    }

    // Indices start at 1
    @AllArgsConstructor
    @Getter
    @Setter
    public static class AtlasCoordinates {
        private int level;
        private int column;
        private UpDown upDown;
    }

    public enum UpDown {
        UP, DOWN
    }
}
