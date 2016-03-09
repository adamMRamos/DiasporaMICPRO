package com.rapture.diaspora.gameobjects;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;

public class DiasporaSprite 
{
	protected TextureRegion texture;
	
	protected Vector2 position;
	protected Vector2 vectorOfRotation;
	
	protected float width;
	protected float height;
	protected float scaleX;
	protected float scaleY;
	protected float originX;
	protected float originY;
	protected float rotation;
	
	public DiasporaSprite(TextureRegion texture, float xCoord, float yCoord, float width, float height, float rotation)
	{
		this.texture = texture;
		
		position = new Vector2(xCoord, yCoord);
		vectorOfRotation = new Vector2(0,0);
		
		this.width = width;
		this.height = height;
		scaleY = 1;
		scaleX = 1;
		originX = width/2;
		originY = height/2;
		this.rotation = rotation;
	}

	protected void updateVectorOfRotation()
	{
		vectorOfRotation.x = MathUtils.sinDeg(rotation);
		vectorOfRotation.y = MathUtils.cosDeg(rotation);
	}
	
	public void update(float delta)
	{
		updateVectorOfRotation();
	}
	
	public void render(SpriteBatch batch)
	{
		batch.draw(texture, position.x, position.y, originX, originY, width, height, scaleX, scaleY, vectorOfRotation.angle() + 90);
	}
	
	//GETTERS SETTERS
	
	public Vector2 getPosition()
	{
		return position.cpy();
	}
	
	public Vector2 getNose()
	{
		return new Vector2(position.x + (getWidth()/2), position.y + getHeight());
	}
	
	public Vector2 getCenter()
	{
		return new Vector2(position.x + (getWidth()/2), position.y + (getHeight()/2));
	}
	
	public float getRotation()
	{
		return rotation;
	}

	public float getWidth()
	{
		return width * scaleX;
	}
	
	public float getHeight()
	{
		return height * scaleY;
	}
	
	public float getX()
	{
		return position.x;
	}
	
	public float getY()
	{
		return position.y;
	}
}
