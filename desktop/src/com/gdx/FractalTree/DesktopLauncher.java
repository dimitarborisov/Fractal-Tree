package com.gdx.FractalTree;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.width = 700;
		config.height = 500;
		config.resizable = false;
		config.samples = 4;
		config.foregroundFPS = 30;
		new LwjglApplication(new GdxTree(), config);
	}
}
