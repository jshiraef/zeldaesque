package com.zeldashtuff.jshiraef;

import org.newdawn.slick.Animation;
import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.tiled.TiledMap;

public class Player {
	
	Animation up, down, left, right, sprite ; //redundant variables?
	
	public float x = 64f;
	public float y = 64f;
	
	public boolean inBossRoom = false;
	
	public Dungeon dungeon;
	
	private int health = 5;
	private int maxHealth = 10;
	private int healthBarX = 100, healthBarY = 120, healthBarWidth = 32, healthBarHeight = 32, healthScale = 64;
	
	public Color healthBarColor;
	
	
	public Player (Dungeon dungeon) throws SlickException{
		
		int[] duration = {300, 300};
		
		this.dungeon = dungeon;
		
		Image[] movementUp ={new Image("res/linkWalking.png"), new Image("res/linkWalking2.png")};
		Image[] movementDown = {new Image("res/linkWalking.png"), new Image("res/linkWalking2.png")};
		
		up = new Animation(movementUp, duration, false);
		sprite = up; 
		
	}
	
	public void render(Graphics g) {
	
		
		g.drawString(" Link's X: " + x +  "\n Link's Y: " + y, 500, 100);
		
		sprite.draw(x, y);
		drawHealthBar(g);
		
	}
	
	public void update(Input input, int delta) throws SlickException {
		
		

		if(y < -25)
		{
			inBossRoom = true;
			dungeon.currentRoom = 2;
			dungeon.room = new TiledMap("lvl/" + dungeon.switchRoom(dungeon.currentRoom) + ".tmx");
			y = 600;
		}
		
		
		
		if (input.isKeyDown(Input.KEY_UP))
		{
			sprite = up;
			if (!dungeon.isBlocked(x, y - delta * 0.1f))
	          {
                sprite.update(delta);
                
                y -= delta * 0.1f;
            }
		}
		else if (input.isKeyDown(Input.KEY_DOWN))
		{
			 if (!dungeon.isBlocked(x, y + sprite.getHeight() + delta * 0.1f))
             {
                 sprite.update(delta);
                 y += delta * 0.1f;
             }
		}
		else if (input.isKeyDown(Input.KEY_LEFT))
		{
			sprite = up;
			if (!dungeon.isBlocked(x - delta * 0.1f, y))
            {
                sprite.update(delta);
                x -= delta * 0.1f;
            }
		}
		else if (input.isKeyDown(Input.KEY_RIGHT))
		{
			sprite = up;
			 if (!dungeon.isBlocked(x + sprite.getWidth() + delta * 0.1f, y))
             {
                 sprite.update(delta);
                 x += delta * 0.1f;
             }
		}
		
		if(x/dungeon.room.getTileWidth() == Boss.bossX/dungeon.room.getTileWidth() || y/dungeon.room.getTileWidth() == Boss.bossY/dungeon.room.getTileWidth()) {
			System.out.println("hit damage");
		}
		
	}
	public void drawHealthBar(Graphics g) {
		float healthScale = health/maxHealth;
		g.setColor(healthBarColor);
		g.fillRect(healthBarX, healthBarY, healthBarWidth * healthScale, healthBarHeight);
	}
	
	

}
