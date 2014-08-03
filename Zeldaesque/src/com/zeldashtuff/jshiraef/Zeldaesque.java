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
	
	
	public static float health = 1, maxHealth = (float) 1.5, healthBarX = 100, healthBarY = 100, healthBarWidth = 200, healthBarHeight = 10;
	
	public Color healthBarColor = Color.red;
	

	
	
	
	public Zeldaesque(String gameName)
	{
		super(gameName);
	}

	@Override
	public void render(GameContainer container, Graphics g) throws SlickException
	{
		
		
		
		dungeon.render(g);
		player.render(g);
		drawHealthBar(g);
		
		
		
		
		if(player.inBossRoom)
		{
			dungeon.loadRoom();
			boss.render(g);
		} 
		
	}
	

	public void drawHealthBar(Graphics g) {
		float healthScale = health/maxHealth;
		g.setColor(healthBarColor);
		g.fillRect(healthBarX, healthBarY, healthBarWidth * healthScale, healthBarHeight);
		g.drawString("Link's HP", healthBarX, healthBarY - 20);
	}
	
	

	
	@Override
	public void init(GameContainer arg0) throws SlickException {
		
		
		
		
		
		
		
		
		boss = new Boss(dungeon);
		dungeon = new Dungeon();
		player = new Player(dungeon);
		
		
		
		
		
		
		
	}

	public void update(GameContainer container, int delta) throws SlickException 
	{
		if(player.inBossRoom)
		{
			boss.update(delta);
		} 
		
		player.update(container.getInput(), delta);
		
	}
	
	public static void main(String[] args)
	{
		try
		{
			appgc = new AppGameContainer(new Zeldaesque(gameName));
			appgc.setDisplayMode(960, 704, false);
			appgc.start();
		}
		catch (SlickException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}