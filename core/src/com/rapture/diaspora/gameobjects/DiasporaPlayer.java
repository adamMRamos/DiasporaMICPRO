package com.rapture.diaspora.gameobjects;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.rapture.diaspora.DiasporaGameMaster;

public class DiasporaPlayer extends DiasporaActor 
{
	private static final float THRUST = 100;
	
	private DiasporaGameMaster master;
	
	private int directionOfThrust;
	private boolean hasFired;
	private boolean firing;
	
	public DiasporaPlayer(DiasporaGameMaster master, TextureRegion textureRegion, float x, float y, float width, float height, float rotation)
	{
		super(textureRegion, x, y, width, height, rotation);
		
		this.master = master;
		
		directionOfThrust = 0;
		hasFired = false;
		firing = false;
	}
	
	private void updateFiring()
	{
		if (master.keyLog.hasKey(Keys.SPACE)) {
			firing = true;
		}
		else {
			firing = false;
			hasFired = false;
		}
	}
	
	private void fireProjectile()
	{
		if (firing && !hasFired) {
			master.pool.spawnProjectile(this);
			hasFired = true;
		}
	}
	
	private void updateDirection()
	{
		int up = 0;
		
		if (master.keyLog.hasKey(Keys.W)) {
			up += 1;
		}
		
		if (master.keyLog.hasKey(Keys.S)) {
			up -= 1;
		}
		
		directionOfThrust = up;
	}
	
	@Override
	protected float updateRotation(float rotation)
	{
		if (master.keyLog.hasKey(Keys.A)) {
			rotation -= 1;
			if (rotation < 0) rotation = 360;
		}
		if (master.keyLog.hasKey(Keys.D)) {
			rotation += 1;
			if (rotation > 360) rotation = 0;
		}
		
		return rotation;
	}
	
	@Override
	protected Vector2 updateVectorOfThrust(Vector2 vectorOfThrust)
	{
		vectorOfThrust = vectorOfRotation.cpy().scl(THRUST * directionOfThrust);
		return vectorOfThrust;
	}
	
	private float hasThrust()
	{
		return Math.abs(directionOfThrust);
	}

	@Override
	protected Vector2 updateAcceleration(Vector2 acceleration)
	{
		acceleration.add(vectorOfThrust.cpy());
		acceleration.scl(hasThrust());
		acceleration.limit(800);
		
		return acceleration;
	}
	
	public void update(float delta)
	{
		updateDirection();
		
		super.update(delta);
		
		updateFiring();
		fireProjectile();
		
		logValues();
	}
	
	private void logValues()
	{
		Gdx.app.log("Player", "logValues() called    direction: " + directionOfThrust);
		Gdx.app.log("Player", "logValues() called    rotation: " + rotation);
		Gdx.app.log("Player", "logValues() called    vectorOfRotation: " + vectorOfRotation);
		Gdx.app.log("Player", "logValues() called    vectorOfRotation Angle: " + vectorOfRotation.angle());
		Gdx.app.log("Player", "logValues() called    vectorOfThrust: " + vectorOfThrust);
		Gdx.app.log("Player", "logValues() called    acceleration: " + acceleration);
		Gdx.app.log("Player", "logValues() called    velocity: " + velocity);
		Gdx.app.log("Player", "logValues() called    position: " + position);
	}

	@Override
	public void collide(Collidable collider, Vector2 finalVelocity) 
	{
		super.collide(collider, finalVelocity);
		
		Gdx.app.log("Player", "hit() called    HIT!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
	}
}
