package com.rapture.diaspora.helpers;

import com.badlogic.gdx.InputProcessor;

public class InputHandler implements InputProcessor
{

	//private DiasporaPlayerTemp actor1;
	public KeyLogHandler keyLog;
	
	public InputHandler(KeyLogHandler log)
	{
		keyLog = log;
	}
	
	@Override
	public boolean keyDown(int keycode) 
	{
		//actor1.addKey(keycode);
		
		keyLog.addKey(keycode);
		
		/**
		if (keycode == Keys.RIGHT)
			actor1.moveRight();
		if (keycode == Keys.LEFT)
			actor1.moveLeft();
		if (keycode == Keys.UP)
			actor1.moveUp();
		if (keycode == Keys.DOWN)
			actor1.moveDown();
		**/
		return false;
	}

	@Override
	public boolean keyUp(int keycode) 
	{
		//actor1.removeKey(keycode);
		
		keyLog.removeKey(keycode);
		
		/**
		if (keycode == Keys.RIGHT) 
			actor1.stopMove(1);
		if (keycode == Keys.LEFT) 
			actor1.stopMove(1);
		if (keycode == Keys.UP) 
			actor1.stopMove(2);
		if (keycode == Keys.DOWN ) 
			actor1.stopMove(2);
		**/
		
		return false;
	}

	@Override
	public boolean keyTyped(char character) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean touchDown(int screenX, int screenY, int pointer, int button) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean touchUp(int screenX, int screenY, int pointer, int button) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean touchDragged(int screenX, int screenY, int pointer) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean mouseMoved(int screenX, int screenY) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean scrolled(int amount) {
		// TODO Auto-generated method stub
		return false;
	}

}
