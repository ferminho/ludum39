package com.alienshots.ludum.asset.texture;

import com.alienshots.ludum.component.*;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

import java.util.HashMap;
import java.util.Map;

public class GameScreenAtlas {

    public static final GameScreenAtlas instance = new GameScreenAtlas();

    private final Map<Class<?>, String> regionNameTemplateLookup = new HashMap<>();

    private Texture spritesLibraryTexture;
    private TextureAtlas textureAtlas;

    public GameScreenAtlas() {
        spritesLibraryTexture = new Texture("sprites.png");
        textureAtlas = new TextureAtlas();
        initAtlasRegions();
    }

    private void initAtlasRegions() {
        initPlayerRegions();
        initSawRegions();
        initDropRegions();
        initCrateRegions();
        initSparkRegions();
        initLeverRegions();
        initDecoSparkRegions();
        initFlyingBatteryRegions();
        initGeneratorRegions();
        initBatteryItemIndicatorRegions();
        initChargeIndicatorRegions();
    }

    private void initPlayerRegions() {
        String baseName = PlayerComponent.class.getName();
        // first floor
        textureAtlas.addRegion(baseName + "1_1_LOW", getSpritesLibraryTexture(), 192, 571, 30, 37);
        textureAtlas.addRegion(baseName + "1_2_LOW", getSpritesLibraryTexture(), 329, 603, 26, 37);
        textureAtlas.addRegion(baseName + "1_3_LOW", getSpritesLibraryTexture(), 455, 603, 26, 37);
        textureAtlas.addRegion(baseName + "1_4_LOW", getSpritesLibraryTexture(), 581, 603, 26, 37);
        textureAtlas.addRegion(baseName + "1_5_LOW", getSpritesLibraryTexture(), 707, 603, 26, 37);
        textureAtlas.addRegion(baseName + "1_6_LOW", getSpritesLibraryTexture(), 832, 603, 26, 37);
        textureAtlas.addRegion(baseName + "1_7_LOW", getSpritesLibraryTexture(), 958, 603, 26, 37);
        textureAtlas.addRegion(baseName + "1_8_LOW", getSpritesLibraryTexture(), 1050, 594, 25, 36);
        textureAtlas.addRegion(baseName + "1_2_HIGH", getSpritesLibraryTexture(), 328, 544, 28, 30);
        textureAtlas.addRegion(baseName + "1_3_HIGH", getSpritesLibraryTexture(), 454, 543, 28, 30);
        textureAtlas.addRegion(baseName + "1_4_HIGH", getSpritesLibraryTexture(), 580, 540, 28, 30);
        textureAtlas.addRegion(baseName + "1_5_HIGH", getSpritesLibraryTexture(), 706, 540, 28, 30);
        textureAtlas.addRegion(baseName + "1_6_HIGH", getSpritesLibraryTexture(), 831, 547, 28, 30);
        textureAtlas.addRegion(baseName + "1_7_HIGH", getSpritesLibraryTexture(), 957, 538, 28, 30);
        textureAtlas.addRegion(baseName + "1_8_HIGH", getSpritesLibraryTexture(), 1052, 538, 25, 36);
        // second floor
        textureAtlas.addRegion(baseName + "2_1_LOW", getSpritesLibraryTexture(), 208, 474, 25, 36);
        textureAtlas.addRegion(baseName + "2_2_LOW", getSpritesLibraryTexture(), 275, 483, 26, 37);
        textureAtlas.addRegion(baseName + "2_3_LOW", getSpritesLibraryTexture(), 401, 483, 26, 37);
        textureAtlas.addRegion(baseName + "2_4_LOW", getSpritesLibraryTexture(), 527, 483, 26, 37);
        textureAtlas.addRegion(baseName + "2_5_LOW", getSpritesLibraryTexture(), 653, 483, 26, 37);
        textureAtlas.addRegion(baseName + "2_6_LOW", getSpritesLibraryTexture(), 779, 483, 26, 37);
        textureAtlas.addRegion(baseName + "2_7_LOW", getSpritesLibraryTexture(), 908, 483, 26, 37);
        textureAtlas.addRegion(baseName + "2_8_LOW", getSpritesLibraryTexture(), 1034, 483, 26, 37);
        textureAtlas.addRegion(baseName + "2_1_HIGH", getSpritesLibraryTexture(), 206, 418, 25, 36);
        textureAtlas.addRegion(baseName + "2_2_HIGH", getSpritesLibraryTexture(), 274, 427, 28, 30);
        textureAtlas.addRegion(baseName + "2_3_HIGH", getSpritesLibraryTexture(), 400, 426, 28, 30);
        textureAtlas.addRegion(baseName + "2_4_HIGH", getSpritesLibraryTexture(), 526, 432, 28, 30);
        textureAtlas.addRegion(baseName + "2_5_HIGH", getSpritesLibraryTexture(), 652, 431, 28, 30);
        textureAtlas.addRegion(baseName + "2_6_HIGH", getSpritesLibraryTexture(), 778, 426, 28, 30);
        textureAtlas.addRegion(baseName + "2_7_HIGH", getSpritesLibraryTexture(), 907, 427, 28, 30);
        textureAtlas.addRegion(baseName + "2_8_HIGH", getSpritesLibraryTexture(), 1033, 434, 28, 30);
        // third floor
        textureAtlas.addRegion(baseName + "3_1_LOW", getSpritesLibraryTexture(), 227, 363, 26, 37);
        textureAtlas.addRegion(baseName + "3_2_LOW", getSpritesLibraryTexture(), 351, 363, 26, 37);
        textureAtlas.addRegion(baseName + "3_3_LOW", getSpritesLibraryTexture(), 477, 363, 26, 37);
        textureAtlas.addRegion(baseName + "3_4_LOW", getSpritesLibraryTexture(), 603, 363, 26, 37);
        textureAtlas.addRegion(baseName + "3_5_LOW", getSpritesLibraryTexture(), 729, 363, 26, 37);
        textureAtlas.addRegion(baseName + "3_6_LOW", getSpritesLibraryTexture(), 848, 363, 26, 37);
        textureAtlas.addRegion(baseName + "3_7_LOW", getSpritesLibraryTexture(), 955, 354, 25, 36);
        textureAtlas.addRegion(baseName + "3_1_HIGH", getSpritesLibraryTexture(), 226, 314, 28, 30);
        textureAtlas.addRegion(baseName + "3_2_HIGH", getSpritesLibraryTexture(), 350, 307, 28, 30);
        textureAtlas.addRegion(baseName + "3_3_HIGH", getSpritesLibraryTexture(), 476, 309, 28, 30);
        textureAtlas.addRegion(baseName + "3_4_HIGH", getSpritesLibraryTexture(), 602, 312, 28, 30);
        textureAtlas.addRegion(baseName + "3_5_HIGH", getSpritesLibraryTexture(), 728, 309, 28, 30);
        textureAtlas.addRegion(baseName + "3_6_HIGH", getSpritesLibraryTexture(), 847, 306, 28, 30);
        textureAtlas.addRegion(baseName + "3_7_HIGH", getSpritesLibraryTexture(), 956, 298, 25, 36);
        // fourth floor
        textureAtlas.addRegion(baseName + "4_1_LOW", getSpritesLibraryTexture(), 266, 229, 30, 37);
        textureAtlas.addRegion(baseName + "4_2_LOW", getSpritesLibraryTexture(), 329, 244, 26, 37);
        textureAtlas.addRegion(baseName + "4_3_LOW", getSpritesLibraryTexture(), 455, 244, 26, 37);
        textureAtlas.addRegion(baseName + "4_4_LOW", getSpritesLibraryTexture(), 581, 244, 26, 37);
        textureAtlas.addRegion(baseName + "4_5_LOW", getSpritesLibraryTexture(), 707, 244, 26, 37);
        textureAtlas.addRegion(baseName + "4_6_LOW", getSpritesLibraryTexture(), 832, 244, 26, 37);
        textureAtlas.addRegion(baseName + "4_7_LOW", getSpritesLibraryTexture(), 956, 244, 26, 37);
        textureAtlas.addRegion(baseName + "4_2_HIGH", getSpritesLibraryTexture(), 328, 179, 28, 30);
        textureAtlas.addRegion(baseName + "4_3_HIGH", getSpritesLibraryTexture(), 454, 186, 28, 30);
        textureAtlas.addRegion(baseName + "4_4_HIGH", getSpritesLibraryTexture(), 580, 188, 28, 30);
        textureAtlas.addRegion(baseName + "4_5_HIGH", getSpritesLibraryTexture(), 706, 185, 28, 30);
        textureAtlas.addRegion(baseName + "4_6_HIGH", getSpritesLibraryTexture(), 831, 183, 28, 30);
        textureAtlas.addRegion(baseName + "4_7_HIGH", getSpritesLibraryTexture(), 956, 177, 30, 37);
    }

