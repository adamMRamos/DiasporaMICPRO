package com.rapture.diaspora.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.rapture.diaspora.RDGame;
import com.rapture.diaspora.RaptureDiasporaGame;

public class DesktopLauncher 
{
	public static void main (String[] arg) 
	{
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		
		config.title = "Diaspora";
		config.width = 1800;
		config.height = 1200;
		
		new LwjglApplication(new RDGame(), config);
	}
}
