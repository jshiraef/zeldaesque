package com.zeldashtuff.jshiraef;



import org.newdawn.slick.Animation;
import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Vector2f;
import org.newdawn.slick.tiled.TiledMap;

public class Player {
	
	Animation up, down, left, right, sprite ; //redundant variables?
	
	Image heroPic1 = new Image("res/linkWalking.png");
	Image heroPic2 = new Image("res/linkWalking2.png");
	
	Image gameOver = new Image("res/GameOver.png");
	
	public float x = 64f;
	public float y = 64f;
	
	int playerTileX;
	int playerTileY;
	
	public static float playerCenterX;
	public static float playerCenterY;
	
	public boolean inBossRoom = false;
	
	public Dungeon dungeon;
	
	public Vector2f position;
	
	private int health = 5;
	private int maxHealth = 10;
	
	Vector2f distanceToBoss = new Vector2f (Boss.bossX - x, Boss.bossY - y);

	
	
	
	
	public Color healthBarColor;
	
	
	public Player (Dungeon dungeon) throws SlickException{
		
		int[] duration = {300, 300};
		
		this.dungeon = dungeon;
		
		Image[] movementUp ={new Image("res/linkWalking.png"), new Image("res/linkWalking2.png")};
		Image[] movementDown = {new Image("res/linkWalking.png"), new Image("res/linkWalking2.png")};
		
		up = new Animation(movementUp, duration, false);
		sprite = up; 
		
	}
	

	
	public void render(Graphics g) {
		
		playerCenterX = x + heroPic1.getHeight()/2;
		playerCenterY = y + heroPic2.getWidth()/2;
	
	
//		g.drawString(" Link's X: " + playerTileX +  "\n Link's Y: " + playerTileY, 500, 100);
		g.drawString(" Link's X: " + x +  "\n Link's Y: " + y, 500, 400);
		
		g.drawString("distance to Boss: " + distanceToBoss, 300, 600);
		
		sprite.draw(x, y);
		
		if(Zeldaesque.maxHealth > 3) {
			gameOver.drawCentered(960/2, 704/2);
			System.out.println("It should be showing");
		}

		
	}
	
	public void update(Input input, int delta) throws SlickException {
		
		
		

		if(y < -25)
		{
			
			inBossRoom = true;
			dungeon.currentRoom = 2;
			dungeon.room = new TiledMap("lvl/" + dungeon.switchRoom(dungeon.currentRoom) + ".tmx");
			y = 600;
		}
		
		
		
		if (input.isKeyDown(Input.KEY_UP))
		{
			sprite = up;
			if (!dungeon.isBlocked(x, y - delta * 0.1f))
	          {
                sprite.update(delta);
                
                y -= delta * 0.1f;
            }
		}
		else if (input.isKeyDown(Input.KEY_DOWN))
		{
			 if (!dungeon.isBlocked(x, y + sprite.getHeight() + delta * 0.1f))
             {
                 sprite.update(delta);
                 y += delta * 0.1f;
             }
		}
		else if (input.isKeyDown(Input.KEY_LEFT))
		{
			sprite = up;
			if (!dungeon.isBlocked(x - delta * 0.1f, y))
            {
                sprite.update(delta);
                x -= delta * 0.1f;
            }
		}
		else if (input.isKeyDown(Input.KEY_RIGHT))
		{
			sprite = up;
			 if (!dungeon.isBlocked(x + sprite.getWidth() + delta * 0.1f, y))
             {
                 sprite.update(delta);
                 x += delta * 0.1f;
             }
		}
		
//		playerTileX = Math.round(x)/dungeon.room.getTileWidth();
//		playerTileY = Math.round(y)/dungeon.room.getTileHeight();
		
		distanceToBoss = new Vector2f (Boss.bossCenterX - playerCenterX, Boss.bossCenterY - playerCenterY);
		
		if((distanceToBoss.x < 40 && distanceToBoss.x > - 40) && (distanceToBoss.y < 60 && distanceToBoss.y > -60)) {
			System.out.println("hit damage");
			
			Zeldaesque.maxHealth += .5;

				if(distanceToBoss.x > 35) {
					sprite.update(delta);
	                x -= delta * 35f;
				}

				if(distanceToBoss.x < -35) {
					sprite.update(delta);
					x += delta * 35f;
				}

				if(distanceToBoss.y > 55) {
					sprite.update(delta);
					y -= delta * 35f;
				}

				if(distanceToBoss.y < -55) {
					sprite.update(delta);
					y += delta * 35f;
				}
			System.out.println("Health: " + Zeldaesque.maxHealth);
		}
		
		
		
	}
	
	
	
//	public Vector2f CollisionCheck(float x, float y) {
//		 int playerTileX = Math.round(x)/dungeon.room.getTileWidth();
//		 int playerTileY = Math.round(y)/dungeon.room.getTileHeight();
//		 
//		 position = CollisionCoordinates(playerTileX, playerTileY);
//		 
//		 if(playerTileX == Boss.bossX || playerTileY == Boss.bossY) {
//				System.out.println("hit damage");
//			}
//		 
//		 return null;
//	}
//
//	private Vector2f CollisionCoordinates(int playerTileX, int playerTileY) {
//		// TODO Auto-generated method stub
//		return null;
//	}
	
	

}
