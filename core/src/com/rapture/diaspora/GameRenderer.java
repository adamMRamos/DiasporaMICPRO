package com.rapture.diaspora;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.rapture.diaspora.gameobjects.DiasporaActor;
import com.rapture.diaspora.gameobjects.DiasporaPlayer;
import com.rapture.diaspora.gameobjects.GameActors;
import com.rapture.diaspora.helpers.AssetLoader;
import com.rapture.diaspora.helpers.GamePool;

public class GameRenderer 
{
	private DiasporaGameMaster master;
	
	private TextureRegion testImg;
	float y = 200;
	
	private DiasporaPlayer player1;
	private GamePool pool;
	
	private GameActors actors;
	private GameCamera camera;
	private ShapeRenderer shapeRenderer;
	
	private SpriteBatch batch;
	
	private int gameWidth;
	private int gameHeight;
	
	public GameRenderer(DiasporaGameMaster gameMaster, int screenWidth, int screenHeight)
	{
		master = gameMaster;
		
		testImg = AssetLoader.testImg;
		
		gameWidth = screenWidth;
		gameHeight = screenHeight;
		//this.midPointY = midPointY;
		
		camera = new GameCamera(gameWidth, gameHeight);
		camera.setToOrtho(false, this.gameWidth, this.gameHeight);
		camera.position.set(0, 0, 0);
		
		batch = new SpriteBatch();
		batch.setProjectionMatrix(camera.combined);
		
		shapeRenderer = new ShapeRenderer();
		shapeRenderer.setProjectionMatrix(camera.combined);
	}
	
	public void initialize()
	{
		pool = master.pool;
		actors = master.actors;
		player1 = actors.player1;
		
		camera.setTarget(player1.getX(), player1.getY());
	}
	
	public void resize(int width, int height)
	{
		camera = new GameCamera(width, height);
	    camera.translate(width / 2, height / 2, 0);
	    
	    gameWidth = width;
	    gameHeight = height;
	}
	
	private void updateCamera(float delta)
	{
		camera.updateGameCamera(player1.getX(), player1.getY(), delta);
	}
	
	private void clearScreen()
	{
		Gdx.gl.glClearColor(0,0,0,1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
	}
	
	private void renderPoolActors(SpriteBatch batch)
	{
		for (DiasporaActor actor : pool.activeActors) actor.render(batch);
	}
	
	private void renderPlayer(SpriteBatch batch)
	{
		if (player1.alive)
			player1.render(batch);
	}
	
	private void spriteBatchRender()
	{
		batch.setProjectionMatrix(camera.combined);
		batch.begin();
		
		renderTestImages();
		
		renderPoolActors(batch);
		renderPlayer(batch);
		
		batch.end();
	}
	
	public void render(float runTime, float delta) 
	{
		Gdx.app.log("Renderer", "render() called");
		Gdx.app.log("Renderer", "render() called    runTime: " + runTime);
		
		updateCamera(delta);
		
		clearScreen();
		
		spriteBatchRender();
		
		renderPlayerHitBox();
	}
	
	private void renderTestImages()
	{
		y = (y+1) % 360;
		
		batch.draw(testImg, 600f, 600f, 0, 0, 200f, y , 1, 1, 10f);
		batch.draw(testImg, 800f, 600f, 0, 0, 100f, 100f, 1, 1, y);
		//		   TextureRegion, x, y, originX, originY, sizeX, sizeY, scaleX, scaleY, rotation
	}
	
	private void renderPlayerHitBox()
	{
		shapeRenderer.setProjectionMatrix(camera.combined);
		shapeRenderer.begin(ShapeType.Filled);
		shapeRenderer.setColor(Color.RED);
		shapeRenderer.circle(player1.body.getBoundingCircle().x, player1.body.getBoundingCircle().y, player1.body.getBoundingCircle().radius);
		shapeRenderer.end();
	}
	
	public void logValues()
	{

		Gdx.app.log("Renderer", "updateCamera() called    camera: " + camera.position);
		Gdx.app.log("Renderer", "updateCamera() called    camera targ: " + (camera.getTarget().x - camera.position.x) + " , " + (camera.getTarget().y - camera.position.y));
		Gdx.app.log("Renderer", "updateCamera() called    actor dist: " + (player1.getX() - camera.position.x) + " , " + (player1.getY() - camera.position.y));
		Gdx.app.log("Renderer", "updateCamera() called    camera veloc: " + camera.getVelocity());
		
		float actorPositionDifference = player1.getX() - camera.position.x; 
		Gdx.app.log("Renderer", "updateCamera() called    cameraOffsetX: " + actorPositionDifference);
	}
	
	//Getters & Setters
	public float getGameWidth()
	{
		return gameWidth;
	}
	
	public float getGameHeight()
	{
		return gameHeight;
	}
}
