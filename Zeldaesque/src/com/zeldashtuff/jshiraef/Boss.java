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

	public static float bossX = 250;
	public static float bossY = 375;
	
	public static float bossCenterX;
	public static float bossCenterY;
	
	public Color healthBarColor = Color.blue;
	
	private static Vector2f distanceToPlayer;
	
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
		
		
		bossCenterX = bossX + bossPic1.getHeight()/2;
		bossCenterY = bossY + bossPic2.getWidth()/2;
		
		
//		g.drawString(" Boss's X: " + bossTileX +  "\n Boss's Y: " + bossTileY, 700, 100);
		g.drawString(" Boss's X: " + bossX +  "\n Boss's Y: " + bossY, 700, 400);
		g.drawString("DISTANCE: " + actualSeparation, 700, 100);
		
		g.drawString("distance to Player: " + distanceToPlayer, 300, 600);


		boss.draw(bossX, bossY);
		drawBossHealthBar(g);
		
		
	}
	
	public void update(int delta) {
		

		
		distanceToPlayer = new Vector2f(bossCenterX - Player.playerCenterX, bossCenterY - Player.playerCenterY);
		
		actualSeparation = Math.sqrt(distanceToPlayer.x * distanceToPlayer.x + distanceToPlayer.y * distanceToPlayer.y);
		
//		 bossTileX = Math.round(bossX)/dungeon.room.getTileWidth();
//		 bossTileY = Math.round(bossY)/dungeon.room.getTileHeight();
		switch(direction)
		{
		case 1:
			if (angry) {
				if(distanceToPlayer.x < 0)
					bossX +=.07;
				else if (distanceToPlayer.x > 0)
					bossX -= .07;
				if(distanceToPlayer.y < 0)
					bossY +=.07;
				else if (distanceToPlayer.y > 0)
					bossY -= .07;
				
				
			}
			
			else direction = 2;
			
			System.out.println("Watch out");
			break;
				
		case 2: 
			System.out.println("Case 2");
		
				
			if (bossX < 350)
				bossX += .1;
			else
				direction = 3;
			
			break;
		case 3:
			
		
			
				if (bossX > 200)
					bossX -= .1;
				else
					direction = 2;
				
			
			break;
			
		}
		
		if(Player.distanceToBoss.x < 50)
		angry = true;
		
	}
	
	public void drawBossHealthBar(Graphics g) {
		float healthScale = bossHealth/bossMaxHealth;
		g.setColor(healthBarColor);
		g.fillRect(bossX, bossY, bossHealthBarWidth * healthScale, bossHealthBarHeight);
		g.drawString("Boss's HP", bossX, bossY - 20);
	}
	
}
