package com.zeldashtuff.jshiraef;

import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Vector2f;

public class Baddie extends Boss{
	
	private float x = 100;
	private float y = 100;
	

	public Baddie(Dungeon dungeon) throws SlickException {
		super(dungeon);
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

}
