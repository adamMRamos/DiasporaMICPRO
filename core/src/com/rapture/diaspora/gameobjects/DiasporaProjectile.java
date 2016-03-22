package com.rapture.diaspora.gameobjects;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Pool.Poolable;

public class DiasporaProjectile extends DiasporaFightingActor implements Poolable
{
	private static final float THRUST = 100;

	private Vector2 originPosition;
	
	public DiasporaProjectile(TextureRegion texture, float width, float height) 
	{
		super(texture, 0, 0, width, height, 0, 100, 1000);

		originPosition = new Vector2(0, 0);
		
		setMass(0.05f);
		
		alive = false;
	}

	public void intialize(DiasporaActor parent)
	{
		body.setID(parent.getBody().getID());
		
		originPosition.set(parent.getX(), parent.getY());
		position.set(parent.getCenter().x - width/2, parent.getCenter().y - height/2);
		body.setPosition(getPosition().x, getPosition().y);
		
		velocity.set(parent.getVelocity().add(parent.getVectorOfRotation().scl(1000)));
		
		rotation = parent.getRotation();
		
		scaleX = 0.25f;
		scaleY = 0.25f;
		
		alive = true;
	}
	
	public void checkDistance()
	{
		if (Math.abs(position.cpy().dst(originPosition.cpy())) >= 5000) alive = false;
	}
	
	@Override
	protected float updateRotation(float rotation) 
	{
		return rotation;
	}
	
	@Override
	protected float getThrust()
	{
		return THRUST;
	}
	
	@Override
	public void reset() 
	{
		body.setPosition(0,0);
		originPosition.set(0,0);
		position.set(0,0);
		velocity.set(0,0);
		acceleration.set(0,0);
		rotation = 0;
		currentHealth = maximumHealth;
		alive = false;
	}

	@Override
	public void update(float delta)
	{
		super.update(delta);
		
		checkDistance();
	}
	
	//Getters && Setters
	public Vector2 getOriginPosition()
	{
		return originPosition.cpy();
	}

	@Override
	protected float hasThrust() 
	{
		return 1;
	}
}