    private void initSawRegions() {
        String baseName = SawComponent.class.getName();
        // Facing right
        textureAtlas.addRegion(baseName + "1_1_LOW", getSpritesLibraryTexture(), 187, 632, 30, 27);
        textureAtlas.addRegion(baseName + "1_3_LOW", getSpritesLibraryTexture(), 298, 632, 30, 27);
        textureAtlas.addRegion(baseName + "1_5_LOW", getSpritesLibraryTexture(), 424, 632, 30, 27);
        textureAtlas.addRegion(baseName + "1_7_LOW", getSpritesLibraryTexture(), 550, 632, 30, 27);
        textureAtlas.addRegion(baseName + "1_9_LOW", getSpritesLibraryTexture(), 676, 632, 30, 27);
        textureAtlas.addRegion(baseName + "1_11_LOW", getSpritesLibraryTexture(), 802, 632, 30, 27);
        textureAtlas.addRegion(baseName + "1_13_LOW", getSpritesLibraryTexture(), 928, 632, 30, 27);
        // Facing left
        textureAtlas.addRegion(baseName + "1_2_LOW", getSpritesLibraryTexture(), 219, 632, 30, 27);
        textureAtlas.addRegion(baseName + "1_4_LOW", getSpritesLibraryTexture(), 356, 632, 30, 27);
        textureAtlas.addRegion(baseName + "1_6_LOW", getSpritesLibraryTexture(), 482, 632, 30, 27);
        textureAtlas.addRegion(baseName + "1_8_LOW", getSpritesLibraryTexture(), 608, 632, 30, 27);
        textureAtlas.addRegion(baseName + "1_10_LOW", getSpritesLibraryTexture(), 734, 632, 30, 27);
        textureAtlas.addRegion(baseName + "1_12_LOW", getSpritesLibraryTexture(), 859, 632, 30, 27);
        textureAtlas.addRegion(baseName + "1_14_LOW", getSpritesLibraryTexture(), 985, 632, 30, 27);
    }

