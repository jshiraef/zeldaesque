package com.zeldashtuff.jshiraef;

import org.newdawn.slick.*;
import org.newdawn.slick.tiled.*;
import org.lwjgl.*;

public class Zeldaesque extends BasicGame{
	
	public static final String gameName = "Zelder Clone";
	public static AppGameContainer appgc;
	private TiledMap room;
	
	private boolean[][] blocked;
	private static final int SIZE = 34;
	private float x = 34f;
	private float y = 34f;
	
	
	
	
	
	
	Animation up, down, left, right, sprite;
	
	int[] duration = {300, 300};
	
	public Zeldaesque(String gameName)
			{
			super(gameName);

			}

	@Override
	public void render(GameContainer container, Graphics g) throws SlickException {
		
		room.render(0, 0);
		
		sprite.draw(x, y);
		
		
	}
	
	private boolean isBlocked(float x, float y) {
		int xBlock = (int)x/ SIZE;
		int yBlock = (int)y/ SIZE;
		return blocked [xBlock][yBlock];
	}

	@Override
	public void init(GameContainer arg0) throws SlickException {
		
		Image[] movementUp ={new Image("res/linkWalking.png"), new Image("res/linkWalking2.png")};
		Image[] movementDown = {new Image("res/linkWalking.png"), new Image("res/linkWalking2.png")};
		
		up = new Animation(movementUp, duration, false);
		sprite =  up;
		
		try {
		room = new TiledMap("lvl/room3.tmx");
		}catch (SlickException e){
			System.out.println("error loading level");
		}
		
		
		
		
	}

	@Override
	public void update(GameContainer container, int delta) throws SlickException 
	{
		
		Input input = container.getInput();
		
		if (input.isKeyDown(Input.KEY_UP))
		{
			sprite = up;
		    sprite.update(delta);
		    
		    y -= delta * 0.1f;
		}
		else if (input.isKeyDown(Input.KEY_DOWN))
		{
			sprite = up;
		    sprite.update(delta);
		    y += delta * 0.1f;
		}
		else if (input.isKeyDown(Input.KEY_LEFT))
		{
			sprite = up;
		    sprite.update(delta);
		    x -= delta * 0.1f;
		}
		else if (input.isKeyDown(Input.KEY_RIGHT))
		{
			sprite = up;
		    sprite.update(delta);
		    x += delta * 0.1f;
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
