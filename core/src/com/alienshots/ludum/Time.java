package com.alienshots.ludum;

import com.badlogic.gdx.Gdx;

import java.util.ArrayList;
import java.util.List;

public class Time {

    public static final int DEFAULT_GAME_SPEED_IN_MS = 1000;
    public static final float GAME_SPEED_INCREASE = 0.8f;

    public static final Time instance = new Time();

    private int elapsedTimeInMs = 0;
    private int gameSpeedInMs = DEFAULT_GAME_SPEED_IN_MS;

    public void increaseSpeed() {
        gameSpeedInMs = (int) ((float)gameSpeedInMs * GAME_SPEED_INCREASE);
    }

    public boolean timeIsMoving() {
        elapsedTimeInMs += Gdx.graphics.getDeltaTime() * 1000;

        if (elapsedTimeInMs >= getGameSpeedInMs()) {
            elapsedTimeInMs -= getGameSpeedInMs();
            return true;
        } else return false;
    }
    private static final List<Timer> timers = new ArrayList<>();

    public static Timer newTimer(int tickRate) {
        Timer newTimer = new Timer(tickRate);
        timers.add(newTimer);
        return newTimer;
    }

    public static void tickTimers()
    {
        timers.forEach(Timer::moveTime);
        SoundManager.instance.play(SoundManager.SFX_TICK);
    }

    public int getGameSpeedInMs() {
        return gameSpeedInMs;
    }

    public static class Timer {
        private int tickRate;
        private int elapsed;

        public Timer(int tickRate) { this.setTickRate(tickRate); }

        public void reset() { elapsed = 0; }

        public void moveTime() {
            elapsed = (elapsed + 1) % getTickRate();
        }

        public boolean isTicking() {
            return elapsed == 0;
        }

        public int getTickRate() {
            return tickRate;
        }

        public void setTickRate(int tickRate) {
            this.tickRate = tickRate;
        }
    }

    public static class SpanTimer {
        private int span;
        private int accumulatedTime;
        private boolean finished;

        public SpanTimer(int span) {
            this.span = span;
        }

        public void update() {
            accumulatedTime += Gdx.graphics.getDeltaTime() * 1000f;
            if (accumulatedTime >= span)
                finished = true;
        }

        public boolean isFinished() {
            return finished;
        }
    }

    public static class BlinkingTimer {
        private int blinkIntervalMs;
        private int maxBlinks;
        private boolean initialState;
        private int accumulatedTime;
        private boolean blinkState;
        private boolean finished;

        /**
         Timer finishes after x blinks (state changes); max=2 initial=true would be true,false, then finish with true
         @param maxBlinks <=0 : blink forever
          */
        public BlinkingTimer(int blinkIntervalMs, int maxBlinks, boolean initialState) {
            this.blinkIntervalMs = blinkIntervalMs;
            this.maxBlinks = maxBlinks;
            this.initialState = initialState;
            this.blinkState = initialState;
        }

        public void update() {
            accumulatedTime += Gdx.graphics.getDeltaTime() * 1000f;
            int blinks;
            if (maxBlinks > 0 && accumulatedTime >= maxBlinks * blinkIntervalMs) {
                finished = true;
                blinks = maxBlinks;
            } else {
                blinks = accumulatedTime / blinkIntervalMs;
            }
            boolean odd = blinks % 2 != 0;
            blinkState = initialState ^ odd; // when odd blinks, opposite to initial, if not, same as initial
        }

        public boolean isBlinkState() {
            return blinkState;
        }

        public boolean isFinished() {
            return finished;
        }
    }
}
