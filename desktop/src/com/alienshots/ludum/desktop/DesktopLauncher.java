package com.alienshots.ludum.desktop;

import com.alienshots.ludum.PowerCharge;
import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();

		config.setFromDisplayMode(LwjglApplicationConfiguration.getDesktopDisplayMode());
		config.fullscreen = false;
		config.width = 1280;
		config.height = 720;
		config.resizable = true;
		config.vSyncEnabled = true;

		new LwjglApplication(new PowerCharge(), config);
	}
}
