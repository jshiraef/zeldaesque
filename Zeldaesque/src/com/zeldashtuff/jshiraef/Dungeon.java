package com.zeldashtuff.jshiraef;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.tiled.TiledMap;

public class Dungeon {
	
	public static TiledMap room;
//	public static int currentRoom = 0;
	
	public Player player;
	public Boss boss;
	
	Room currentRoom;
	
	Room firstRoom;
	Room bossRoom;
	Room baddieRoom;
	
	
	
	
	
	public Dungeon() throws SlickException {
		
		
		
		boss = new Boss(this);
		player = new Player(this);
		
		
		firstRoom = new Room("room5");
		bossRoom = new Room("room4");
		baddieRoom = new Room("room6");
		
		
		currentRoom = firstRoom;
		
	}
	
	
	public void render(Graphics g)	{
		
		currentRoom.room.render(0, 0);
		
		if(player.inBossRoom)
		{
			bossRoom.loadRoom();
			boss.render(g);
		} 
		
		player.render(g);
		
		if(player.inBadRoom)
		{
			
	//		boss.render(g);
			currentRoom.bc.render(g);
		}
		
	}
	
	public void update (GameContainer container, int delta) throws SlickException {
		
		if(player.inBossRoom)
		{
			boss.update(delta);
		} 
		
		if(player.inBadRoom) {
			baddieRoom.bc.update(delta);
		}
		
		if(player.inBadRoom && player.enteredNewRoom) {
			baddieRoom.bc.addBaddie(new Baddie(currentRoom.getPixelWidth()/3, currentRoom.getPixelHeight()/3, currentRoom.getPixelWidth()/3 + 25, currentRoom.getPixelHeight()/3 + 34, this));
			baddieRoom.bc.addBaddie(new Baddie(currentRoom.getPixelWidth()/2, currentRoom.getPixelHeight()/2, (currentRoom.getPixelWidth()/2) + 25, (currentRoom.getPixelHeight()/3) + 34, this));
			baddieRoom.bc.addBaddie(new Baddie((currentRoom.getPixelWidth()/3) * 2, currentRoom.getPixelHeight()/4, (currentRoom.getPixelWidth()/3) * 2 + 25, currentRoom.getPixelHeight()/4 + 34, this));
			player.enteredNewRoom = false;
		}
		
//		System.out.println("Tile ID: " room.getTileID());
		
		player.update(container.getInput(), delta);
		
		
	}
	
	
	
	
	
	

}
