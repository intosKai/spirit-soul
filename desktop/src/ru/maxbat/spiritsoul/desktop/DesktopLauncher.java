package ru.maxbat.spiritsoul.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import ru.maxbat.spiritsoul.SpiritSoulGame;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.width = 1280;
		config.height = 720;
		config.backgroundFPS = 60;
		config.foregroundFPS = 60;
		new LwjglApplication(new SpiritSoulGame(), config);
	}
}
