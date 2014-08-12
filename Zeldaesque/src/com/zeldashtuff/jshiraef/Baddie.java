package com.zeldashtuff.jshiraef;

import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Vector2f;

public class Baddie extends Boss{
	
	private float x = 100;
	private float y = 100;
	
	private float centerX = 0;
	private float centerY = 0;
	

	public Baddie(float x, float y, float centerX, float centerY, Dungeon dungeon) throws SlickException {
		super(dungeon);
		
		this.x = x;
		this.y = y;
		this.centerX = centerX;
		this.centerY = centerY;
		this.dungeon = dungeon;
		// TODO Auto-generated constructor stub
	}
	
public void update(int delta) {
		
		
		if(!destroyed) {
		Vector2f distanceToPlayer = new Vector2f(bossCenterX - dungeon.player.playerCenterX, bossCenterY - dungeon.player.playerCenterY);
		
		actualSeparation = Math.sqrt(distanceToPlayer.x * distanceToPlayer.x + distanceToPlayer.y * distanceToPlayer.y);
		
//		 bossTileX = Math.round(bossX)/dungeon.room.getTileWidth();
//		 bossTileY = Math.round(bossY)/dungeon.room.getTileHeight();
		switch(direction)
		{
		case 1:
			if (angry) {
				
				System.out.println("Watch out");
				
				if(distanceToPlayer.x < 0)
					x +=.07;
				else if (distanceToPlayer.x > 0)
					x -= .07;
				if(distanceToPlayer.y < 0)
					y +=.07;
				else if (distanceToPlayer.y > 0)
					y -= .07;
				
			}
			
			else direction = 2;
			
			
			break;
				
		case 2:
				
			if (x < 350)
				x += .1;
			else
				direction = 3;
			
			break;
		case 3:

				if (x > 200)
					x -= .1;
				else
					direction = 2;
			
			break;
		}
	
			
		}
		else {
			bossCenterX = -100;
			bossCenterY = -100;
			
		}
		
		if(bossHealthBarWidth <= 0) {
			dead = true;
		}

//		if(Player.distanceToBoss.x < 50)
//		angry = true;
		
	}

	public void render(Graphics g)	 {
		
		if(!destroyed) {
			bossCenterX = x + bossPic1.getHeight()/2;
			bossCenterY = y + bossPic2.getWidth()/2;
			
	
			if(!dead) {
				drawBossHealthBar(g);
				boss.draw(x, y);
				
				if(hit)
					bossPic1.drawFlash(x,  y);
				
			}
			else explode();
			}
		
	}
	
	public float getX() {
		return x;
	}
	
	public float getY() {
		return y;
	}

}
