package com.rapture.diaspora;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.utils.Array;
import com.rapture.diaspora.gameobjects.Collidable;
import com.rapture.diaspora.gameobjects.DiasporaPlayer;
import com.rapture.diaspora.gameobjects.GameActors;
import com.rapture.diaspora.helpers.CollisionHandler;
import com.rapture.diaspora.helpers.GamePool;

public class GameWorld 
{
	public DiasporaGameMaster master;
	
	public DiasporaPlayer player1;
	public GameActors actors;
	public GamePool pool;
	
	public GameWorld(DiasporaGameMaster gameMaster)
	{
		master = gameMaster;
	}
	
	public void initialize()
	{
		actors = master.actors;
		player1 = actors.player1;
		
		pool = master.pool;
		master.enemySpawnHandler.spawnEnemy(player1);
	}
	
	private void updateEnemySpawnHandler(float delta)
	{
		master.enemySpawnHandler.update(player1, delta);
	}
	
	private void updatePool(float delta)
	{
		pool.update(delta);
	}
	
	private void updatePlayer(float delta)
	{
		if (player1.alive) {
			player1.update(delta);
		}
	}
	
	private Array<Collidable> getCollidingActors()
	{
		Array<Collidable> collidingActors = new Array<Collidable>();
		
		collidingActors.add(player1);
		
		collidingActors.addAll(pool.getActiveActors());
		
		return collidingActors;
	}
	
	private void updateCollidables()
	{
		CollisionHandler.updateActorCollisions(getCollidingActors());
	}
	
	public void update(float delta)
	{
		Gdx.app.log("GameWorld", "update(" + delta + ") called");
		
		updatePool(delta);
		
		updatePlayer(delta);
		
		updateCollidables();
		
		updateEnemySpawnHandler(delta);
		
		Gdx.app.log("GameWorld", "Update() called    actor1: " + player1.getPosition());
		Gdx.app.log("GameWorld", "Update() called    pool actors: " + pool.getActiveActors().size);
		Gdx.app.log("GameWorld", "Update() called    keys: " + master.keyLog);
	}
}
