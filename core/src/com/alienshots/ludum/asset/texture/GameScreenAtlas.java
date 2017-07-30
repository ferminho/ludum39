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
        sourceImage = new Texture("background.png");
        textureAtlas = new TextureAtlas();

        initNameTemplateLookup();
        initAtlasRegions();
    }

    private void initNameTemplateLookup() {
        regionNameTemplateLookup.put(PlayerComponent.class, PlayerComponent.class.getName() + "%s_%s");
        regionNameTemplateLookup.put(SawComponent.class, SawComponent.class.getName() + "%s_%s");
    }

    private void initAtlasRegions() {
        initPlayerRegions();
        initSawRegions();
    }

    private void initPlayerRegions() {
        String baseName = PlayerComponent.class.getName();
        textureAtlas.addRegion(baseName + "1_1", sourceImage, 0, 0, 30, 30);
        textureAtlas.addRegion(baseName + "1_2", sourceImage, 50, 50, 30, 30);
    }

    private void initSawRegions() {
        String baseName = SawComponent.class.getName();
        textureAtlas.addRegion(baseName + "1_1", sourceImage, 100, 100, 30, 30);
        textureAtlas.addRegion(baseName + "1_2", sourceImage, 150, 150, 30, 30);
        textureAtlas.addRegion(baseName + "1_3", sourceImage, 200, 200, 30, 30);
        textureAtlas.addRegion(baseName + "1_4", sourceImage, 250, 250, 30, 30);
        textureAtlas.addRegion(baseName + "1_5", sourceImage, 300, 300, 30, 30);
        textureAtlas.addRegion(baseName + "1_6", sourceImage, 350, 350, 30, 30);
        textureAtlas.addRegion(baseName + "1_7", sourceImage, 400, 400, 30, 30);
    }

    public TextureRegion getScreenTexture(Class<?> tagClass, AtlasCoordinates coords) {
        String regionName = String.format(regionNameTemplateLookup.get(tagClass), coords.getLevel(), coords.getColumn());
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
    }
}
