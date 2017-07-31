package com.alienshots.ludum.component;

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

        private static final int JUMP_DURATION_IN_MS = 1000;

        int jumpElapsedInMs = 0;

        public boolean isOver() {
            jumpElapsedInMs += Gdx.graphics.getDeltaTime() * 1000;

            if (jumpElapsedInMs >= JUMP_DURATION_IN_MS) {
                jumpElapsedInMs -= jumpElapsedInMs;
                return true;
            } else return false;
        }
    }
}
