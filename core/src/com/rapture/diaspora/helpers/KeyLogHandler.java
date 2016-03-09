package com.rapture.diaspora.helpers;

import com.badlogic.gdx.utils.Array;

public class KeyLogHandler 
{
	private Array<Integer> keysHit;
	
	public KeyLogHandler()
	{
		keysHit = new Array<Integer>();
	}
	
	public void addKey(int key)
	{
		keysHit.add(key);
	}
	
	public void removeKey(int key)
	{
		keysHit.removeValue(key, true);
	}
	
	public boolean hasKey(int key)
	{
		return keysHit.contains(key, true);
	}
	
	private String getKeyString(int key)
	{
		String keyString = "";
		
		switch (key){
			case 19:
				keyString += "UP";
				break;
			case 20:
				keyString += "DOWN";
				break;
			case 21:
				keyString += "LEFT";
				break;
			case 22:
				keyString += "RIGHT";
				break;
			case 29:
				keyString += "A";
				break;
			case 32:
				keyString += "D";
				break;
			case 33:
				keyString += "E";
				break;
			case 45:
				keyString += "Q";
				break;	
			case 47:
				keyString += "S";
				break;
			case 51:
				keyString += "W";
				break;
			case 62:
				keyString += "SPACE";
				break;
			default:
				keyString += key;
		}
		return keyString;
	}
	
	public String toString()
	{
		String keys = "";
		
		for (int key : keysHit) keys += getKeyString(key) + " ";
		
		return keys;
	}
}
