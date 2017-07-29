package com.alienshots.ludum;

import com.alienshots.ludum.asset.texture.GameScreenAtlas;
import com.alienshots.ludum.system.PlayerControlSystem;
import com.alienshots.ludum.system.RenderSystem;
import com.badlogic.ashley.core.Engine;
import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;

public class PowerCharge extends ApplicationAdapter {
    private Engine engine;

    @Override
	public void create () {
        initEngine();
    }

	@Override
	public void render () {
//		Gdx.gl.glClearColor(1, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        engine.update(Gdx.graphics.getDeltaTime());
    }
	
	@Override
	public void dispose () {
        GameScreenAtlas.instance.dispose();
	}

	private void initEngine() {
        engine = new Engine();

        engine.addSystem(new RenderSystem());
        engine.addSystem(new PlayerControlSystem());

        engine.addEntity(GameEntitiesFactory.instance.createPlayer());
    }
}
