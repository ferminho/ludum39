package com.alienshots.ludum;

import com.alienshots.ludum.system.RenderSystem;
import com.badlogic.ashley.core.Engine;
import com.badlogic.ashley.core.Entity;
import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;

public class PowerCharge extends ApplicationAdapter {
	SpriteBatch batch;
	Texture img;

    private Engine engine;
    private TextureAtlas textureAtlas;
    private World world;

    @Override
	public void create () {
		batch = new SpriteBatch();
		img = new Texture("badlogic.jpg");

        engine = new Engine();
        textureAtlas = new TextureAtlas();
        textureAtlas.addRegion("PlayerStart", img, 0, 0, 30, 30);
        world = new World(textureAtlas);

        engine.addSystem(new RenderSystem());
        engine.addEntity(world.createPlayer());
    }

	@Override
	public void render () {
//		Gdx.gl.glClearColor(1, 0, 0, 1);
//		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
//		batch.begin();
//		batch.draw(img, 0, 0);
//		batch.end();

//        batch.begin();
//        textureAtlas.addRegion("Blo", img, 60, 60, 30, 30);
//        batch.draw(textureAtlas.findRegion("Bla"), 0, 0);
//        batch.draw(textureAtlas.findRegion("Blo"), 60, 60);
//        batch.end();
        engine.update(Gdx.graphics.getDeltaTime());
    }
	
	@Override
	public void dispose () {
		batch.dispose();
		img.dispose();
		textureAtlas.dispose();
	}
}
