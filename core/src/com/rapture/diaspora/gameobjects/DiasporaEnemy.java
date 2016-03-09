package com.rapture.diaspora.gameobjects;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Pool.Poolable;

public class DiasporaEnemy extends DiasporaActor implements Poolable
{

	private static final float THRUST = 100;
	private static final float ACT_CYCLE_LIMIT = 10;
	private static final float MAX_OFFSCREEN_TIME_LIMIT = 10;
	private static final float MAX_TARGET_DISTANCE = 100;
	
	private Vector2 targetVector;
	private float targetRotation;
	
	private float offscreenTimer;
	private float actCycle;
	
	private DiasporaPlayer player1;
	
	public DiasporaEnemy(TextureRegion texture, float xCoord, float yCoord, float width, float height, float rotation) 
	{
		super(texture, xCoord, yCoord, width, height, rotation);
		
		targetVector = new Vector2(0,0);
		targetRotation = 0;
		
		offscreenTimer = 1;
		actCycle = 0;
		
		alive = false;
	}
	
	public void intialize(DiasporaPlayer player1, float x, float y)
	{
		this.player1 = player1;
		
		position.set(x, y);
		body.setPosition(position.x, position.y);
		
		velocity.set(0,0);
		
		scaleX = 1;
		scaleY = 1;
		rotation = 0;
		
		offscreenTimer = 1;
		actCycle = 0;
		alive = true;
	}
	
	private void findTargetRotation()
	{
		targetRotation = MathUtils.random(361);
	}
	
	private void findTargetVector()
	{
		targetVector.set(1, 1);
		targetVector.setAngle(targetRotation);
		targetVector.scl(MAX_TARGET_DISTANCE + position.dst(0, 0));
	}
	
	private void updateOffscreenStatus()
	{
		offscreenTimer %= MAX_OFFSCREEN_TIME_LIMIT;
		
		if (offscreenTimer < 1) alive = false;
	}
	
	private void updateOffscreenTimer(float delta)
	{
		if (position.dst(player1.getPosition()) > 1000) offscreenTimer += delta;
	}
	
	private void updateOffscreen(float delta)
	{
		updateOffscreenTimer(delta);
		updateOffscreenStatus();
	}
	
	private void updateActCycle(float delta)
	{
		actCycle += delta;
		actCycle %= ACT_CYCLE_LIMIT;
	}
	
	public void act(float delta)
	{
		updateActCycle(delta);
		
		if (actCycle < 1) {
			findTargetRotation();
			findTargetVector();
		}
	}
	
	private float hasThrust()
	{
		return MathUtils.clamp(targetVector.cpy().dst(position), 0, 1);
	}
	
	@Override
	protected float updateRotation(float rotation) 
	{
		float rotationDirection = MathUtils.clamp(targetRotation - rotation, -1, 1);
		rotation += rotationDirection * 0.5f;
		return rotation;
	}
	
	@Override
	protected Vector2 updateVectorOfThrust(Vector2 vectorOfThrust) 
	{
		vectorOfThrust = vectorOfRotation.cpy().scl(THRUST);
		return vectorOfThrust;
	}

	@Override
	protected Vector2 updateAcceleration(Vector2 acceleration) 
	{
		acceleration.add(vectorOfThrust.cpy());
		acceleration.scl(hasThrust());
		acceleration.limit(300);
		return acceleration;
	}

	@Override
	public void update(float delta)
	{
		act(delta);
		
		super.update(delta);
		
		updateOffscreen(delta);
		//logValues();
	}
	
	private void logValues()
	{
		Gdx.app.log("Enemy", "logValues() called    actCycle: " + actCycle);
		Gdx.app.log("Enemy", "logValues() called    targetRotation: " + targetRotation);
		Gdx.app.log("Enemy", "logValues() called    rotation: " + rotation);
		Gdx.app.log("Enemy", "logValues() called    targetVector: " + targetVector);
		Gdx.app.log("Enemy", "logValues() called    hasThrust(): " + hasThrust());
		Gdx.app.log("Enemy", "logValues() called    vectorOfThrust: " + vectorOfThrust);
		Gdx.app.log("Enemy", "logValues() called    acceleration: " + acceleration);
		Gdx.app.log("Enemy", "logValues() called    velocity: " + velocity);
		Gdx.app.log("Enemy", "logValues() called    position: " + position);
	}

	@Override
	public void reset() 
	{
		body.setPosition(0,0);
		position.set(0,0);
		velocity.set(0,0);
		acceleration.set(0,0);
		rotation = 0;
		alive = false;
	}

}
