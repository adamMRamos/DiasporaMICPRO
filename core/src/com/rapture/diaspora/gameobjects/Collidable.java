package com.rapture.diaspora.gameobjects;

import com.badlogic.gdx.math.Vector2;

public interface Collidable 
{
	public void collide(Collidable collider, Vector2 finalVelocity);
	
	public DiasporaBody getBody();
}
