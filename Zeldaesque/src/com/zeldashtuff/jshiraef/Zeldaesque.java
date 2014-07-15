package com.zeldashtuff.jshiraef;

import org.newdawn.slick.*;
import org.newdawn.slick.tiled.*;
import org.lwjgl.*;

public class Zeldaesque extends BasicGame{
	
	public static final String gameName = "Zelder Clone";
	public static AppGameContainer appgc;
	private TiledMap room;
	
	public Zeldaesque(String gameName)
			{
			super(gameName);

			}

	@Override
	public void render(GameContainer container, Graphics g) throws SlickException {
		
		room.render(0, 0);
		
		
	}

	@Override
	public void init(GameContainer arg0) throws SlickException {
		
		try {
		room = new TiledMap("lvl/room1.tmx");
		}catch (SlickException e){
			System.out.println("error loading level");
		}
		
		
	}

	@Override
	public void update(GameContainer arg0, int arg1) throws SlickException {
		// TODO Auto-generated method stub
		
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
