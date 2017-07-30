package com.alienshots.ludum;

import com.alienshots.ludum.asset.texture.GameScreenAtlas;
import com.alienshots.ludum.system.SawMovementSystem;
import com.alienshots.ludum.system.MovementSystem;
import com.alienshots.ludum.system.PlayerControlSystem;
import com.alienshots.ludum.system.RenderSystem;
import com.badlogic.ashley.core.Engine;
import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;

import java.util.Arrays;
import java.util.stream.IntStream;

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
        IntStream.range(1,3).forEach(i ->
                engine.addEntity(GameEntitiesFactory.instance.createSaw())
        );
    }
}
