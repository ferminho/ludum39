package com.alienshots.ludum.component;

import com.badlogic.ashley.core.Component;

public class PlayerEventComponent implements Component {
    private boolean userDied;

    public PlayerEventComponent(boolean userDied) {
        this.userDied = userDied;
    }

    public boolean isUserDied() {
        return userDied;
    }

    public void setUserDied(boolean userDied) {
        this.userDied = userDied;
    }
}