    private void initDropRegions() {
        String baseName = DropComponent.class.getName();
        textureAtlas.addRegion(baseName + "2_1_HIGH", getSpritesLibraryTexture(), 260, 434, 13, 23);
        textureAtlas.addRegion(baseName + "2_1_MEDIUM", getSpritesLibraryTexture(), 260, 483, 13, 23);
        textureAtlas.addRegion(baseName + "2_1_LOW", getSpritesLibraryTexture(), 258, 511, 16, 10); // splash
        textureAtlas.addRegion(baseName + "2_2_HIGH", getSpritesLibraryTexture(), 386, 434, 13, 23);
        textureAtlas.addRegion(baseName + "2_2_MEDIUM", getSpritesLibraryTexture(), 386, 483, 13, 23);
        textureAtlas.addRegion(baseName + "2_2_LOW", getSpritesLibraryTexture(), 384, 511, 16, 10); // splash
        textureAtlas.addRegion(baseName + "2_3_HIGH", getSpritesLibraryTexture(), 512, 434, 13, 23);
        textureAtlas.addRegion(baseName + "2_3_MEDIUM", getSpritesLibraryTexture(), 512, 483, 13, 23);
        textureAtlas.addRegion(baseName + "2_3_LOW", getSpritesLibraryTexture(), 510, 511, 16, 10); // splash
        textureAtlas.addRegion(baseName + "2_4_HIGH", getSpritesLibraryTexture(), 638, 434, 13, 23);
        textureAtlas.addRegion(baseName + "2_4_MEDIUM", getSpritesLibraryTexture(), 638, 483, 13, 23);
        textureAtlas.addRegion(baseName + "2_4_LOW", getSpritesLibraryTexture(), 636, 511, 16, 10); // splash
        textureAtlas.addRegion(baseName + "2_5_HIGH", getSpritesLibraryTexture(), 764, 434, 13, 23);
        textureAtlas.addRegion(baseName + "2_5_MEDIUM", getSpritesLibraryTexture(), 764, 483, 13, 23);
        textureAtlas.addRegion(baseName + "2_5_LOW", getSpritesLibraryTexture(), 762, 511, 16, 10); // splash
        textureAtlas.addRegion(baseName + "2_6_HIGH", getSpritesLibraryTexture(), 893, 434, 13, 23);
        textureAtlas.addRegion(baseName + "2_6_MEDIUM", getSpritesLibraryTexture(), 893, 483, 13, 23);
        textureAtlas.addRegion(baseName + "2_6_LOW", getSpritesLibraryTexture(), 891, 511, 16, 10); // splash
    }

