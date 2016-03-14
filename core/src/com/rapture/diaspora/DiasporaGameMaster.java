package com.rapture.diaspora;

import com.rapture.diaspora.gameobjects.DiasporaPlayer;
import com.rapture.diaspora.gameobjects.GameActors;
import com.rapture.diaspora.helpers.AssetLoader;
import com.rapture.diaspora.helpers.EnemySpawnHandler;
import com.rapture.diaspora.helpers.GamePool;
import com.rapture.diaspora.helpers.KeyLogHandler;

public class DiasporaGameMaster 
{
	public EnemySpawnHandler enemySpawnHandler;
	public GameWorld world;
	public GameRenderer renderer;
	public GamePool pool;
	public GameActors actors;
	public KeyLogHandler keyLog;
	
	public DiasporaGameMaster(int screenWidth, int screenHeight)
	{
		keyLog = new KeyLogHandler();
		pool = new GamePool();
		actors = new GameActors(new DiasporaPlayer(this, AssetLoader.testImg, 900f, 900f, 100f, 100f, 0f, 3000, 800));
		enemySpawnHandler = new EnemySpawnHandler(this, 1000);
		
		world = new GameWorld(this);
		renderer = new GameRenderer(this, screenWidth, screenHeight);
	}
	
	public void initialize()
	{
		world.initialize();
		renderer.initialize();
	}
	
}
