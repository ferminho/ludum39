package com.alienshots.ludum;

import com.alienshots.ludum.asset.texture.GameScreenAtlas;
import com.alienshots.ludum.component.*;
import com.badlogic.ashley.core.Component;
import com.badlogic.ashley.core.Entity;

import static com.alienshots.ludum.asset.texture.GameScreenAtlas.AtlasCoordinates;
import static com.alienshots.ludum.asset.texture.GameScreenAtlas.VerticalPosition;

public class GameEntitiesFactory {

    public static final GameEntitiesFactory instance = new GameEntitiesFactory();

    public Entity createWorld() {
        Entity world = new Entity();
        world.add(new WorldComponent());
        world.add(new WorldChargeComponent());
        world.add(new SawDirectionComponent(SawDirectionComponent.Direction.LEFT));
        world.add(new CrateDirectionComponent(CrateDirectionComponent.Direction.RIGHT));
        return world;
    }

    public Entity createChargeIndicator(Entity world) {
        Entity chargeIndicator = new Entity();
        AtlasCoordinates initialCoords = new AtlasCoordinates(0, 10, VerticalPosition.LOW);
        chargeIndicator.add(new ChargeIndicatorComponent());
        chargeIndicator.add(world.getComponent(WorldChargeComponent.class));
        chargeIndicator.add(new DisplayComponent(true));
        chargeIndicator.add(buildPositionComponent(ChargeIndicatorComponent.class, initialCoords));
        return chargeIndicator;
    }

    public Entity createLever() {
        Entity lever = new Entity();
        AtlasCoordinates initialCoords = new AtlasCoordinates(4, 1, VerticalPosition.LOW);

        lever.add(new LeverComponent());
        lever.add(new LeverStateComponent(true));
        lever.add(new DisplayComponent(true));
        lever.add(buildPositionComponent(LeverComponent.class, initialCoords));
        return lever;
    }

    public Entity createGenerator(Entity lever, Entity world) {
        Entity generator = new Entity();
        AtlasCoordinates initialCoords = new AtlasCoordinates(4, 1, VerticalPosition.LOW);

        generator.add(new GeneratorComponent());
        generator.add(new GeneratorLevelComponent());
        generator.add(world.getComponent(WorldChargeComponent.class));
        generator.add(new DisplayComponent(false));
        generator.add(lever.getComponent(LeverStateComponent.class));
        generator.add(buildPositionComponent(GeneratorComponent.class, initialCoords));
        return generator;
    }

    public Entity createPlayer(Entity generator, Entity lever, Entity world) {
        Entity player = new Entity();
        AtlasCoordinates initialCoords = new AtlasCoordinates(1, 1, VerticalPosition.LOW);

        player.add(new PlayerComponent());
        player.add(new CollisionComponent());
        player.add(new DisplayComponent(true));
        player.add(buildPositionComponent(PlayerComponent.class, initialCoords));
        player.add(new LifeComponent(3));
        player.add(new BatteryItemComponent(true));
        player.add(new FlyingBatteryLaunchComponent());
        player.add(generator.getComponent(GeneratorLevelComponent.class));
        player.add(lever.getComponent(LeverStateComponent.class));
        player.add(world.getComponent(SawDirectionComponent.class));
        player.add(world.getComponent(CrateDirectionComponent.class));
        return player;
    }

    public Entity createBatteryItemIndicator(Entity player) {
        Entity batteryItemIndicator = new Entity();
        BatteryItemComponent batteryItemComponent = player.getComponent(BatteryItemComponent.class);
        AtlasCoordinates initialCoords = new AtlasCoordinates(0, 1, VerticalPosition.LOW);

        batteryItemIndicator.add(batteryItemComponent);
        batteryItemIndicator.add(new DisplayComponent(true));
        batteryItemIndicator.add(buildPositionComponent(BatteryItemComponent.class, initialCoords));
        return batteryItemIndicator;
    }

    public Entity createLifeIndicator(Entity player) {
        Entity lifeIndicator = new Entity();
        LifeComponent lifeComponent = player.getComponent(LifeComponent.class);
        AtlasCoordinates initialCoords = new AtlasCoordinates(0, lifeComponent.getLives(), VerticalPosition.LOW);

        lifeIndicator.add(lifeComponent);
        lifeIndicator.add(new DisplayComponent(true));
        lifeIndicator.add(buildPositionComponent(LifeComponent.class, initialCoords));
        return lifeIndicator;
    }

    public Entity createFlyingBattery(Entity player, Entity generator) {
        Entity flyingBattery = new Entity();
        AtlasCoordinates initialCoords = new AtlasCoordinates(4, 1, VerticalPosition.LOW);

        flyingBattery.add(new FlyingBatteryComponent());
        flyingBattery.add(player.getComponent(FlyingBatteryLaunchComponent.class));
        flyingBattery.add(generator.getComponent(GeneratorLevelComponent.class));
        flyingBattery.add(new DisplayComponent(true));
        flyingBattery.add(buildPositionComponent(FlyingBatteryComponent.class, initialCoords));
        return flyingBattery;
    }

    public Entity createSaw(Entity world) {
        Entity saw = new Entity();
        AtlasCoordinates initialCoords = new AtlasCoordinates(1, 13, VerticalPosition.LOW);

        saw.add(new SawComponent());
        saw.add(new HazardComponent());
        saw.add(new CollisionComponent());
        saw.add(new DisplayComponent(false));
        saw.add(buildPositionComponent(SawComponent.class, initialCoords));
        saw.add(world.getComponent(SawDirectionComponent.class));
        return saw;
    }

    public Entity createDrop(int column, int delayInUpdates) {
        Entity drop = new Entity();
        AtlasCoordinates initialCoords = new AtlasCoordinates(2, column, VerticalPosition.HIGH);
        drop.add(new DropComponent(Time.newTimer(delayInUpdates)));
        drop.add(new HazardComponent());
        drop.add(new CollisionComponent());
        drop.add(new DisplayComponent(false));
        drop.add(buildPositionComponent(DropComponent.class, initialCoords));
        return drop;
    }

    public Entity createCrate(Entity world) {
        Entity crate = new Entity();
        AtlasCoordinates initialCoords = new AtlasCoordinates(3, 1, VerticalPosition.LOW);

        crate.add(new CrateComponent());
        crate.add(new HazardComponent());
        crate.add(new CollisionComponent());
        crate.add(new DisplayComponent(false));
        crate.add(buildPositionComponent(CrateComponent.class, initialCoords));
        crate.add(world.getComponent(CrateDirectionComponent.class));
        return crate;
    }

    private PositionComponent buildPositionComponent(Class<? extends Component> componentClass,
                                                     AtlasCoordinates initialCoords) {
        return PositionComponent.builder()
                .coords(initialCoords)
                .region(GameScreenAtlas.instance.getScreenTexture(componentClass, initialCoords))
                .build();
    }
}