    private void initCrateRegions() {
        String baseName = CrateComponent.class.getName();
        // Facing right
        textureAtlas.addRegion(baseName + "3_1_LOW", getSpritesLibraryTexture(), 186, 298, 39, 102);
        textureAtlas.addRegion(baseName + "3_3_LOW", getSpritesLibraryTexture(), 310, 298, 39, 102);
        textureAtlas.addRegion(baseName + "3_5_LOW", getSpritesLibraryTexture(), 436, 298, 39, 102);
        textureAtlas.addRegion(baseName + "3_7_LOW", getSpritesLibraryTexture(), 562, 298, 39, 102);
        textureAtlas.addRegion(baseName + "3_9_LOW", getSpritesLibraryTexture(), 688, 298, 39, 102);
        textureAtlas.addRegion(baseName + "3_11_LOW", getSpritesLibraryTexture(), 807, 298, 39, 102);
        textureAtlas.addRegion(baseName + "3_13_LOW", getSpritesLibraryTexture(), 915, 298, 39, 102);
        textureAtlas.addRegion(baseName + "3_15_LOW", getSpritesLibraryTexture(), 1044, 298, 39, 102);
        // Facing left
        textureAtlas.addRegion(baseName + "3_2_LOW", getSpritesLibraryTexture(), 254, 298, 39, 102);
        textureAtlas.addRegion(baseName + "3_4_LOW", getSpritesLibraryTexture(), 379, 298, 39, 102);
        textureAtlas.addRegion(baseName + "3_6_LOW", getSpritesLibraryTexture(), 505, 298, 39, 102);
        textureAtlas.addRegion(baseName + "3_8_LOW", getSpritesLibraryTexture(), 631, 298, 39, 102);
        textureAtlas.addRegion(baseName + "3_10_LOW", getSpritesLibraryTexture(), 757, 298, 39, 102);
        textureAtlas.addRegion(baseName + "3_12_LOW", getSpritesLibraryTexture(), 876, 298, 39, 102);
        textureAtlas.addRegion(baseName + "3_14_LOW", getSpritesLibraryTexture(), 982, 298, 39, 102);
        textureAtlas.addRegion(baseName + "3_16_LOW", getSpritesLibraryTexture(), 1044, 298, 39, 102);
    }

