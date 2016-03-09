package com.rapture.diaspora;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.rapture.diaspora.helpers.AssetLoader;
import com.rapture.diaspora.screens.GameScreen;

public class RDGame extends Game
{
	
	@Override
	public void create() 
	{
		Gdx.app.log("RDGame", "create()");
		AssetLoader.load();
		setScreen(new GameScreen());
	}

}
