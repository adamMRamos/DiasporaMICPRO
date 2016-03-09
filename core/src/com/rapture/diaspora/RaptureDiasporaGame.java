package com.rapture.diaspora;

import java.util.Iterator;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.TimeUtils;

public class RaptureDiasporaGame extends ApplicationAdapter 
{
	SpriteBatch batch;
	OrthographicCamera camera; 
	Texture img;
	
	@Override
	public void create () 
	{
		batch = new SpriteBatch();
		camera = new OrthographicCamera();
		camera.setToOrtho(false, 800, 480);
		
		img = new Texture(Gdx.files.internal("badlogic.jpg"));
	}

	@Override
	public void render () {
		Gdx.gl.glClearColor(0, 0, 0.2f, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		
		camera.update();
		
		batch.setProjectionMatrix(camera.combined);
		batch.begin();
		
		batch.draw(img, camera.viewportWidth, camera.viewportHeight);
		
		batch.end();
		
		int x = 0;
		
		if(Gdx.input.isKeyPressed(Keys.LEFT))  x -= 200 * Gdx.graphics.getDeltaTime();
		if(Gdx.input.isKeyPressed(Keys.RIGHT)) x += 200 * Gdx.graphics.getDeltaTime();
	}
	
	@Override
	public void dispose()
	{
		batch.dispose();
	}
	
	//raindrop.x = MathUtils.random(0, 800-64);
	//lastDropTime = TimeUtils.nanoTime();
}
