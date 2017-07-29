package com.alienshots.ludum.asset.texture;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

import static com.alienshots.ludum.asset.texture.GameScreenAtlas.ScreenTexture.PLAYER_L1_1;
import static com.alienshots.ludum.asset.texture.GameScreenAtlas.ScreenTexture.PLAYER_L1_2;

public class GameScreenAtlas {

    public static final GameScreenAtlas instance = new GameScreenAtlas();

    private Texture sourceImage;
    private TextureAtlas textureAtlas;

    public GameScreenAtlas() {
        sourceImage = new Texture("badlogic.jpg");
        textureAtlas = new TextureAtlas();

        textureAtlas.addRegion(PLAYER_L1_1.name(), sourceImage, 0, 0, 30, 30);
        textureAtlas.addRegion(PLAYER_L1_2.name(), sourceImage, 50, 50, 30, 30);
    }

    public TextureRegion getScreenTexture(ScreenTexture screenTexture) {
        return textureAtlas.findRegion(screenTexture.name());
    }

    public void dispose() {
        sourceImage.dispose();
    }

    public enum ScreenTexture {
        PLAYER_L1_1, PLAYER_L1_2, PLAYER_L1_3, PLAYER_L1_4, PLAYER_L1_5, PLAYER_L1_6, PLAYER_L1_7, PLAYER_L1_8,
        PLAYER_L2_1, PLAYER_L2_2, PLAYER_L2_3, PLAYER_L2_4, PLAYER_L2_5, PLAYER_L2_6, PLAYER_L2_7, PLAYER_L2_8,
        PLAYER_L3_1, PLAYER_L3_2, PLAYER_L3_3, PLAYER_L3_4, PLAYER_L3_5, PLAYER_L3_6, PLAYER_L3_7, PLAYER_L3_8,
        PLAYER_L4_1, PLAYER_L4_2, PLAYER_L4_3, PLAYER_L4_4, PLAYER_L4_5, PLAYER_L4_6, PLAYER_L4_7, PLAYER_L4_8
    }
}
