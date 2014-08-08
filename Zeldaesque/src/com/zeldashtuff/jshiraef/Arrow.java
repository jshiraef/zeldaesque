package com.zeldashtuff.jshiraef;

import org.newdawn.slick.Animation;
import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Vector2f;
import org.newdawn.slick.tiled.TiledMap;




public class Arrow {
	
	private float x = 100;
	private float y = 100;
	private Direction direction;
	
	Animation projectile;
	
	public boolean loaded = false;

	Image arrows = new Image("res/arrowSpriteSheet.png");
	
	Image arrow = arrows.getSubImage(1, 1, 30, 30);
	
	
	public Arrow(float x, float y, Player player, Direction direction) throws SlickException {
		
		this.x = x;
		this.y = y;
		this.direction = playerDirection;
		
	}
	
	public void update() {
		
		if (direction == Direction.NORTH)
			y -= 1;
		
		else if(direction == Direction.SOUTH)
			y += 1;
		
		else if(direction == Direction.EAST)
			x -= 1;
		
		else if(direction == Direction.WEST)
			x += 1;
		
	}
	
	public void render(Graphics g) {
		
		arrow.draw((float)x, (float)y);
		
	}
	
	public float getX() {
		return x;
	}
	
	public float getY() {
		return y;
	}
}
