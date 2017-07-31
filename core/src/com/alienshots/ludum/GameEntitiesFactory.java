package com.alienshots.ludum;

import com.alienshots.ludum.asset.texture.GameScreenAtlas;
import com.alienshots.ludum.component.*;
import com.badlogic.ashley.core.Component;
import com.badlogic.ashley.core.Entity;

import static com.alienshots.ludum.asset.texture.GameScreenAtlas.*;

public class GameEntitiesFactory {

    public static final GameEntitiesFactory instance = new GameEntitiesFactory();

    public Entity createPlayer() {
        Entity player = new Entity();
        AtlasCoordinates initialCoords = new AtlasCoordinates(1, 1, VerticalPosition.LOW);

        player.add(new PlayerComponent());
        player.add(new DisplayComponent(true));
        player.add(buildPositionComponent(PlayerComponent.class, initialCoords));
        player.add(new LifeComponent(3));
        player.add(new BatteryItemComponent(true));
        player.add(new FlyingBatteryComponent());
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

    public Entity createFlyingBattery(Entity player) {
        Entity flyingBattery = new Entity();
        FlyingBatteryComponent flyingBatteryComponent = player.getComponent(FlyingBatteryComponent.class);
        AtlasCoordinates initialCoords = new AtlasCoordinates(4, 1, VerticalPosition.LOW);

        flyingBattery.add(flyingBatteryComponent);
        flyingBattery.add(new DisplayComponent(true));
        flyingBattery.add(buildPositionComponent(FlyingBatteryComponent.class, initialCoords));
        return flyingBattery;
    }
    public Entity createSaw() {
        Entity saw = new Entity();
        AtlasCoordinates initialCoords = new AtlasCoordinates(1, 7, VerticalPosition.LOW);

        saw.add(new SawComponent());
        saw.add(new DisplayComponent(false));
        saw.add(buildPositionComponent(SawComponent.class, initialCoords));
        return saw;
    }

    public Entity createDrop(int column, int delayInUpdates) {
        Entity drop = new Entity();
        AtlasCoordinates initialCoords = new AtlasCoordinates(2, column, VerticalPosition.HIGH);
        drop.add(new DropComponent(Time.newTimer(delayInUpdates)));
        drop.add(new DisplayComponent(false));
        drop.add(buildPositionComponent(DropComponent.class, initialCoords));
        return drop;
    }

    public Entity createCrate() {
        Entity crate = new Entity();
        AtlasCoordinates initialCoords = new AtlasCoordinates(3, 1, VerticalPosition.LOW);

        crate.add(new CrateComponent());
        crate.add(new DisplayComponent(false));
        crate.add(buildPositionComponent(CrateComponent.class, initialCoords));
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
