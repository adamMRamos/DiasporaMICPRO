package com.rapture.diaspora.gameobjects;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.rapture.diaspora.DiasporaGameMaster;

public abstract class DiasporaFightingActor extends DiasporaActor
{
	protected DiasporaGameMaster master;
	
	private float accelerationLimit;
	
	protected float maximumHealth;
	protected float currentHealth;
	
	public DiasporaFightingActor(TextureRegion texture, float xCoord, float yCoord, float width, float height, 
			float rotation, float maxHealth, float accelLimit) 
	{
		super(texture, xCoord, yCoord, width, height, rotation);
		
		accelerationLimit = accelLimit;
		maximumHealth = maxHealth;
		currentHealth = maxHealth;
	}
	
	protected void updateHealthStatus()
	{
		if (currentHealth < 0) 
			alive = false;
	}
	
	protected abstract float hasThrust();
	
	@Override
	public void collide(Collidable collider, Vector2 finalVelocity) 
	{
		float dst = getVelocity().dst(finalVelocity.cpy());
		
		Gdx.app.log("FightingActor", "collide() called    force of impact " + dst);
		
		currentHealth -= dst;
		
		super.collide(collider, finalVelocity);
	}
	
	@Override
	protected Vector2 updateAcceleration(Vector2 acceleration) 
	{
		acceleration.add(vectorOfThrust.cpy());
		acceleration.scl(hasThrust());
		acceleration.limit(accelerationLimit);
		
		return acceleration;
	}
	
	@Override
	public void update(float delta)
	{
		super.update(delta);
		
		updateHealthStatus();
	}
}