    private void initSparkRegions() {
        String baseName = SparkComponent.class.getName();
        textureAtlas.addRegion(baseName + "4_1_HIGH", getSpritesLibraryTexture(), 379, 95, 34, 42); // going away
        textureAtlas.addRegion(baseName + "4_2_HIGH", getSpritesLibraryTexture(), 420, 115, 54, 42); // about to fall
        textureAtlas.addRegion(baseName + "4_2_MEDIUM", getSpritesLibraryTexture(), 428, 158, 26, 114); // discharge
        textureAtlas.addRegion(baseName + "4_2_LOW", getSpritesLibraryTexture(), 414, 274, 40, 23); // on floor
        textureAtlas.addRegion(baseName + "4_3_HIGH", getSpritesLibraryTexture(), 480, 128, 63, 13);
        textureAtlas.addRegion(baseName + "4_4_HIGH", getSpritesLibraryTexture(), 546, 115, 54, 42); // about to fall
        textureAtlas.addRegion(baseName + "4_4_MEDIUM", getSpritesLibraryTexture(), 554, 158, 26, 114); // discharge
        textureAtlas.addRegion(baseName + "4_4_LOW", getSpritesLibraryTexture(), 540, 274, 40, 23); // on floor
        textureAtlas.addRegion(baseName + "4_5_HIGH", getSpritesLibraryTexture(), 604, 130, 61, 13);
        textureAtlas.addRegion(baseName + "4_6_HIGH", getSpritesLibraryTexture(), 672, 115, 54, 42); // about to fall
        textureAtlas.addRegion(baseName + "4_6_MEDIUM", getSpritesLibraryTexture(), 680, 158, 26, 114); // discharge
        textureAtlas.addRegion(baseName + "4_6_LOW", getSpritesLibraryTexture(), 666, 274, 40, 23); // on floor
        textureAtlas.addRegion(baseName + "4_7_HIGH", getSpritesLibraryTexture(), 729, 127, 62, 16);
        textureAtlas.addRegion(baseName + "4_8_HIGH", getSpritesLibraryTexture(), 797, 115, 54, 42); // about to fall
        textureAtlas.addRegion(baseName + "4_8_MEDIUM", getSpritesLibraryTexture(), 805, 158, 26, 114); // discharge
        textureAtlas.addRegion(baseName + "4_8_LOW", getSpritesLibraryTexture(), 791, 274, 40, 23); // on floor
        textureAtlas.addRegion(baseName + "4_9_HIGH", getSpritesLibraryTexture(), 856, 130, 63, 13);
        textureAtlas.addRegion(baseName + "4_10_HIGH", getSpritesLibraryTexture(), 938, 127, 84, 15);
        textureAtlas.addRegion(baseName + "4_10_LOW", getSpritesLibraryTexture(), 791, 274, 51, 15); // dissipating on floor
        textureAtlas.addRegion(baseName + "4_11_HIGH", getSpritesLibraryTexture(), 1045, 118, 42, 33); // source
    }

    private void initLeverRegions() {
        String baseName = LeverComponent.class.getName();
        textureAtlas.addRegion(baseName + "4_1_LOW", getSpritesLibraryTexture(), 188, 237, 27, 26);
        textureAtlas.addRegion(baseName + "4_2_LOW", getSpritesLibraryTexture(), 239, 237, 27, 26);
    }

    private void initDecoSparkRegions() {
        String baseName = DecoSparkComponent.class.getName();
        textureAtlas.addRegion(baseName + "4_1_LOW", getSpritesLibraryTexture(), 1022, 163, 23, 21);
        textureAtlas.addRegion(baseName + "4_2_LOW", getSpritesLibraryTexture(), 1051, 158, 14, 18);
        textureAtlas.addRegion(baseName + "4_3_LOW", getSpritesLibraryTexture(), 1032, 185, 22, 38);
        textureAtlas.addRegion(baseName + "4_4_LOW", getSpritesLibraryTexture(), 1060, 174, 28, 32);
        textureAtlas.addRegion(baseName + "4_5_LOW", getSpritesLibraryTexture(), 1049, 230, 41, 15);
    }

