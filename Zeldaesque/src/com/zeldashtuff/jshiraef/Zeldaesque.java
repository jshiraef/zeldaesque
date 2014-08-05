package com.zeldashtuff.jshiraef;

import org.newdawn.slick.*;
import org.newdawn.slick.tiled.*;
import org.lwjgl.*;



public class Zeldaesque extends BasicGame
{
	public static final String gameName = "Zelder Clone";
	public static AppGameContainer appgc;
	
	
	
	public Boss boss;
	public Player player;
	public Dungeon dungeon;
	
	
	
	private static final int SIZE = 64;
	public static final int screenWidth = 960;
	public static final int screenHeight = 704;
	
	
	public static float health = 1, maxHealth = (float) 1.5, healthBarX = 100, healthBarY = 100, healthBarWidth = 200, healthBarHeight = 10;
	
	public Color healthBarColor = Color.red;
	

	
	
	
	public Zeldaesque(String gameName)
	{
		super(gameName);
	}

	public void init(GameContainer arg0) throws SlickException {
	
		
		dungeon = new Dungeon();
	
		}
	
	public void update(GameContainer container, int delta) throws SlickException 
		{
		
		dungeon.update(container, delta);
	
		}
	
	
	@Override
	public void render(GameContainer container, Graphics g) throws SlickException
	{
		
		dungeon.render(g);
	
		drawHealthBar(g);
		
	}
	

	public void drawHealthBar(Graphics g) {
		float healthScale = health/maxHealth;
		g.setColor(healthBarColor);
		g.fillRect(healthBarX, healthBarY, healthBarWidth * healthScale, healthBarHeight);
		g.drawString("Link's HP", healthBarX, healthBarY - 20);
	}
	
	

	

	

	
	
	public static void main(String[] args) throws SlickException
	{
		try
		{
			appgc = new AppGameContainer(new Zeldaesque(gameName));
			appgc.setDisplayMode(screenWidth, screenHeight, false);
			appgc.start();
		}
		catch (SlickException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}