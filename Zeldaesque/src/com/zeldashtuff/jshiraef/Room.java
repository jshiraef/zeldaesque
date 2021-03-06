package com.zeldashtuff.jshiraef;

import org.newdawn.slick.SlickException;
import org.newdawn.slick.tiled.TiledMap;

public class Room {
	
	TiledMap room;
	
	private boolean[][] blocked;
	private boolean[][] movable;
	
	public BaddieController bc;
	public ProjectileController pc;
	
	public Room(String roomPath) throws SlickException
	{
		
		bc = new BaddieController();
		pc = new ProjectileController();
		
		room = new TiledMap("lvl/" + roomPath + ".tmx");
		
		loadRoom();
	}
	
	public boolean isBlocked(float x, float y)
	{
		int tileX = (int)x / room.getTileWidth();
		int tileY = (int)y / room.getTileHeight();
		return blocked[tileX][tileY];
	}
	
	public boolean isMovable(float x, float y)
	{
		int tileX = (int) x/ room.getTileWidth();
		int tileY = (int) y/ room.getTileHeight();
		return movable [tileX][tileY];
	}
	
	public void loadRoom() {
		 blocked = new boolean[room.getWidth()][room.getHeight()];
		 movable = new boolean[room.getWidth()][room.getHeight()];
			
			for (int xAxis=0;xAxis<room.getWidth(); xAxis++)
	        {
	             for (int yAxis=0;yAxis<room.getHeight(); yAxis++)
	             {
	                 int tileID = room.getTileId(xAxis, yAxis, 0);
	                 String blockedValue = room.getTileProperty(tileID, "stone", "false");
	                 if ("true".equals(blockedValue))
	                 {
	                     blocked[xAxis][yAxis] = true;
	                 }
	                 
	                 String movableValue = room.getTileProperty(tileID, "movableBox", "false");
	                 if ("true".equals(movableValue))
	                 {
	                	 movable[xAxis][yAxis] = true;
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
