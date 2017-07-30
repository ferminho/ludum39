package com.alienshots.ludum;

import com.badlogic.gdx.Gdx;
import jdk.nashorn.internal.objects.annotations.Constructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

public class Time {

    public static final int DEFAULT_GAME_SPEED_IN_MS = 1000;

    public static final Time instance = new Time();

    private int elapsedTimeInMs = 0;
    private int gameSpeedInMs = DEFAULT_GAME_SPEED_IN_MS;

    public boolean timeIsMoving() {
        elapsedTimeInMs += Gdx.graphics.getDeltaTime() * 1000;

        if (elapsedTimeInMs >= gameSpeedInMs) {
            elapsedTimeInMs -= gameSpeedInMs;
            return true;
        } else return false;
    }
    private static final List<Timer> timers = new ArrayList<>();

    public static Timer newTimer(int tickRate) {
        Timer newTimer = new Timer(tickRate);
        timers.add(newTimer);
        return newTimer;
    }

    public static void tickTimers() {
        timers.forEach(Timer::moveTime);
    }

    public static class Timer {
        @Getter
        @Setter
        private int tickRate;
        private int elapsed;

        public Timer(int tickRate) { this.tickRate = tickRate; }

        public void reset() { elapsed = 0; }

        public void moveTime() {
            elapsed = (elapsed + 1) % tickRate;
        }

        public boolean isTicking() {
            return elapsed == 0;
        }
    }
}
