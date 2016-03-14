package com.rapture.diaspora.helpers;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Intersector;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;
import com.rapture.diaspora.gameobjects.Collidable;
import com.rapture.diaspora.gameobjects.DiasporaActor;
import com.rapture.diaspora.gameobjects.DiasporaBody;
import com.rapture.diaspora.gameobjects.DiasporaPlayer;
import com.rapture.diaspora.gameobjects.DiasporaProjectile;

public class CollisionHandler 
{

	public static void updateActorCollisions(Array<Collidable> collidables)
	{
		int size = collidables.size;
		
		boolean[] collidedActorTable = new boolean[(size*2)-1];
		
		for (int i = 0; i < size; i++) {
			
			Collidable actorA = collidables.get(i);
			
			for (int j = 0; j < size; j++) {
				
				if (i != j) {
					Collidable actorB = collidables.get(j);
					
					if(!collidedActorTable[(i+j)] && detectCollision(actorA.getBody(), actorB.getBody())) {
						
						collidedActorTable[i+j] = true;
						
						if (actorA instanceof DiasporaActor && actorB instanceof DiasporaActor) {
							handleActorCollision((DiasporaActor)actorA, (DiasporaActor)actorB);
						}
					}
				}
			}
		}
	}
	
	public static boolean detectCollision(DiasporaBody body1, DiasporaBody body2)
	{
		return Intersector.overlaps(body1.getBoundingCircle(), body2.getBoundingCircle()) 
				&& (body1.getID() != body2.getID());
	}
	
	public static void handleActorCollision(DiasporaActor actorA, DiasporaActor actorB)
	{
		Vector2 actorACurrentVelocity = actorA.getVelocity();
		Vector2 actorBCurrentVelocity = actorB.getVelocity();
		
		//calculate first term of equation for actorB
		Vector2 firstVectorB = actorACurrentVelocity.cpy().scl((2*actorA.getMass()) /
				(actorA.getMass()+actorB.getMass()));
		Vector2 secondVectorB = actorBCurrentVelocity.cpy().scl((actorA.getMass()-actorB.getMass()) /
				(actorA.getMass()+actorB.getMass()));
		
		//calculate final velocity for actorB
		Vector2 actorBFinalVelocity = firstVectorB.sub(secondVectorB);
		
		//calculate first term of equation for actorA
		Vector2 firstVectorA = actorACurrentVelocity.cpy().scl((actorA.getMass()-actorB.getMass()) /
				(actorA.getMass()+actorB.getMass()));
		Vector2 secondVectorA = actorBCurrentVelocity.cpy().scl((2*actorB.getMass()) /
				(actorA.getMass()+actorB.getMass()));
			
		//calculate final velocity for actorA
		Vector2 actorAFinalVelocity = firstVectorA.add(secondVectorA);
				
		actorA.collide(actorB, actorAFinalVelocity);
		actorB.collide(actorA, actorBFinalVelocity);
	}
	
}
