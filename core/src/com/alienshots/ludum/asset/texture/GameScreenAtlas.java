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

    private Texture sourceImage;
    private TextureAtlas textureAtlas;

    public GameScreenAtlas() {
        sourceImage = new Texture("sprites.png");
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
        textureAtlas.addRegion(baseName + "1_1_LOW", sourceImage, 192, 571, 30, 37);
        textureAtlas.addRegion(baseName + "1_2_LOW", sourceImage, 329, 603, 26, 37);
        textureAtlas.addRegion(baseName + "1_3_LOW", sourceImage, 455, 603, 26, 37);
        textureAtlas.addRegion(baseName + "1_4_LOW", sourceImage, 581, 603, 26, 37);
        textureAtlas.addRegion(baseName + "1_5_LOW", sourceImage, 707, 603, 26, 37);
        textureAtlas.addRegion(baseName + "1_6_LOW", sourceImage, 832, 603, 26, 37);
        textureAtlas.addRegion(baseName + "1_7_LOW", sourceImage, 958, 603, 26, 37);
        textureAtlas.addRegion(baseName + "1_8_LOW", sourceImage, 1050, 594, 25, 36);
        textureAtlas.addRegion(baseName + "1_2_HIGH", sourceImage, 328, 544, 28, 30);
        textureAtlas.addRegion(baseName + "1_3_HIGH", sourceImage, 454, 543, 28, 30);
        textureAtlas.addRegion(baseName + "1_4_HIGH", sourceImage, 580, 540, 28, 30);
        textureAtlas.addRegion(baseName + "1_5_HIGH", sourceImage, 706, 540, 28, 30);
        textureAtlas.addRegion(baseName + "1_6_HIGH", sourceImage, 831, 547, 28, 30);
        textureAtlas.addRegion(baseName + "1_7_HIGH", sourceImage, 957, 538, 28, 30);
        textureAtlas.addRegion(baseName + "1_8_HIGH", sourceImage, 1052, 538, 25, 36);
        // second floor
        textureAtlas.addRegion(baseName + "2_1_LOW", sourceImage, 208, 474, 25, 36);
        textureAtlas.addRegion(baseName + "2_2_LOW", sourceImage, 275, 483, 26, 37);
        textureAtlas.addRegion(baseName + "2_3_LOW", sourceImage, 401, 483, 26, 37);
        textureAtlas.addRegion(baseName + "2_4_LOW", sourceImage, 527, 483, 26, 37);
        textureAtlas.addRegion(baseName + "2_5_LOW", sourceImage, 653, 483, 26, 37);
        textureAtlas.addRegion(baseName + "2_6_LOW", sourceImage, 779, 483, 26, 37);
        textureAtlas.addRegion(baseName + "2_7_LOW", sourceImage, 908, 483, 26, 37);
        textureAtlas.addRegion(baseName + "2_8_LOW", sourceImage, 1034, 483, 26, 37);
        textureAtlas.addRegion(baseName + "2_1_HIGH", sourceImage, 206, 418, 25, 36);
        textureAtlas.addRegion(baseName + "2_2_HIGH", sourceImage, 274, 427, 28, 30);
        textureAtlas.addRegion(baseName + "2_3_HIGH", sourceImage, 400, 426, 28, 30);
        textureAtlas.addRegion(baseName + "2_4_HIGH", sourceImage, 526, 432, 28, 30);
        textureAtlas.addRegion(baseName + "2_5_HIGH", sourceImage, 652, 431, 28, 30);
        textureAtlas.addRegion(baseName + "2_6_HIGH", sourceImage, 778, 426, 28, 30);
        textureAtlas.addRegion(baseName + "2_7_HIGH", sourceImage, 907, 427, 28, 30);
        textureAtlas.addRegion(baseName + "2_8_HIGH", sourceImage, 1033, 434, 28, 30);
        // third floor
        textureAtlas.addRegion(baseName + "3_1_LOW", sourceImage, 227, 363, 26, 37);
        textureAtlas.addRegion(baseName + "3_2_LOW", sourceImage, 351, 363, 26, 37);
        textureAtlas.addRegion(baseName + "3_3_LOW", sourceImage, 477, 363, 26, 37);
        textureAtlas.addRegion(baseName + "3_4_LOW", sourceImage, 603, 363, 26, 37);
        textureAtlas.addRegion(baseName + "3_5_LOW", sourceImage, 729, 363, 26, 37);
        textureAtlas.addRegion(baseName + "3_6_LOW", sourceImage, 848, 363, 26, 37);
        textureAtlas.addRegion(baseName + "3_7_LOW", sourceImage, 955, 354, 25, 36);
        textureAtlas.addRegion(baseName + "3_1_HIGH", sourceImage, 226, 314, 28, 30);
        textureAtlas.addRegion(baseName + "3_2_HIGH", sourceImage, 350, 307, 28, 30);
        textureAtlas.addRegion(baseName + "3_3_HIGH", sourceImage, 476, 309, 28, 30);
        textureAtlas.addRegion(baseName + "3_4_HIGH", sourceImage, 602, 312, 28, 30);
        textureAtlas.addRegion(baseName + "3_5_HIGH", sourceImage, 728, 309, 28, 30);
        textureAtlas.addRegion(baseName + "3_6_HIGH", sourceImage, 847, 306, 28, 30);
        textureAtlas.addRegion(baseName + "3_7_HIGH", sourceImage, 956, 298, 25, 36);
        // fourth floor
        textureAtlas.addRegion(baseName + "4_1_LOW", sourceImage, 266, 229, 30, 37);
        textureAtlas.addRegion(baseName + "4_2_LOW", sourceImage, 329, 244, 26, 37);
        textureAtlas.addRegion(baseName + "4_3_LOW", sourceImage, 455, 244, 26, 37);
        textureAtlas.addRegion(baseName + "4_4_LOW", sourceImage, 581, 244, 26, 37);
        textureAtlas.addRegion(baseName + "4_5_LOW", sourceImage, 707, 244, 26, 37);
        textureAtlas.addRegion(baseName + "4_6_LOW", sourceImage, 832, 244, 26, 37);
        textureAtlas.addRegion(baseName + "4_7_LOW", sourceImage, 956, 244, 26, 37);
        textureAtlas.addRegion(baseName + "4_2_HIGH", sourceImage, 328, 179, 28, 30);
        textureAtlas.addRegion(baseName + "4_3_HIGH", sourceImage, 454, 186, 28, 30);
        textureAtlas.addRegion(baseName + "4_4_HIGH", sourceImage, 580, 188, 28, 30);
        textureAtlas.addRegion(baseName + "4_5_HIGH", sourceImage, 706, 185, 28, 30);
        textureAtlas.addRegion(baseName + "4_6_HIGH", sourceImage, 831, 183, 28, 30);
        textureAtlas.addRegion(baseName + "4_7_HIGH", sourceImage, 956, 177, 30, 37);
    }

    private void initSawRegions() {
        String baseName = SawComponent.class.getName();
        // Facing right
        textureAtlas.addRegion(baseName + "1_1_LOW", sourceImage, 187, 632, 30, 27);
        textureAtlas.addRegion(baseName + "1_3_LOW", sourceImage, 298, 632, 30, 27);
        textureAtlas.addRegion(baseName + "1_5_LOW", sourceImage, 424, 632, 30, 27);
        textureAtlas.addRegion(baseName + "1_7_LOW", sourceImage, 550, 632, 30, 27);
        textureAtlas.addRegion(baseName + "1_9_LOW", sourceImage, 676, 632, 30, 27);
        textureAtlas.addRegion(baseName + "1_11_LOW", sourceImage, 802, 632, 30, 27);
        textureAtlas.addRegion(baseName + "1_13_LOW", sourceImage, 928, 632, 30, 27);
        // Facing left
        textureAtlas.addRegion(baseName + "1_2_LOW", sourceImage, 219, 632, 30, 27);
        textureAtlas.addRegion(baseName + "1_4_LOW", sourceImage, 356, 632, 30, 27);
        textureAtlas.addRegion(baseName + "1_6_LOW", sourceImage, 482, 632, 30, 27);
        textureAtlas.addRegion(baseName + "1_8_LOW", sourceImage, 608, 632, 30, 27);
        textureAtlas.addRegion(baseName + "1_10_LOW", sourceImage, 734, 632, 30, 27);
        textureAtlas.addRegion(baseName + "1_12_LOW", sourceImage, 859, 632, 30, 27);
        textureAtlas.addRegion(baseName + "1_14_LOW", sourceImage, 985, 632, 30, 27);
    }

    private void initDropRegions() {
        String baseName = DropComponent.class.getName();
        textureAtlas.addRegion(baseName + "2_1_HIGH", sourceImage, 260, 434, 13, 23);
        textureAtlas.addRegion(baseName + "2_1_MEDIUM", sourceImage, 260, 483, 13, 23);
        textureAtlas.addRegion(baseName + "2_1_LOW", sourceImage, 258, 511, 16, 10); // splash
        textureAtlas.addRegion(baseName + "2_2_HIGH", sourceImage, 386, 434, 13, 23);
        textureAtlas.addRegion(baseName + "2_2_MEDIUM", sourceImage, 386, 483, 13, 23);
        textureAtlas.addRegion(baseName + "2_2_LOW", sourceImage, 384, 511, 16, 10); // splash
        textureAtlas.addRegion(baseName + "2_3_HIGH", sourceImage, 512, 434, 13, 23);
        textureAtlas.addRegion(baseName + "2_3_MEDIUM", sourceImage, 512, 483, 13, 23);
        textureAtlas.addRegion(baseName + "2_3_LOW", sourceImage, 510, 511, 16, 10); // splash
        textureAtlas.addRegion(baseName + "2_4_HIGH", sourceImage, 638, 434, 13, 23);
        textureAtlas.addRegion(baseName + "2_4_MEDIUM", sourceImage, 638, 483, 13, 23);
        textureAtlas.addRegion(baseName + "2_4_LOW", sourceImage, 636, 511, 16, 10); // splash
        textureAtlas.addRegion(baseName + "2_5_HIGH", sourceImage, 764, 434, 13, 23);
        textureAtlas.addRegion(baseName + "2_5_MEDIUM", sourceImage, 764, 483, 13, 23);
        textureAtlas.addRegion(baseName + "2_5_LOW", sourceImage, 762, 511, 16, 10); // splash
        textureAtlas.addRegion(baseName + "2_6_HIGH", sourceImage, 893, 434, 13, 23);
        textureAtlas.addRegion(baseName + "2_6_MEDIUM", sourceImage, 893, 483, 13, 23);
        textureAtlas.addRegion(baseName + "2_6_LOW", sourceImage, 891, 511, 16, 10); // splash
    }

    private void initCrateRegions() {
        String baseName = CrateComponent.class.getName();
        // Facing right
        textureAtlas.addRegion(baseName + "3_1_LOW", sourceImage, 186, 298, 39, 102);
        textureAtlas.addRegion(baseName + "3_3_LOW", sourceImage, 310, 298, 39, 102);
        textureAtlas.addRegion(baseName + "3_5_LOW", sourceImage, 436, 298, 39, 102);
        textureAtlas.addRegion(baseName + "3_7_LOW", sourceImage, 562, 298, 39, 102);
        textureAtlas.addRegion(baseName + "3_9_LOW", sourceImage, 688, 298, 39, 102);
        textureAtlas.addRegion(baseName + "3_11_LOW", sourceImage, 807, 298, 39, 102);
        textureAtlas.addRegion(baseName + "3_13_LOW", sourceImage, 915, 298, 39, 102);
        textureAtlas.addRegion(baseName + "3_15_LOW", sourceImage, 1044, 298, 39, 102);
        // Facing left
        textureAtlas.addRegion(baseName + "3_2_LOW", sourceImage, 254, 298, 39, 102);
        textureAtlas.addRegion(baseName + "3_4_LOW", sourceImage, 379, 298, 39, 102);
        textureAtlas.addRegion(baseName + "3_6_LOW", sourceImage, 505, 298, 39, 102);
        textureAtlas.addRegion(baseName + "3_8_LOW", sourceImage, 631, 298, 39, 102);
        textureAtlas.addRegion(baseName + "3_10_LOW", sourceImage, 757, 298, 39, 102);
        textureAtlas.addRegion(baseName + "3_12_LOW", sourceImage, 876, 298, 39, 102);
        textureAtlas.addRegion(baseName + "3_14_LOW", sourceImage, 982, 298, 39, 102);
        textureAtlas.addRegion(baseName + "3_16_LOW", sourceImage, 1044, 298, 39, 102);
    }

    private void initSparkRegions() {
        String baseName = SparkComponent.class.getName();
        textureAtlas.addRegion(baseName + "4_1_HIGH", sourceImage, 379, 95, 34, 42); // going away
        textureAtlas.addRegion(baseName + "4_2_HIGH", sourceImage, 420, 115, 54, 42); // about to fall
        textureAtlas.addRegion(baseName + "4_2_MEDIUM", sourceImage, 428, 158, 26, 114); // discharge
        textureAtlas.addRegion(baseName + "4_2_LOW", sourceImage, 414, 274, 40, 23); // on floor
        textureAtlas.addRegion(baseName + "4_3_HIGH", sourceImage, 480, 128, 63, 13);
        textureAtlas.addRegion(baseName + "4_4_HIGH", sourceImage, 546, 115, 54, 42); // about to fall
        textureAtlas.addRegion(baseName + "4_4_MEDIUM", sourceImage, 554, 158, 26, 114); // discharge
        textureAtlas.addRegion(baseName + "4_4_LOW", sourceImage, 540, 274, 40, 23); // on floor
        textureAtlas.addRegion(baseName + "4_5_HIGH", sourceImage, 604, 130, 61, 13);
        textureAtlas.addRegion(baseName + "4_6_HIGH", sourceImage, 672, 115, 54, 42); // about to fall
        textureAtlas.addRegion(baseName + "4_6_MEDIUM", sourceImage, 680, 158, 26, 114); // discharge
        textureAtlas.addRegion(baseName + "4_6_LOW", sourceImage, 666, 274, 40, 23); // on floor
        textureAtlas.addRegion(baseName + "4_7_HIGH", sourceImage, 729, 127, 62, 16);
        textureAtlas.addRegion(baseName + "4_8_HIGH", sourceImage, 797, 115, 54, 42); // about to fall
        textureAtlas.addRegion(baseName + "4_8_MEDIUM", sourceImage, 805, 158, 26, 114); // discharge
        textureAtlas.addRegion(baseName + "4_8_LOW", sourceImage, 791, 274, 40, 23); // on floor
        textureAtlas.addRegion(baseName + "4_9_HIGH", sourceImage, 856, 130, 63, 13);
        textureAtlas.addRegion(baseName + "4_10_HIGH", sourceImage, 938, 127, 84, 15);
        textureAtlas.addRegion(baseName + "4_10_LOW", sourceImage, 791, 274, 51, 15); // dissipating on floor
        textureAtlas.addRegion(baseName + "4_11_HIGH", sourceImage, 1045, 118, 42, 33); // source
    }

    private void initLeverRegions() {
        String baseName = LeverComponent.class.getName();
        textureAtlas.addRegion(baseName + "4_1_LOW", sourceImage, 188, 237, 27, 26);
        textureAtlas.addRegion(baseName + "4_2_LOW", sourceImage, 239, 237, 27, 26);
    }

    private void initDecoSparkRegions() {
        String baseName = DecoSparkComponent.class.getName();
        textureAtlas.addRegion(baseName + "4_1_LOW", sourceImage, 1022, 163, 23, 21);
        textureAtlas.addRegion(baseName + "4_2_LOW", sourceImage, 1051, 158, 14, 18);
        textureAtlas.addRegion(baseName + "4_3_LOW", sourceImage, 1032, 185, 22, 38);
        textureAtlas.addRegion(baseName + "4_4_LOW", sourceImage, 1060, 174, 28, 32);
        textureAtlas.addRegion(baseName + "4_5_LOW", sourceImage, 1049, 230, 41, 15);
    }

    private void initFlyingBatteryRegions() {
        String baseName = FlyingBatteryComponent.class.getName();
        textureAtlas.addRegion(baseName + "4_1_LOW", sourceImage, 990, 184, 18, 15);
    }

    private void initGeneratorRegions() {
        String baseName = GeneratorComponent.class.getName();
        textureAtlas.addRegion(baseName + "4_1_LOW", sourceImage, 997, 256, 13, 11);
        textureAtlas.addRegion(baseName + "4_2_LOW", sourceImage, 997, 256, 28, 15);
        textureAtlas.addRegion(baseName + "4_3_LOW", sourceImage, 997, 256, 43, 15);
    }

    private void initBatteryItemIndicatorRegions() {
        String baseName = BatteryItemComponent.class.getName();
        textureAtlas.addRegion(baseName + "0_1_LOW", sourceImage, 191, 89, 46, 22);
    }

    private void initChargeIndicatorRegions() {
        String baseName = ChargeIndicatorComponent.class.getName();
        textureAtlas.addRegion(baseName + "0_1_LOW", sourceImage, 1075, 91, 15, 18);
        textureAtlas.addRegion(baseName + "0_2_LOW", sourceImage, 1060, 91, 30, 18);
        textureAtlas.addRegion(baseName + "0_3_LOW", sourceImage, 1045, 91, 45, 18);
        textureAtlas.addRegion(baseName + "0_4_LOW", sourceImage, 1030, 91, 60, 18);
        textureAtlas.addRegion(baseName + "0_5_LOW", sourceImage, 1015, 91, 75, 18);
        textureAtlas.addRegion(baseName + "0_6_LOW", sourceImage, 1000, 91, 90, 18);
        textureAtlas.addRegion(baseName + "0_7_LOW", sourceImage, 985, 91, 105, 18);
        textureAtlas.addRegion(baseName + "0_8_LOW", sourceImage, 970, 91, 120, 18);
        textureAtlas.addRegion(baseName + "0_9_LOW", sourceImage, 955, 91, 135, 18);
        textureAtlas.addRegion(baseName + "0_10_LOW", sourceImage, 940, 91, 150, 18);
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
        sourceImage.dispose();
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