    private void initFlyingBatteryRegions() {
        String baseName = FlyingBatteryComponent.class.getName();
        textureAtlas.addRegion(baseName + "4_1_LOW", getSpritesLibraryTexture(), 990, 184, 18, 15);
    }

    private void initGeneratorRegions() {
        String baseName = GeneratorComponent.class.getName();
        textureAtlas.addRegion(baseName + "4_1_LOW", getSpritesLibraryTexture(), 997, 256, 13, 11);
        textureAtlas.addRegion(baseName + "4_2_LOW", getSpritesLibraryTexture(), 997, 256, 28, 15);
        textureAtlas.addRegion(baseName + "4_3_LOW", getSpritesLibraryTexture(), 997, 256, 43, 15);
    }

    private void initBatteryItemIndicatorRegions() {
        String baseName = BatteryItemComponent.class.getName();
        textureAtlas.addRegion(baseName + "0_1_LOW", getSpritesLibraryTexture(), 191, 89, 46, 22);
    }

    private void initChargeIndicatorRegions() {
        String baseName = ChargeIndicatorComponent.class.getName();
        textureAtlas.addRegion(baseName + "0_1_LOW", getSpritesLibraryTexture(), 1075, 91, 15, 18);
        textureAtlas.addRegion(baseName + "0_2_LOW", getSpritesLibraryTexture(), 1060, 91, 30, 18);
        textureAtlas.addRegion(baseName + "0_3_LOW", getSpritesLibraryTexture(), 1045, 91, 45, 18);
        textureAtlas.addRegion(baseName + "0_4_LOW", getSpritesLibraryTexture(), 1030, 91, 60, 18);
        textureAtlas.addRegion(baseName + "0_5_LOW", getSpritesLibraryTexture(), 1015, 91, 75, 18);
        textureAtlas.addRegion(baseName + "0_6_LOW", getSpritesLibraryTexture(), 1000, 91, 90, 18);
        textureAtlas.addRegion(baseName + "0_7_LOW", getSpritesLibraryTexture(), 985, 91, 105, 18);
        textureAtlas.addRegion(baseName + "0_8_LOW", getSpritesLibraryTexture(), 970, 91, 120, 18);
        textureAtlas.addRegion(baseName + "0_9_LOW", getSpritesLibraryTexture(), 955, 91, 135, 18);
        textureAtlas.addRegion(baseName + "0_10_LOW", getSpritesLibraryTexture(), 940, 91, 150, 18);
    }

    public TextureRegion getScreenTexture(Class<?> tagClass, AtlasCoordinates coords) {
        String regionName = tagClass.getName() +
                coords.getLevel() + "_" +
                coords.getColumn() + "_" +
                coords.getVerticalPosition();
        TextureRegion region = textureAtlas.findRegion(regionName);
        if (region == null)
            System.out.println("REGION not found for " +regionName);
        return region;
    }

    public void dispose() {
        getSpritesLibraryTexture().dispose();
    }

    public Texture getSpritesLibraryTexture() {
        return spritesLibraryTexture;
    }

    // Indices start at 1
    public static class AtlasCoordinates {
        public AtlasCoordinates(int level, int column, VerticalPosition verticalPosition) {
            this.level = level;
            this.column = column;
            this.verticalPosition = verticalPosition;
        }

        private int level;
        private int column;
        private VerticalPosition verticalPosition;

        public int getLevel() {
            return level;
        }

        public void setLevel(int level) {
            this.level = level;
        }

        public int getColumn() {
            return column;
        }

        public void setColumn(int column) {
            this.column = column;
        }

        public VerticalPosition getVerticalPosition() {
            return verticalPosition;
        }

        public void setVerticalPosition(VerticalPosition verticalPosition) {
            this.verticalPosition = verticalPosition;
        }
    }

    public enum VerticalPosition {
        LOW, MEDIUM, HIGH
    }
}
