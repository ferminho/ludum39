package com.alienshots.ludum.component;

import com.badlogic.ashley.core.Component;

public class DisplayComponent implements Component {

    public DisplayComponent(boolean visible) {
        this.visible = visible;
    }

    private boolean visible;

    public boolean isVisible() {
        return visible;
    }

    public void setVisible(boolean visible) {
        this.visible = visible;
    }
}
