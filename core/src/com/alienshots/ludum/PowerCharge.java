package com.alienshots.ludum;

import com.alienshots.ludum.asset.texture.GameScreenAtlas;
import com.alienshots.ludum.system.*;
import com.alienshots.ludum.system.collision.HazardCollisionSystem;
import com.alienshots.ludum.system.collision.PlayerCollisionSystem;
import com.alienshots.ludum.system.ui.BatteryItemIndicatorUpdateSystem;
import com.alienshots.ludum.system.ui.ChargeIndicatorUpdateSystem;
import com.alienshots.ludum.system.ui.GeneratorLevelIndicatorUpdateSystem;
import com.alienshots.ludum.system.ui.LifeIndicatorUpdateSystem;
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
    private Entity world;
    private Entity player;

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

        initEntities();
        initSystems();
    }

    private void initEntities() {
        GameEntitiesFactory factory = GameEntitiesFactory.instance;
        world = GameEntitiesFactory.instance.createWorld();
        engine.addEntity(world);
        engine.addEntity(factory.createChargeIndicator(world));
        Entity lever = factory.createLever();
        engine.addEntity(lever);
        Entity generator = factory.createGenerator(lever, world);
        engine.addEntity(generator);
        player = factory.createPlayer(generator, lever, world);
        engine.addEntity(player);
        engine.addEntity(factory.createBatteryItemIndicator(player));
        engine.addEntity(factory.createLifeIndicator(player));
        engine.addEntity(factory.createFlyingBattery(player, generator));
        IntStream.range(0,3).forEach(i ->
                engine.addEntity(factory.createSaw(world))
        );
        engine.addEntity(factory.createDrop(1, 1));
        engine.addEntity(factory.createDrop(2, 3));
        engine.addEntity(factory.createDrop(4, 2));
        engine.addEntity(factory.createDrop(6, 1));
        IntStream.range(0,3).forEach(i ->
                engine.addEntity(factory.createCrate(world))
        );
    }

    private void initSystems() {
        engine.addSystem(new WorldChargeDrainerSystem());
        engine.addSystem(new GeneratorActivatorSystem());
        engine.addSystem(new LeverMovementSystem());
        engine.addSystem(new PlayerControlSystem());
        engine.addSystem(new PlayerEventsSystem());
        engine.addSystem(new SawMovementSystem());
        engine.addSystem(new DropMovementSystem());
        engine.addSystem(new CrateMovementSystem());
        engine.addSystem(new FlyingBatteryMovementSystem());
        engine.addSystem(new HazardCollisionSystem(player));
        engine.addSystem(new PlayerCollisionSystem(player));
        engine.addSystem(new ChargeIndicatorUpdateSystem());
        engine.addSystem(new GeneratorLevelIndicatorUpdateSystem());
        engine.addSystem(new BatteryItemIndicatorUpdateSystem());
        engine.addSystem(new LifeIndicatorUpdateSystem());
        engine.addSystem(new RenderSystem(camera, world));
    }
}
