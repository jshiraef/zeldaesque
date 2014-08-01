package com.zeldashtuff.jshiraef;

import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.tiled.TiledMap;

public class Dungeon {
	
	public static TiledMap room;
	public int currentRoom = 0;
	
	private boolean[][] blocked;
	
	
	public Dungeon() {
		try
		{
			room = new TiledMap("lvl/" + switchRoom(currentRoom) + ".tmx");
		}
		catch (SlickException e) 
		{
			System.out.println("error loading level");
		}
		
		loadRoom();
		
	}
	
	
	public void render(Graphics g)	{
		
		room.render(0, 0);
		
	}
	
	public void update () {
		
		
		
	}
	
	public boolean isBlocked(float x, float y)
	{
		int xBlock = (int)x / room.getTileWidth();
		int yBlock = (int)y / room.getTileHeight();
		return blocked[xBlock][yBlock];
	}
	
	public static String switchRoom(int roomNumber)
	{
		
		
		switch (roomNumber)
		{
		case 1 :
			return "room5";
		case 2 : 
			return "room4";
			//System.out.println("It should be room 2!");
		case 3 : 
			return "room5";
		default:
			return "room5";
		}
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
	
	

}
