package com.rapture.diaspora.helpers;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.Texture.TextureFilter;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class AssetLoader 
{
	public static Texture texture;
	public static Texture enemyTexture;
	public static Texture proj;
	public static Texture aPlanet;
	
	public static TextureRegion testImg;
	public static TextureRegion enemy;
	public static TextureRegion projectile;
	public static TextureRegion planet;
	
	public static void load()
	{
		texture = new Texture(Gdx.files.internal("TestImg.png"));
		texture.setFilter(TextureFilter.Nearest, TextureFilter.Nearest);
		
		testImg = new TextureRegion(texture, 0, 0, 100, 100);
		testImg.flip(false, true);
		
		enemyTexture = new Texture(Gdx.files.internal("Enemy.png"));
		enemyTexture.setFilter(TextureFilter.Nearest, TextureFilter.Nearest);
		
		enemy = new TextureRegion(enemyTexture, 0, 0, 128, 128);
		enemy.flip(false, true);
		
		proj = new Texture(Gdx.files.internal("Projectile.png"));
		proj.setFilter(TextureFilter.Nearest, TextureFilter.Nearest);
		
		projectile = new TextureRegion(proj, 0, 0, 64, 64);
		projectile.flip(false, true);
		
		aPlanet = new Texture(Gdx.files.internal("Planet.png"));
		aPlanet.setFilter(TextureFilter.Nearest, TextureFilter.Nearest);
		
		planet = new TextureRegion(aPlanet, 0, 0, 512, 512);
		planet.flip(false, true);
		
	}
	
	public static void dispose()
	{
		texture.dispose();
		proj.dispose();
	}
	
}
