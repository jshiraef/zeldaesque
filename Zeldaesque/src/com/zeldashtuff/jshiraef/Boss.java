package com.zeldashtuff.jshiraef;

import org.newdawn.slick.Animation;
import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.SpriteSheet;
import org.newdawn.slick.geom.Vector2f;

public class Boss {

	Animation boss, pacing, kaboom, fullKaboom;
	
	Image bossPic1 = new Image("res/girlBoss.png");
	Image bossPic2 = new Image("res/girlBoss2.png");
	Image bossPic3 = new Image("res/girlBoss3.png");
	
	Image fullExplosionSpriteSheet = new Image("res/explodingSheet2.png");
	
	
	
	public Dungeon dungeon;
	
	public boolean hit = false;
	public boolean dead = false;
	
	public boolean destroyed = false;

	public float x = 250;
	public float y = 375;
	
	public float bossSpeed = (float) 0.05;
	
	public float bossCenterX;
	public float bossCenterY;
	
	public Color healthBarColor = Color.blue;
	
	protected double actualSeparation;
	
	public static boolean angry = false;
	
	public float bossHealth = 1, bossMaxHealth = (float) 1.5, bossHealthBarX = 100, bossHealthBarY = 100, bossHealthBarWidth = 200, bossHealthBarHeight = 10;
	
	Vector2f bossCollisionRadius = new Vector2f(40, 60);
	
//	public static int bossTileX;
//	public static int bossTileY;
	
	

	
	public static int direction = 1;
	
	int[] bossDuration = {300, 300, 300};
	int[] explosionDuration = {150, 150, 250, 250};
	
	
	public Boss(Dungeon dungeon) throws SlickException {
		
		Image[] bossPacing = {bossPic1, bossPic2, bossPic3};

		pacing = new Animation(bossPacing, bossDuration, true);
		fullKaboom = new Animation(new SpriteSheet(fullExplosionSpriteSheet, 64, 64), 100);
		
		boss = pacing;
		
		this.dungeon = dungeon;
	}
	
	public void render(Graphics g) {
		
		
		
		
		
//		g.drawString(" Boss's X: " + bossTileX +  "\n Boss's Y: " + bossTileY, 700, 100);
//		g.drawString(" Boss's X: " + bossX +  "\n Boss's Y: " + bossY, 700, 400);
//		g.drawString("DISTANCE: " + actualSeparation, 700, 100);
//		
//		g.drawString("distance to Player: " + distanceToPlayer, 300, 600);

		//if(!hit)
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
	
	public void drawBossHealthBar(Graphics g) {
		float healthScale = bossHealth/bossMaxHealth;
		g.setColor(healthBarColor);
		g.fillRect(x, y, bossHealthBarWidth * healthScale, bossHealthBarHeight);
		g.drawString("Boss's HP", x, y - 20);
	}
	
	public void explode() {
		fullKaboom.setLooping(false);
		fullKaboom.draw(x, y);
		
		System.out.println("explode frame is " + fullKaboom.getFrame());
		
		if (fullKaboom.getFrame() == 19)
			destroyed = true;
			
	}
	
	public float getBossX() {
		return x;
	}
	
	public float getBossY() {
		return y;
	}
	
	public void setBossX() {
		this.x = x;
	}
	
	public void setBossY() {
		this.y = y;
	}
	
}
