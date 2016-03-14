package com.rapture.diaspora.helpers;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.rapture.diaspora.DiasporaGameMaster;
import com.rapture.diaspora.gameobjects.DiasporaPlayer;

public class EnemySpawnHandler 
{
	private static final float TIMER_LIMIT = 5;
	
	private DiasporaGameMaster master;
	private float spawnTimer;
	private float spawnRadius;
	
	private boolean spawning;
	private boolean spawnedEnemy;
	
	public EnemySpawnHandler(DiasporaGameMaster gameMaster, float enemySpawnRadius)
	{
		master = gameMaster;
		spawnTimer = 0;
		spawnRadius = enemySpawnRadius;
		spawnedEnemy = false;
		spawning = false;
	}
	
	public void spawnRandomEnemy(DiasporaPlayer player1)
	{
		float randomAngle = MathUtils.random()*MathUtils.PI*2;
		float radius = spawnRadius;
		
		Vector2 spawnPosition = new Vector2(master.actors.player1.getX() + MathUtils.sinDeg(randomAngle)*radius, 
				master.actors.player1.getY() + MathUtils.cosDeg(randomAngle)*radius);
		
		master.pool.spawnEnemy(player1, master, spawnPosition);
	}
	
	public void spawnEnemy(DiasporaPlayer player1)
	{
		master.pool.spawnEnemy(player1, master);
	}
	
	private void updateSpawning()
	{
		if (spawnTimer < 1) spawning = true;
		else {
			spawning = false;
			spawnedEnemy = false;
		}
	}
	
	public void update(DiasporaPlayer player1, float delta)
	{
		spawnTimer += delta;
		spawnTimer %= TIMER_LIMIT;
		
		updateSpawning();
		
		if (spawning && !spawnedEnemy) {
			spawnRandomEnemy(player1);
			spawnedEnemy = true;
		}
		
		Gdx.app.log("EnemySpawnHandler", "update() called    timer: " + spawnTimer);
	}
}
