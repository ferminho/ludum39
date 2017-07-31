package com.alienshots.ludum.system;

import com.alienshots.ludum.component.GeneratorComponent;
import com.alienshots.ludum.component.GeneratorLevelComponent;
import com.alienshots.ludum.component.LeverStateComponent;
import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;

public class GeneratorActivatorSystem extends IteratingSystem {

    private final ComponentMapper<GeneratorLevelComponent> generatorLevelMapper;
    private final ComponentMapper<LeverStateComponent> leverStateMapper;
    private int previousLevel;

    public GeneratorActivatorSystem() {
        super(Family.all(GeneratorComponent.class).get());

        generatorLevelMapper = ComponentMapper.getFor(GeneratorLevelComponent.class);
        leverStateMapper = ComponentMapper.getFor(LeverStateComponent.class);
    }

    @Override
    protected void processEntity(Entity generator, float deltaTime) {
        GeneratorLevelComponent generatorLevel = generatorLevelMapper.get(generator);
        LeverStateComponent leverState = leverStateMapper.get(generator);

        if (generatorLevel.getLevel() == GeneratorLevelComponent.MAX_LEVEL) {
            if (previousLevel < GeneratorLevelComponent.MAX_LEVEL) {
                leverState.setChargePosition(false);
            } else if (leverState.isChargePosition()) {
                generatorLevel.setLevel(0);
                // TODO: do something, raise battery charge, some effect, whatever
            }
        }
        previousLevel = generatorLevel.getLevel();
    }
}
