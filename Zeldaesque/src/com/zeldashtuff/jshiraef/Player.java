package com.zeldashtuff.jshiraef;

import org.newdawn.slick.Animation;
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
		
	}
	
	

}
