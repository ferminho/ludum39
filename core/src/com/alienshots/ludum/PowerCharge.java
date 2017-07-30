package com.alienshots.ludum;

import com.alienshots.ludum.asset.texture.GameScreenAtlas;
import com.alienshots.ludum.system.MovementSystem;
import com.alienshots.ludum.system.PlayerControlSystem;
import com.alienshots.ludum.system.RenderSystem;
import com.alienshots.ludum.system.SawMovementSystem;
import com.badlogic.ashley.core.Engine;
import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import java.util.Arrays;
import java.util.stream.IntStream;

public class PowerCharge extends ApplicationAdapter {
    private Batch batch;
    private Texture backgroundTexture;
    private Engine engine;

    @Override
	public void create () {
        batch = new SpriteBatch();
        this.backgroundTexture = new Texture("background.png");
        initEngine();
    }

	@Override
	public void render () {
//		Gdx.gl.glClearColor(1, 0, 0, 1);
        batch.begin();
        batch.draw(backgroundTexture, 0, 0);
        batch.end();

        moveGame();
        engine.update(Gdx.graphics.getDeltaTime());
    }

    @Override
	public void dispose () {
        GameScreenAtlas.instance.dispose();
	}

    private void moveGame() {
        boolean timeIsMoving = Time.instance.timeIsMoving();
        if (timeIsMoving) {
            Time.tickTimers();
        }
        Arrays.stream(engine.getSystems().toArray())
              .filter(es -> es instanceof MovementSystem)
              .forEach(es -> es.setProcessing(timeIsMoving));
    }

    private void initEngine() {
        engine = new Engine();

        initSystems();
        initEntities();
    }

    private void initSystems() {
        engine.addSystem(new RenderSystem());
        engine.addSystem(new PlayerControlSystem());
        engine.addSystem(new SawMovementSystem());
    }

    private void initEntities() {
        engine.addEntity(GameEntitiesFactory.instance.createPlayer());
        IntStream.range(0,3).forEach(i ->
                engine.addEntity(GameEntitiesFactory.instance.createSaw())
        );
    }
}
