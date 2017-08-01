package com.alienshots.ludum.component;

import com.alienshots.ludum.Time;
import com.badlogic.ashley.core.Component;
import com.badlogic.gdx.Gdx;

public class PlayerComponent implements Component {
    private Jump jump = Jump.NOT_JUMPING;

    public boolean isJumping() {
        return jump != Jump.NOT_JUMPING;
    }

    public boolean jumpIsOver() {
        return jump.isOver();
    }

    public void startJump() {
        jump = new Jump();
    }

    public void stopJump() {
        jump = Jump.NOT_JUMPING;
    }

    public static class Jump {
        private static final Jump NOT_JUMPING = new Jump();

        private static final float JUMP_DURATION_TICKS = 0.9f;

        int jumpElapsedInMs = 0;

        public boolean isOver() {
            jumpElapsedInMs += Gdx.graphics.getDeltaTime() * 1000;

            if (jumpElapsedInMs >= Time.instance.getGameSpeedInMs() * JUMP_DURATION_TICKS) {
                jumpElapsedInMs -= jumpElapsedInMs;
                return true;
            } else return false;
        }
    }
}
