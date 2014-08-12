package com.zeldashtuff.jshiraef;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.tiled.TiledMap;

public class Dungeon {
	
	public static TiledMap room;
	public int currentRoom = 0;
	
	public Player player;
	public Boss boss;
//	public Baddie baddie;
	
	private boolean[][] blocked;
	
	
	public Dungeon() throws SlickException {
		try
		{
			room = new TiledMap("lvl/" + switchRoom(currentRoom) + ".tmx");
		}
		catch (SlickException e) 
		{
			System.out.println("error loading level");
		}
		
		boss = new Boss(this);
		player = new Player(this);
		
		
		loadRoom();
		
	}
	
	
	public void render(Graphics g)	{
		
		room.render(0, 0);
		
		if(player.inBossRoom)
		{
			loadRoom();
			boss.render(g);
		} 
		
		player.render(g);
		
		if(player.inBadRoom)
		{
			loadRoom();
			boss.render(g);
//			baddie.render();
		}
		
	}
	
	public void update (GameContainer container, int delta) throws SlickException {
		
		if(player.inBossRoom)
		{
			boss.update(delta);
		} 
		
		player.update(container.getInput(), delta);
		
		
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
			return "room6";
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
