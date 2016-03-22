package com.rapture.diaspora.gameobjects;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;

public class DiasporaPlanet extends DiasporaActor {

	public DiasporaPlanet(TextureRegion texture, float xCoord, float yCoord, float width, float height,
			float rotation) 
	{
		super(texture, xCoord, yCoord, width, height, rotation);
		mass = 1000;
	}

	@Override
	protected float updateRotation(float rotation) 
	{
		return rotation;
	}

	@Override
	protected float getThrust() 
	{
		return 0;
	}

	@Override
	protected Vector2 updateAcceleration(Vector2 acceleration) 
	{
		return acceleration;
	}

}
