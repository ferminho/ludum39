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
        // first floor
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
        // second floor
        textureAtlas.addRegion(baseName + "2_1_DOWN", sourceImage, 192, 571, 25, 36);
        textureAtlas.addRegion(baseName + "2_2_DOWN", sourceImage, 275, 483, 26, 37);
        textureAtlas.addRegion(baseName + "2_3_DOWN", sourceImage, 401, 483, 26, 37);
        textureAtlas.addRegion(baseName + "2_4_DOWN", sourceImage, 527, 483, 26, 37);
        textureAtlas.addRegion(baseName + "2_5_DOWN", sourceImage, 653, 483, 26, 37);
        textureAtlas.addRegion(baseName + "2_6_DOWN", sourceImage, 779, 483, 26, 37);
        textureAtlas.addRegion(baseName + "2_7_DOWN", sourceImage, 908, 483, 26, 37);
        textureAtlas.addRegion(baseName + "2_8_DOWN", sourceImage, 1034, 483, 26, 37);
        textureAtlas.addRegion(baseName + "2_1_UP", sourceImage, 206, 418, 25, 36);
        textureAtlas.addRegion(baseName + "2_2_UP", sourceImage, 274, 427, 28, 30);
        textureAtlas.addRegion(baseName + "2_3_UP", sourceImage, 400, 426, 28, 30);
        textureAtlas.addRegion(baseName + "2_4_UP", sourceImage, 526, 432, 28, 30);
        textureAtlas.addRegion(baseName + "2_5_UP", sourceImage, 652, 431, 28, 30);
        textureAtlas.addRegion(baseName + "2_6_UP", sourceImage, 778, 426, 28, 30);
        textureAtlas.addRegion(baseName + "2_7_UP", sourceImage, 907, 427, 28, 30);
        textureAtlas.addRegion(baseName + "2_8_UP", sourceImage, 1033, 434, 28, 30);
        // third floor
        textureAtlas.addRegion(baseName + "3_1_DOWN", sourceImage, 227, 363, 26, 37);
        textureAtlas.addRegion(baseName + "3_2_DOWN", sourceImage, 351, 363, 26, 37);
        textureAtlas.addRegion(baseName + "3_3_DOWN", sourceImage, 477, 363, 26, 37);
        textureAtlas.addRegion(baseName + "3_4_DOWN", sourceImage, 603, 363, 26, 37);
        textureAtlas.addRegion(baseName + "3_5_DOWN", sourceImage, 729, 363, 26, 37);
        textureAtlas.addRegion(baseName + "3_6_DOWN", sourceImage, 855, 363, 26, 37);
        textureAtlas.addRegion(baseName + "3_7_DOWN", sourceImage, 955, 354, 25, 36);
        textureAtlas.addRegion(baseName + "3_8_DOWN", sourceImage, 1044, 363, 26, 37);
        textureAtlas.addRegion(baseName + "3_1_UP", sourceImage, 274, 427, 28, 30);
        textureAtlas.addRegion(baseName + "3_2_UP", sourceImage, 350, 307, 28, 30);
        textureAtlas.addRegion(baseName + "3_3_UP", sourceImage, 476, 309, 28, 30);
        textureAtlas.addRegion(baseName + "3_4_UP", sourceImage, 602, 312, 28, 30);
        textureAtlas.addRegion(baseName + "3_5_UP", sourceImage, 728, 309, 28, 30);
        textureAtlas.addRegion(baseName + "3_6_UP", sourceImage, 854, 306, 28, 30);
        textureAtlas.addRegion(baseName + "3_7_UP", sourceImage, 956, 298, 25, 36);
        textureAtlas.addRegion(baseName + "3_8_UP", sourceImage, 1043, 309, 28, 30);
        // fourth floor
        textureAtlas.addRegion(baseName + "4_1_DOWN", sourceImage, 266, 229, 30, 37);
        textureAtlas.addRegion(baseName + "4_2_DOWN", sourceImage, 329, 244, 26, 37);
        textureAtlas.addRegion(baseName + "4_3_DOWN", sourceImage, 455, 244, 26, 37);
        textureAtlas.addRegion(baseName + "4_4_DOWN", sourceImage, 581, 244, 26, 37);
        textureAtlas.addRegion(baseName + "4_5_DOWN", sourceImage, 707, 244, 26, 37);
        textureAtlas.addRegion(baseName + "4_6_DOWN", sourceImage, 832, 244, 26, 37);
        textureAtlas.addRegion(baseName + "4_7_DOWN", sourceImage, 956, 244, 26, 37);
        textureAtlas.addRegion(baseName + "4_2_UP", sourceImage, 328, 179, 28, 30);
        textureAtlas.addRegion(baseName + "4_3_UP", sourceImage, 454, 186, 28, 30);
        textureAtlas.addRegion(baseName + "4_4_UP", sourceImage, 580, 188, 28, 30);
        textureAtlas.addRegion(baseName + "4_5_UP", sourceImage, 706, 185, 28, 30);
        textureAtlas.addRegion(baseName + "4_6_UP", sourceImage, 831, 183, 28, 30);
        textureAtlas.addRegion(baseName + "4_7_UP", sourceImage, 956, 177, 30, 37);
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
