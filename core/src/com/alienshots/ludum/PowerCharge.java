package com.alienshots.ludum;

import com.alienshots.ludum.asset.texture.GameScreenAtlas;
import com.alienshots.ludum.system.*;
import com.badlogic.ashley.core.Engine;
import com.badlogic.ashley.core.Entity;
import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector3;

import java.util.Arrays;
import java.util.stream.IntStream;

public class PowerCharge extends ApplicationAdapter {
    private OrthographicCamera camera;
    private Batch batch;
    private Texture backgroundTexture;
    private Engine engine;

    @Override
	public void create () {
        initCenteredCamera();
        batch = new SpriteBatch();
        this.backgroundTexture = new Texture("background.png");
        initEngine();
    }

	@Override
	public void render () {
        camera.update();
        renderBackground();
        makeGameTimeProgress();
        engine.update(Gdx.graphics.getDeltaTime());
    }

    @Override
	public void dispose () {
        GameScreenAtlas.instance.dispose();
	}

	private void initCenteredCamera() {
        this.camera = new OrthographicCamera(1280f, 720f);
        camera.position.set(new Vector3(camera.viewportWidth / 2f, camera.viewportHeight / 2f, 0));
    }

    private void renderBackground() {
        batch.setProjectionMatrix(camera.combined);
        batch.begin();
        batch.draw(backgroundTexture, 0, 0);
        batch.end();
    }

    private void makeGameTimeProgress() {
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
        engine.addSystem(new RenderSystem(camera));
        engine.addSystem(new PlayerControlSystem());
        engine.addSystem(new SawMovementSystem());
        engine.addSystem(new DropMovementSystem());
        engine.addSystem(new CrateMovementSystem());
    }

    private void initEntities() {
        Entity player = GameEntitiesFactory.instance.createPlayer();
        engine.addEntity(player);
        engine.addEntity(GameEntitiesFactory.instance.createLifeIndicator(player));
        IntStream.range(0,3).forEach(i ->
                engine.addEntity(GameEntitiesFactory.instance.createSaw())
        );
        engine.addEntity(GameEntitiesFactory.instance.createDrop(1, 1));
        engine.addEntity(GameEntitiesFactory.instance.createDrop(2, 3));
        engine.addEntity(GameEntitiesFactory.instance.createDrop(4, 2));
        engine.addEntity(GameEntitiesFactory.instance.createDrop(6, 1));
        IntStream.range(0,3).forEach(i ->
                engine.addEntity(GameEntitiesFactory.instance.createCrate())
        );
    }
}
