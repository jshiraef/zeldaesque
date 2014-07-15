package com.zeldashtuff.jshiraef;

import org.newdawn.slick.*;
import org.lwjgl.*;

public class Zeldaesque extends BasicGame{
	
	public static final String gameName = "Zelder Clone";
	public static AppGameContainer appgc;
	
	public Zeldaesque(String gameName)
			{
			super(gameName);

			}

	@Override
	public void render(GameContainer arg0, Graphics arg1) throws SlickException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void init(GameContainer arg0) throws SlickException {
		// TODO Auto-generated method stub
		
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
