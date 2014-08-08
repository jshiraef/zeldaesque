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
	
	

	static Animation sprite, up, down, left, right, hit; //redundant variables?


	
	Image heroPic1 = new Image("res/linkWalking.png");
	Image heroPic2 = new Image("res/linkWalking2.png");
	Image damagedHeroPic1 = new Image("res/damagedLinkWalking.png");
	Image damagedHeroPic2 = new Image("res/damagedLinkWalking.png");
	
	Image gameOver = new Image("res/GameOver.png");
	
	public float x = 64f;
	public float y = 64f;

	
	int playerTileX;
	int playerTileY;
	
	public float playerCenterX;
	public float playerCenterY;
	
	public boolean inBossRoom = false;
	
	public Dungeon dungeon;
	public ProjectileController pc;
	
	public Direction playerDirection;
	
	private int projectileCooldown = 0;
	public boolean hasBeenShot = false;
	
	private float speed = .15f;
	
	public float health = 1, maxHealth = (float) 1.5, healthBarX = 100, healthBarY = 100, healthBarWidth = 200, healthBarHeight = 10;
	
	public Color healthBarColor = Color.red;
	
	
	public Player (Dungeon dungeon) throws SlickException{
		
		int[] duration = {300, 300};
		int[] damageDuration = {150, 150, 150, 150};
		
		pc = new ProjectileController();
		
		
		
		
		this.dungeon = dungeon;
		
		Image[] movementUp ={heroPic1, heroPic2};
		Image[] movementDown = {new Image("res/linkWalking.png"), new Image("res/linkWalking2.png")};
		Image[] damaged = {damagedHeroPic1, heroPic1, damagedHeroPic2, heroPic2};
		
		up = new Animation(movementUp, duration, false);
		hit = new Animation(damaged, damageDuration, false);
		sprite = up; 
		
	}
	

	
	public void render(Graphics g) {
		
		if (maxHealth > 3) {
			playerDeath();
		}
		
		drawHealthBar(g);
		
		playerCenterX = x + heroPic1.getHeight()/2;
		playerCenterY = y + heroPic2.getWidth()/2;
	
	
//		g.drawString(" Link's X: " + playerTileX +  "\n Link's Y: " + playerTileY, 500, 100);
//		g.drawString(" Link's X: " + playerX +  "\n Link's Y: " + playerY, 500, 400);
		
//		g.drawString("distance to Boss: " + distanceToBoss, 300, 600);
		
		sprite.draw(x, y);
		pc.render(g);
		
		

		
	}
	
	public void update(Input input, int delta) throws SlickException {
		
		pc.update();
		

		if(y < -25)
		{
			
			inBossRoom = true;
			dungeon.currentRoom = 2;
			dungeon.room = new TiledMap("lvl/" + dungeon.switchRoom(dungeon.currentRoom) + ".tmx");
			y = 600;
		}
		
		
		
		
//		speed *= delta; 	
		
		if (input.isKeyDown(Input.KEY_UP))
		{
			playerDirection = Direction.NORTH;
			
			if (!dungeon.isBlocked(x, y - delta * 0.1f))
	          {
				if(y < (0 - (heroPic1.getHeight()/2)))
					y = 0 - (heroPic1.getHeight()/2);
				else {
                sprite.update(delta);
                y -= delta* speed;
				}
            }
		}
		else if (input.isKeyDown(Input.KEY_DOWN))
		{
			playerDirection = Direction.SOUTH;
			
			 if (!dungeon.isBlocked(x, y + sprite.getHeight() + delta * 0.1f))
             {
				 if(y > (Zeldaesque.screenHeight - 74)) //edge of screen collision detection
						y = Zeldaesque.screenHeight - 74; //edge of screen collision detection
					else {
						
                 sprite.update(delta);
                 y += delta* speed;
					}
             }
		}
		 if (input.isKeyDown(Input.KEY_LEFT))
		{
			 playerDirection = Direction.EAST;
			 
			if (!dungeon.isBlocked(x - delta * 0.1f, y))
            {
                sprite.update(delta);
                x -= delta* speed;
            }
		}
		else if (input.isKeyDown(Input.KEY_RIGHT))
		{
			playerDirection = Direction.WEST;
			
			 if (!dungeon.isBlocked(x + sprite.getWidth() + delta * 0.1f, y))
             {
                 sprite.update(delta);
                 x += delta* speed;
             }
		}
		
//		playerTileX = Math.round(x)/dungeon.room.getTileWidth();
//		playerTileY = Math.round(y)/dungeon.room.getTileHeight();
		 
		 
		 
		 for(int i = 0; i < pc.getQuiverSize(); i++	) {
			 
			 Vector2f distanceToArrow = new Vector2f(dungeon.boss.bossCenterX - ProjectileController.TempArrow.getX(), dungeon.boss.bossCenterY - ProjectileController.TempArrow.getY());
//			 System.out.println("distance to arrow" + distanceToArrow);
			 
			 if ((distanceToArrow.x < 40 && distanceToArrow.x > -40) && (distanceToArrow.y < 60 && distanceToArrow.y > -60))
			 {
				 System.out.println("a HIT!!");
			 
			 	dungeon.boss.hit = true;
			 	
				 dungeon.boss.bossMaxHealth += .00001;
			 }
			 else dungeon.boss.hit = true;
		 		
		 }
		
		 
		
		Vector2f distanceToBoss = new Vector2f (dungeon.boss.bossCenterX - playerCenterX, dungeon.boss.bossCenterY - playerCenterY);
		
		if((distanceToBoss.x < 40 && distanceToBoss.x > - 40) && (distanceToBoss.y < 60 && distanceToBoss.y > -60)) {
			System.out.println("hit damage");
			
			Boss.angry = true;
			
			maxHealth += .5;

				if(distanceToBoss.x > 35) {
					sprite = hit;
					sprite.update(delta);
	                x -= delta * 28f;
				}

				if(distanceToBoss.x < -35) {
					sprite = hit;
					sprite.update(delta);
					x += delta * 28f;
				}

				if(distanceToBoss.y > 55) {
					sprite = hit;
					sprite.update(delta);
					y -= delta * 28f;
				}

				if(distanceToBoss.y < -55) {
					sprite = hit;
					sprite.update(delta);
					y += delta * 28f;
				}
			System.out.println("Health: " + maxHealth);
		}
		
		if (input.isKeyDown(Input.KEY_SPACE)) {
//			Arrow.loaded = true;
			
			if (projectileCooldown <= 0) {
			pc.addArrow(new Arrow(x, y, this, this.playerDirection));
			projectileCooldown = 60;
			}
			
		}
		
		if(projectileCooldown > 0){
			projectileCooldown -= delta;
		System.out.println("projectile cooldown " + projectileCooldown);
		}
		
	}
	
	public void playerDeath() {
		gameOver.drawFlash(960/4, 704/5);
		
	}
	
	public void drawHealthBar(Graphics g) {
		float healthScale = health/maxHealth;
		g.setColor(healthBarColor);
		g.fillRect(healthBarX, healthBarY, healthBarWidth * healthScale, healthBarHeight);
		g.drawString("Link's HP", healthBarX, healthBarY - 20);
	}

	
	public float getX() {
		return x;
	}
	
	public float getY() {
		return y;
	}
	
	public void setX() {
		this.x = x;
	}
	
	public void setY() {
		this.y = y;
	}
	


}
