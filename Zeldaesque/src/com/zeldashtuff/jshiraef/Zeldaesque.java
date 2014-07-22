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
	
	
	private int health, maxHealth, healthBarX, healthBarY, healthBarWidth, healthBarHeight, healthScale;
	
	public Color healthBarColor;
	

	
	
	
	
	
	
	public Zeldaesque(String gameName)
	{
		super(gameName);
	}

	@Override
	public void render(GameContainer container, Graphics g) throws SlickException
	{
		
		
		
		dungeon.render(g);
		player.render(g);
		
		
		
		
		if(player.inBossRoom)
		{
			boss.render();
		} 
		
	}
	
	public void drawHealthBar(Graphics g) {
		float healthScale = health/maxHealth;
		g.setColor(healthBarColor);
		g.fillRect(healthBarX, healthBarY, healthBarWidth * healthScale, healthBarHeight);
	}
	

	
	@Override
	public void init(GameContainer arg0) throws SlickException {
		
		
		
		
		
		
		
		
		boss = new Boss();
		dungeon = new Dungeon();
		player = new Player(dungeon);
		
		
		
		
		
		
		
	}

	public void update(GameContainer container, int delta) throws SlickException 
	{
		
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