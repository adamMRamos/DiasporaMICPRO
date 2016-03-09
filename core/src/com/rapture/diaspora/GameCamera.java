package com.rapture.diaspora;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.Vector2;

public class GameCamera extends OrthographicCamera
{
	
	private Vector2 velocity;
	private Vector2 target;

	public GameCamera(float width, float height, Vector2 cameraVelocity, Vector2 cameraTarget)
	{
		super(width, height);
		
		velocity = cameraVelocity;
		target = cameraTarget;
	}
	
	public GameCamera(int width, int height) 
	{
		super(width, height);
		velocity = new Vector2(0,0);
		target = new Vector2(0,0);
	}
	
	private void updateVelocity(float delta)
	{
		float targetDistance = Math.abs(target.cpy().dst(position.x, position.y));
		Vector2 direction = target.cpy().sub(position.x, position.y).nor();
		
		velocity.add(direction.scl(targetDistance));
		velocity.scl(delta);
	}
	
	public void updateGameCamera(float x, float y, float delta)
	{
		setTarget(x, y);
		
		updateVelocity(delta);
		
		translate(velocity.x, velocity.y);
		
		super.update();
	}
	
	//Getters & Setters
	public Vector2 getVelocity()
	{
		return velocity.cpy();
	}
	
	public Vector2 getTarget()
	{
		return target.cpy();
	}
	
	public void setVelocity(float x, float y)
	{
		velocity.set(x, y);
	}
	
	public void setTarget(float x, float y)
	{
		target.set(x, y);
	}
}
