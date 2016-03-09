package com.rapture.diaspora.helpers;

import com.badlogic.gdx.utils.Array;

public class ActorIDHandler 
{
	public static Array<Integer> ACTOR_ID_ARRAY = new Array<Integer>();
	
	public static Integer generateNewID()
	{
		if (ACTOR_ID_ARRAY.size < 1) ACTOR_ID_ARRAY.add(0);
		else ACTOR_ID_ARRAY.add(ACTOR_ID_ARRAY.peek() + 1);
		
		
		return ACTOR_ID_ARRAY.peek();
	}
	
	public static void removeID(int id)
	{
		ACTOR_ID_ARRAY.removeValue(id, true);
	}
}
