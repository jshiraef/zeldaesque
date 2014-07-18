package com.zeldashtuff.jshiraef;

import org.newdawn.slick.*;
import org.newdawn.slick.tiled.*;
import org.lwjgl.*;

public class Zeldaesque extends BasicGame{
	
	public static final String gameName = "Zelder Clone";
	public static AppGameContainer appgc;
	private TiledMap room;
	int currentRoom = 0;
	
	private boolean[][] blocked;
	private static final int SIZE = 64;
	private float x = 64f;
	private float y = 64f;
	
	private float bossX = 250;
	private float bossY = 250;
	
	private boolean inBossRoom = false;
	
	private int direction = 1;
	
	
	
	
	
	
	Animation up, down, left, right, sprite, boss, pacing;
	
	int[] duration = {300, 300};
	int[] bossDuration = {300, 300, 300};
	
	public Zeldaesque(String gameName)
			{
			super(gameName);

			}

	@Override
	public void render(GameContainer container, Graphics g) throws SlickException {
		
		room.render(0, 0);
		
		g.drawString(" Link's X: " + x +  "\n Link's Y: " + y, 500, 100);
		
		sprite.draw(x, y);
		
		if (inBossRoom){
			
			boss.draw(bossX, bossY);
			
			switch(direction) {
			
			case 1: 
				
				if (bossX < 400)
					bossX ++;
				else direction = 2;
				
			case 2:
				if (bossX > 300)
					bossX--;
				else direction = 1;
			
			}
			
			
		}
		
	}
	
	private boolean isBlocked(float x, float y) {
		int xBlock = (int)x/ SIZE;
		int yBlock = (int)y/ SIZE;
		return blocked [xBlock][yBlock];
	}

	public static String switchRoom(int roomNumber){
		
		String roomName = "";
		
		switch (roomNumber) {
		
		case 1 :
			roomName = "room1";
			break;
		
		case 2 : 
			roomName = "room4";
			System.out.println("It should be room 2!");
			break;
		case 3 : 
			roomName = "room3";
			break;
		default:
			roomName = "room3";
		}
		
		return roomName;
	}
	
	@Override
	public void init(GameContainer arg0) throws SlickException {
		
		Image[] movementUp ={new Image("res/linkWalking.png"), new Image("res/linkWalking2.png")};
		Image[] movementDown = {new Image("res/linkWalking.png"), new Image("res/linkWalking2.png")};
		
		Image[] bossPacing = {new Image("res/girlBoss.png"), new Image("res/girlBoss2.png"), new Image("res/girlBoss3.png")};
		
		up = new Animation(movementUp, duration, false);
		pacing = new Animation(bossPacing, bossDuration, true);
		
		sprite =  up;
		boss = pacing;
		
		
		
		
		
		
		try {
		room = new TiledMap("lvl/" + switchRoom(currentRoom) + ".tmx");
		}catch (SlickException e){
			System.out.println("error loading level");
		}
		
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

	public void update(GameContainer container, int delta) throws SlickException 
	{
		
		
		if(y < -50){
			inBossRoom = true;
			currentRoom = 2;
			room = new TiledMap("lvl/" + switchRoom(currentRoom) + ".tmx");
		}
		
		
		switchRoom(currentRoom);
		Input input = container.getInput();
		
		if (input.isKeyDown(Input.KEY_UP))
		{
			sprite = up;
			if (!isBlocked(x, y - delta * 0.1f))
            {
                sprite.update(delta);
                
                y -= delta * 0.1f;
            }
		}
		else if (input.isKeyDown(Input.KEY_DOWN))
		{
			 if (!isBlocked(x, y + SIZE + delta * 0.1f))
             {
                 sprite.update(delta);
                 y += delta * 0.1f;
             }
		}
		else if (input.isKeyDown(Input.KEY_LEFT))
		{
			sprite = up;
			if (!isBlocked(x - delta * 0.1f, y))
            {
                sprite.update(delta);
                x -= delta * 0.1f;
            }
		}
		else if (input.isKeyDown(Input.KEY_RIGHT))
		{
			sprite = up;
			 if (!isBlocked(x + SIZE + delta * 0.1f, y))
             {
                 sprite.update(delta);
                 x += delta * 0.1f;
             }
		}
	
		
	}
	
	public static void main(String[] args) {
		
		try {
			appgc = new AppGameContainer(new Zeldaesque(gameName));
			appgc.setDisplayMode(11*64, 7*64, false);
			appgc.start();
		} catch (SlickException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
		
		
	}
	}
