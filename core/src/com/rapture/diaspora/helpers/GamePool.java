package com.rapture.diaspora.helpers;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.Pool;
import com.rapture.diaspora.gameobjects.DiasporaActor;
import com.rapture.diaspora.gameobjects.DiasporaEnemy;
import com.rapture.diaspora.gameobjects.DiasporaPlayer;
import com.rapture.diaspora.gameobjects.DiasporaProjectile;

public class GamePool 
{
	private final Pool<DiasporaEnemy> enemyPool = new Pool<DiasporaEnemy>()
		{
			@Override
			protected DiasporaEnemy newObject()
			{
				return new DiasporaEnemy(AssetLoader.enemy, 0, 0, 128f, 128f, 0f);
			}
		};
	
	private final Pool<DiasporaProjectile> projectilePool = new Pool<DiasporaProjectile>() 
		{
			@Override
			protected DiasporaProjectile newObject() 
			{
				return new DiasporaProjectile(AssetLoader.projectile, 64, 64);
			}
		};
	
	public final Array<DiasporaActor> activeActors;
		
	public GamePool()
	{
		activeActors = new Array<DiasporaActor>();
	}
	
	public DiasporaProjectile spawnProjectile(DiasporaActor parent)
	{
		DiasporaProjectile projectile = projectilePool.obtain();
		projectile.intialize(parent);
		
		activeActors.add(projectile);
		
		return projectile;
	}

	public DiasporaEnemy spawnEnemy(DiasporaPlayer player1, Vector2 spawnPosition)
	{
		DiasporaEnemy enemy = enemyPool.obtain();
		enemy.intialize(player1, spawnPosition.x, spawnPosition.y);

		activeActors.add(enemy);
		
		return enemy;
	}
	
	public DiasporaEnemy spawnEnemy(DiasporaPlayer player1)
	{
		DiasporaEnemy enemy = enemyPool.obtain();
		enemy.intialize(player1, 700, 700);

		activeActors.add(enemy);
		
		return enemy;
	}
	
	private boolean isEnemy(DiasporaActor actor)
	{
		return actor instanceof DiasporaEnemy;
	}
	
	private boolean isProjectile(DiasporaActor actor)
	{
		return actor instanceof DiasporaProjectile;
	}
	
	private void disableActor(DiasporaActor actor, int i)
	{
		activeActors.removeIndex(i);
		
		if (isEnemy(actor))
			enemyPool.free((DiasporaEnemy)actor);
		else if (isProjectile(actor))
			projectilePool.free((DiasporaProjectile)actor);
	}
	
	public void update(float delta)
	{
		for (int i = 0; i < activeActors.size; i++) {
			DiasporaActor actor = activeActors.get(i);
			
			if (actor.alive) {
				actor.update(delta);
			}
			else {
				disableActor(actor, i);
			}
		}
	}
	
	//Getters & setters
	public Array<DiasporaActor> getActiveActors()
	{
		return activeActors;
	}
}
