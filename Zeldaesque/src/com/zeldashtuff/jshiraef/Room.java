package com.zeldashtuff.jshiraef;

import org.newdawn.slick.SlickException;
import org.newdawn.slick.tiled.TiledMap;

public class Room {
	
	TiledMap room;
	
	private boolean[][] blocked;
	
	
	public BaddieController bc;
	public ProjectileController pc;
	
	public Room (String roomPath) {
		
		try
		{
			room = new TiledMap("lvl/" + roomPath + ".tmx");
		}
		catch (SlickException e) 
		{
			System.out.println("error loading level");
		}
		
		bc = new BaddieController();
		pc = new ProjectileController();
		
		
		loadRoom();
	
	}
	
	public boolean isBlocked(float x, float y)
	{
		int xBlock = (int)x / room.getTileWidth();
		int yBlock = (int)y / room.getTileHeight();
		return blocked[xBlock][yBlock];
	}
	
	
	public void loadRoom() {
		 blocked = new boolean[room.getWidth()][room.getHeight()];
			
			for (int xAxis=0;xAxis<room.getWidth(); xAxis++)
	        {
	             for (int yAxis=0;yAxis<room.getHeight(); yAxis++)
	             {
	                 int tileID = room.getTileId(xAxis, yAxis, 0);
	                 String value = room.getTileProperty(tileID, "stone", "false");
	                 if ("true".equals(value))
	                 {
	                     blocked[xAxis][yAxis] = true;
	                 }
	             }
	         }
	}
	
	public float getPixelWidth() {
		return room.getWidth() * 64;
	}
	
	public float getPixelHeight() {
		return room.getHeight() * 64;
	}

}
