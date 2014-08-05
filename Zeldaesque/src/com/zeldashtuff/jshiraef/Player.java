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
	
	Animation up, down, left, right ; //redundant variables?

	static Animation sprite;

	static Animation hit;
	
	Image heroPic1 = new Image("res/linkWalking.png");
	Image heroPic2 = new Image("res/linkWalking2.png");
	Image damagedHeroPic1 = new Image("res/damagedLinkWalking.png");
	Image damagedHeroPic2 = new Image("res/damagedLinkWalking.png");
	
	Image gameOver = new Image("res/GameOver.png");
	
	public static float playerX = 64f;
	public static float playerY = 64f;
	
	int playerTileX;
	int playerTileY;
	
	public static float playerCenterX;
	public static float playerCenterY;
	
	public boolean inBossRoom = false;
	
	public Dungeon dungeon;
	
	public static Vector2f position;
	
	private int health = 5;
	private int maxHealth = 10;
	
	private float speed = .15f;
	
	public static Vector2f distanceToBoss = new Vector2f (Boss.bossX - playerX, Boss.bossY - playerY);

	
	
	
	
	public Color healthBarColor;
	
	
	public Player (Dungeon dungeon) throws SlickException{
		
		int[] duration = {300, 300};
		int[] damageDuration = {150, 150, 150, 150};
		
		
		this.dungeon = dungeon;
		
		Image[] movementUp ={heroPic1, heroPic2};
		Image[] movementDown = {new Image("res/linkWalking.png"), new Image("res/linkWalking2.png")};
		Image[] damaged = {damagedHeroPic1, heroPic1, damagedHeroPic2, heroPic2};
		
		up = new Animation(movementUp, duration, false);
		hit = new Animation(damaged, damageDuration, false);
		sprite = up; 
		
	}
	

	
	public void render(Graphics g) {
		
		if (Zeldaesque.maxHealth > 3) {
			playerDeath();
		}
		
		playerCenterX = playerX + heroPic1.getHeight()/2;
		playerCenterY = playerY + heroPic2.getWidth()/2;
	
	
//		g.drawString(" Link's X: " + playerTileX +  "\n Link's Y: " + playerTileY, 500, 100);
//		g.drawString(" Link's X: " + playerX +  "\n Link's Y: " + playerY, 500, 400);
		
//		g.drawString("distance to Boss: " + distanceToBoss, 300, 600);
		
		sprite.draw(playerX, playerY);
		
		

		
	}
	
	public void update(Input input, int delta) throws SlickException {
		
		
		

		if(playerY < -25)
		{
			
			inBossRoom = true;
			dungeon.currentRoom = 2;
			dungeon.room = new TiledMap("lvl/" + dungeon.switchRoom(dungeon.currentRoom) + ".tmx");
			playerY = 600;
		}
		
		
		
		
//		speed *= delta; 	
		
		if (input.isKeyDown(Input.KEY_UP))
		{
			
			if (!dungeon.isBlocked(playerX, playerY - delta * 0.1f))
	          {
				if(playerY < (0 - (heroPic1.getHeight()/2)))
					playerY = 0 - (heroPic1.getHeight()/2);
				else {
                sprite.update(delta);
                playerY -= delta* speed;
				}
            }
		}
		else if (input.isKeyDown(Input.KEY_DOWN))
		{
			 if (!dungeon.isBlocked(playerX, playerY + sprite.getHeight() + delta * 0.1f))
             {
				 if(playerY > (Zeldaesque.screenHeight - 74)) //edge of screen collision detection
						playerY = Zeldaesque.screenHeight - 74; //edge of screen collision detection
					else {
						
                 sprite.update(delta);
                 playerY += delta* speed;
					}
             }
		}
		 if (input.isKeyDown(Input.KEY_LEFT))
		{
			
			if (!dungeon.isBlocked(playerX - delta * 0.1f, playerY))
            {
                sprite.update(delta);
                playerX -= delta* speed;
            }
		}
		else if (input.isKeyDown(Input.KEY_RIGHT))
		{
			
			 if (!dungeon.isBlocked(playerX + sprite.getWidth() + delta * 0.1f, playerY))
             {
                 sprite.update(delta);
                 playerX += delta* speed;
             }
		}
		
//		playerTileX = Math.round(x)/dungeon.room.getTileWidth();
//		playerTileY = Math.round(y)/dungeon.room.getTileHeight();
		
		distanceToBoss = new Vector2f (Boss.bossCenterX - playerCenterX, Boss.bossCenterY - playerCenterY);
		
		if((distanceToBoss.x < 40 && distanceToBoss.x > - 40) && (distanceToBoss.y < 60 && distanceToBoss.y > -60)) {
			System.out.println("hit damage");
			
			Boss.direction = 1;
			Boss.angry = true;
			
			Zeldaesque.maxHealth += .5;

				if(distanceToBoss.x > 35) {
					sprite = hit;
					sprite.update(delta);
	                playerX -= delta * 28f;
				}

				if(distanceToBoss.x < -35) {
					sprite = hit;
					sprite.update(delta);
					playerX += delta * 28f;
				}

				if(distanceToBoss.y > 55) {
					sprite = hit;
					sprite.update(delta);
					playerY -= delta * 28f;
				}

				if(distanceToBoss.y < -55) {
					sprite = hit;
					sprite.update(delta);
					playerY += delta * 28f;
				}
			System.out.println("Health: " + Zeldaesque.maxHealth);
		}
		
		
	}
	
	public void playerDeath() {
		gameOver.drawFlash(960/4, 704/5);
		
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
	
//public static void collisionBounce(float distanceX, float distanceY, float spriteX, float spriteY, Animation sprite, int delta) {
//		
//		if(distanceX > 35) {
//			sprite.update(delta);
//            spriteX -= delta * 35f;
//		}
//
//		else if(distanceX < -35) {
//			sprite.update(delta);
//			spriteX += delta * 35f;
//		}
//
//		else if(distanceY > 55) {
//			sprite.update(delta);
//			spriteY -= delta * 35f;
//		}
//
//		else if(distanceY < -55) {
//			sprite.update(delta);
//			spriteY += delta * 35f;
//		}
//		
//	}
	

}
