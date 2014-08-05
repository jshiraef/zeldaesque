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
	
	Animation projectile;
	
	public static boolean loaded = false;

	Image arrows = new Image("res/brownTile.png");
	
	Image arrow = arrows.getSubImage(1, 1, 32, 32);
	
	
	public Arrow(float x, float y, Player player) throws SlickException {
		
		this.x = x;
		this.y = y;
		
	}
	
	public void update() {
		
		if(Player.sprite == Player.up)
			y -= 1;
		
		else if(Player.sprite == Player.down)
			y += 1;
		
		else if(Player.sprite == Player.left)
			x -= 1;
		
		else if(Player.sprite == Player.right)
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
