package com.zeldashtuff.jshiraef;

import org.newdawn.slick.Animation;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.SpriteSheet;
import org.newdawn.slick.geom.Vector2f;

public class Baddie extends Boss{
	
	Animation soldier, soldierRight, soldierLeft;
	
	Image soldierRightSpriteSheet = new Image("res/soldierSpriteWalkingRightTest.png");
	Image soldierLeftSpriteSheet = new Image("res/soldierSpriteWalkingLeftTest.png");
	
	private float x = 100;
	private float y = 100;
	
	private float centerX = 0;
	private float centerY = 0;
	
	public float baddieHealth = 1, baddieMaxHealth = (float) 1.5, baddieHealthBarX = 100, baddieHealthBarY = 100, baddieHealthBarWidth = 200, baddieHealthBarHeight = 10;

	

	public Baddie(float x, float y, float centerX, float centerY, Dungeon dungeon) throws SlickException {
		super(dungeon);
		
		this.x = x;
		this.y = y;
		this.centerX = centerX;
		this.centerY = centerY;
		this.dungeon = dungeon;
		
		soldierRight = new Animation(new SpriteSheet(soldierRightSpriteSheet, 50, 68), 150);
		soldierLeft = new Animation(new SpriteSheet(soldierLeftSpriteSheet, 50, 68), 150);
		
		soldier = soldierRight;
	}
	
public void update(int delta) {
		
		
		if(!destroyed) {
		Vector2f distanceToPlayer = new Vector2f(centerX - dungeon.player.playerCenterX, centerY - dungeon.player.playerCenterY);
		
		actualSeparation = Math.sqrt(distanceToPlayer.x * distanceToPlayer.x + distanceToPlayer.y * distanceToPlayer.y);
		
//		 bossTileX = Math.round(bossX)/dungeon.room.getTileWidth();
//		 bossTileY = Math.round(bossY)/dungeon.room.getTileHeight();
		switch(direction)
		{
		case 1:
			if (angry) {
				
				System.out.println("Watch out");
				
				if(distanceToPlayer.x < 0)
					x += delta * bossSpeed;
				else if (distanceToPlayer.x > 0)
					x -= delta * bossSpeed;
				if(distanceToPlayer.y < 0)
					y += delta * bossSpeed;
				else if (distanceToPlayer.y > 0)
					y -= delta * bossSpeed;
				
			}
			
			else direction = 2;
			
			
			break;
				
		case 2:
				
			if (x < 450) {
				soldier = soldierRight;
				x += .1;
			}
			else
				direction = 3;
			System.out.println("Baddie should have just switched to case 3");
			
			break;
		case 3:

				if (x > 200) {
					soldier = soldierLeft;
					x -= .1;
				}
				else
					direction = 2;
				System.out.println("Baddie should now have switched to case 2");
			
			break;
		}
	
			
		}
		else {
			centerX = -100;
			centerY = -100;
			
		}
		
		if(baddieHealthBarWidth <= 0) {
			dead = true;
		}

//		if(Player.distanceToBoss.x < 50)
//		angry = true;
		
	}

	public void render(Graphics g)	 {
		
		if(!destroyed) {
			centerX = x + 25;
			centerY = y + 34;
			
	
			if(!dead) {
				drawBaddieHealthBar(g);
				soldier.draw(x, y);
				
				if(hit)
					bossPic1.drawFlash(x,  y);
				
			}
			else explode();
			}
		
	}
	
	public void drawBaddieHealthBar(Graphics g) {
		float healthScale = baddieHealth/baddieMaxHealth;
		g.setColor(healthBarColor);
		g.fillRect(x, y, baddieHealthBarWidth * healthScale, baddieHealthBarHeight);
		g.drawString("Baddie's HP", x, y - 20);
	}
	
	public float getX() {
		return x;
	}
	
	public float getY() {
		return y;
	}

}
