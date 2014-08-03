package com.zeldashtuff.jshiraef;

import org.newdawn.slick.Animation;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

public class Boss {

	Animation boss, pacing;
	
	Image bossPic1 = new Image("res/girlBoss.png");
	Image bossPic2 = new Image("res/girlBoss2.png");
	Image bossPic3 = new Image("res/girlBoss3.png");
	
	public Dungeon dungeon;

	public static float bossX = 250;
	public static float bossY = 500;
	
	public static float bossCenterX;
	public static float bossCenterY;
	
	
	
//	public static int bossTileX;
//	public static int bossTileY;
	
	

	
	private int direction = 1;
	
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


		boss.draw(bossX, bossY);
		
		switch(direction)
		{
		case 1: 
			if (bossX < 350)
				bossX += .05;
			else
				direction = 2;
			break;
		case 2:
			if (bossX > 200)
				bossX -= .05;
			else
				direction = 1;
			break;
		}
	}
	
	public void update(int delta) {
		
//		 bossTileX = Math.round(bossX)/dungeon.room.getTileWidth();
//		 bossTileY = Math.round(bossY)/dungeon.room.getTileHeight();
		
	}
	
}
