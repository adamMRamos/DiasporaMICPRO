package com.rapture.diaspora.gameobjects;

import com.badlogic.gdx.math.Circle;
import com.rapture.diaspora.helpers.ActorIDHandler;

public class DiasporaBody 
{
	private Circle boundingCircle;
	
	private Integer id;
	
	private float sizeX;
	private float sizeY;
	private float scale;
	
	public DiasporaBody(float xCoord, float yCoord, float sizeX, float sizeY, float radius, float scale)
	{
		boundingCircle = new Circle(xCoord, yCoord, radius*scale);
		
		id = ActorIDHandler.generateNewID();
		
		this.sizeX = sizeX;
		this.sizeY = sizeY;
		this.scale = scale;
	}
	
	public void setPosition(float x, float y)
	{
		boundingCircle.setPosition(x + (sizeX*scale), y + (sizeY*scale));
	}
	
	//Getters setters
	public float getX()
	{
		return boundingCircle.x;
	}
	
	public float getY()
	{
		return boundingCircle.y;
	}
	
	public float getArea()
	{
		return boundingCircle.area();
	}
	
	public float getCircumference()
	{
		return boundingCircle.circumference();
	}
	
	public Circle getBoundingCircle()
	{
		return boundingCircle;
	}
	
	public void setID(Integer newID)
	{
		id = newID;
	}
	
	public Integer getID()
	{
		return id;
	}
}
