package com.rapture.diaspora.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.GL20;
import com.rapture.diaspora.DiasporaGameMaster;
import com.rapture.diaspora.helpers.InputHandler;

public class GameScreen implements Screen
{
	private DiasporaGameMaster master;
	
	private float runTime;
	
	public GameScreen()
	{
		Gdx.app.log("GameScreen", "Attached");
		
		float screenWidth = Gdx.graphics.getWidth();
		float screenHeight = Gdx.graphics.getHeight();
		
		runTime = 0; //needed for drawing animations
		
		master = new DiasporaGameMaster((int)screenWidth, (int)screenHeight);
		master.initialize();
		
		Gdx.input.setInputProcessor(new InputHandler(master.keyLog));
	}
	
	@Override
	public void show() 
	{
		Gdx.app.log("GameScreen", "show() called");
	}

	@Override
	public void render(float delta) 
	{
		// Sets a Color to Fill the Screen with (RGB = 10, 15, 230), Opacity of 1 (100%)
        Gdx.gl.glClearColor(10/255.0f, 15/255.0f, 230/255.0f, 1f);
        // Fills the screen with the selected color
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        
        Gdx.app.log("GameScreen", "render() called    FPS: " + (1/delta) + "");
        
        runTime += delta;
        
        if (!master.keyLog.hasKey(Keys.P)) 
        	master.world.update(delta);
        
        master.renderer.render(runTime, delta);
	}

	@Override
	public void resize(int width, int height) 
	{
		Gdx.app.log("GameScreen", "resize(int,int) called    width,height: " + width + "," + height);
		master.renderer.resize(width, height);
	}

	@Override
	public void pause() 
	{
		Gdx.app.log("GameScreen", "pause() called");
	}

	@Override
	public void resume() 
	{
		Gdx.app.log("GameScreen", "resume() called");
	}

	@Override
	public void hide() 
	{
		Gdx.app.log("GameScreen", "hide() called");
	}

	@Override
	public void dispose() 
	{
		Gdx.app.log("GameScreen", "dispose() called");
	}
	
}
