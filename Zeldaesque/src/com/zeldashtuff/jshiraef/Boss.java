package com.zeldashtuff.jshiraef;

import org.newdawn.slick.Animation;
import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Vector2f;

public class Boss {

	Animation boss, pacing;
	
	Image bossPic1 = new Image("res/girlBoss.png");
	Image bossPic2 = new Image("res/girlBoss2.png");
	Image bossPic3 = new Image("res/girlBoss3.png");
	
	public Dungeon dungeon;

	public float x = 250;
	public float y = 375;
	
	public float bossCenterX;
	public float bossCenterY;
	
	public Color healthBarColor = Color.blue;
	
	private double actualSeparation;
	
	public static boolean angry = false;
	
	public static float bossHealth = 1, bossMaxHealth = (float) 1.5, bossHealthBarX = 100, bossHealthBarY = 100, bossHealthBarWidth = 200, bossHealthBarHeight = 10;
	
	
	
//	public static int bossTileX;
//	public static int bossTileY;
	
	

	
	public static int direction = 1;
	
	int[] bossDuration = {300, 300, 300};
	
	
	public Boss(Dungeon dungeon) throws SlickException {
		
		Image[] bossPacing = {bossPic1, bossPic2, bossPic3};
		pacing = new Animation(bossPacing, bossDuration, true);
		boss = pacing;
		
		this.dungeon = dungeon;
	}
	
	public void render(Graphics g) {
		
		
		bossCenterX = x + bossPic1.getHeight()/2;
		bossCenterY = y + bossPic2.getWidth()/2;
		
		
//		g.drawString(" Boss's X: " + bossTileX +  "\n Boss's Y: " + bossTileY, 700, 100);
//		g.drawString(" Boss's X: " + bossX +  "\n Boss's Y: " + bossY, 700, 400);
//		g.drawString("DISTANCE: " + actualSeparation, 700, 100);
//		
//		g.drawString("distance to Player: " + distanceToPlayer, 300, 600);


		boss.draw(x, y);
		drawBossHealthBar(g);
		
		
	}
	
	public void update(int delta) {
		
		
		
		Vector2f distanceToPlayer = new Vector2f(bossCenterX - dungeon.player.playerCenterX, bossCenterY - dungeon.player.playerCenterY);
		
		actualSeparation = Math.sqrt(distanceToPlayer.x * distanceToPlayer.x + distanceToPlayer.y * distanceToPlayer.y);
		
//		 bossTileX = Math.round(bossX)/dungeon.room.getTileWidth();
//		 bossTileY = Math.round(bossY)/dungeon.room.getTileHeight();
		switch(direction)
		{
		case 1:
			if (angry) {
				
				
				if(distanceToPlayer.x < 0)
					x +=.07;
				else if (distanceToPlayer.x > 0)
					x -= .07;
				if(distanceToPlayer.y < 0)
					y +=.07;
				else if (distanceToPlayer.y > 0)
					y -= .07;
				
				Player.sprite = Player.hit;
			}
			
			else direction = 2;
			
			System.out.println("Watch out");
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
		
//		if(Player.distanceToBoss.x < 50)
//		angry = true;
		
	}
	
	public void drawBossHealthBar(Graphics g) {
		float healthScale = bossHealth/bossMaxHealth;
		g.setColor(healthBarColor);
		g.fillRect(x, y, bossHealthBarWidth * healthScale, bossHealthBarHeight);
		g.drawString("Boss's HP", x, y - 20);
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
