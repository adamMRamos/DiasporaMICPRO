package com.rapture.diaspora.gameobjects;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;

public abstract class DiasporaActor extends DiasporaSprite implements Collidable
{
	private static final float FRICTION = 0.02f;
	
	public DiasporaBody body;
	protected float mass;
	
	protected Vector2 acceleration;
	protected Vector2 velocity;
	
	protected Vector2 vectorOfThrust;
	
	public boolean alive;
	
	public DiasporaActor(TextureRegion texture, float xCoord, float yCoord, float width, float height, float rotation) 
	{
		super(texture, xCoord, yCoord, width, height, rotation);
		
		acceleration = new Vector2(0,0);
		velocity = new Vector2(0,0);
		
		vectorOfThrust = new Vector2(0,0);
		
		body = new DiasporaBody(xCoord, yCoord, width/2, height/2, width/2, scaleX);
		mass = 1;
		
		alive = true;
	}

	@Override()
	public DiasporaBody getBody()
	{
		return body;
	}
	
	@Override
	public void collide(Collidable collider, Vector2 finalVelocity) 
	{
		velocity = finalVelocity.cpy();
	}
	
	protected abstract float updateRotation(float rotation);
	
	protected abstract Vector2 updateVectorOfThrust(Vector2 vectorOfThrust);
	
	protected abstract Vector2 updateAcceleration(Vector2 acceleration);
	
	protected void updateVelocity(float delta)
	{
		velocity.add(acceleration.cpy().scl(delta));
		
		//Add Friction
		velocity.add(velocity.cpy().scl(FRICTION).rotate(180f));
		//velocity.limit(800);
	}
	
	protected void updatePosition(float delta)
	{
		position.add(velocity.cpy().scl(delta));
	}
	
	protected void updateBody()
	{
		body.setPosition(position.x, position.y);
	}
	
	public void update(float delta)
	{
		rotation = updateRotation(rotation);
		
		super.update(delta);
		
		vectorOfThrust = updateVectorOfThrust(vectorOfThrust);
		acceleration = updateAcceleration(acceleration);
		
		updateVelocity(delta);
		updatePosition(delta);
		
		updateBody();
	}
	
	//Getters & setters
	public Vector2 getVectorOfRotation()
	{
		return vectorOfRotation.cpy();
	}
	
	public Vector2 getAcceleration()
	{
		return acceleration.cpy();
	}
	
	public Vector2 getVelocity()
	{
		return velocity.cpy();
	}
	
	public void setMass(float newMass)
	{
		mass = newMass;
	}
	
	public float getMass()
	{
		return mass;
	}
}
