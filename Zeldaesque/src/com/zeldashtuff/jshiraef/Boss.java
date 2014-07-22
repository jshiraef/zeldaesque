package com.zeldashtuff.jshiraef;

import org.newdawn.slick.Animation;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

public class Boss {

	Animation boss, pacing;
	

	private float bossX = 250;
	private float bossY = 250;
	
	private int direction = 1;
	
	int[] bossDuration = {300, 300, 300};
	
	
	public Boss() throws SlickException {
		
		Image[] bossPacing = {new Image("res/girlBoss.png"), new Image("res/girlBoss2.png"), new Image("res/girlBoss3.png")};
		pacing = new Animation(bossPacing, bossDuration, true);
		boss = pacing;
	}
	
	public void render() {


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
	
}
