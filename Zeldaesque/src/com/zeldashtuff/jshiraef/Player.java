package com.zeldashtuff.jshiraef;



import org.newdawn.slick.Animation;
import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.SpriteSheet;
import org.newdawn.slick.geom.Vector2f;
import org.newdawn.slick.tiled.TiledMap;


public class Player {
	
	

	static Animation sprite, up, down, left, right, hit, hitDown, hitUp, hitRight, hitLeft; //redundant variables?


	
	
	Image damagedHeroPic1 = new Image("res/damagedLinkWalking.png");
	Image damagedHeroPic2 = new Image("res/damagedLinkWalking.png");
	
	Image fullLinkSpriteSheet = new Image("res/LinkSpriteSheet.png");
	Image damagedLink = new Image("res/damagedLinkSpriteSheet.png");
	
	Image gameOver = new Image("res/GameOver.png");
	
	Image damagedDown = damagedLink.getSubImage(1, 1, 199, 84);
	Image damagedLeft = damagedLink.getSubImage(1, 86, 199, 84);
	Image damagedRight = damagedLink.getSubImage(1, 171, 199, 84);
	Image damagedUp = damagedLink.getSubImage(1, 255, 199, 84);
	
	Image walkingDown = fullLinkSpriteSheet.getSubImage(1, 1, 199, 84);
	Image walkingLeft = fullLinkSpriteSheet.getSubImage(1, 86, 199, 84);
	Image walkingRight = fullLinkSpriteSheet.getSubImage(1, 171, 199, 84);
	Image walkingUp = fullLinkSpriteSheet.getSubImage(1, 255, 199, 84);
	
	public float x = 64f;
	public float y = 64f;

	
	int playerTileX;
	int playerTileY;
	
	public float playerCenterX;
	public float playerCenterY;
	
	public boolean inBossRoom = false;
	public boolean inBadRoom = false;
	public boolean playerHit = false;
	public boolean enteredNewRoom = false;
	
	public Dungeon dungeon;
	;
	
	public Direction playerDirection;
	
	private int projectileCooldown = 0;
	public int bossDamageCooldown = 0;
	public int playerDamageCooldown = 0;
	
	private float speed = .2f;
	
	public float health = 1, maxHealth = (float) 1.5, healthBarX = 100, healthBarY = 100, healthBarWidth = 200, healthBarHeight = 10;
	
	public Color healthBarColor = Color.red;
	
	
	public Player (Dungeon dungeon) throws SlickException{
		
		int[] duration = {300, 300};
		int[] damageDuration = {150, 150, 150, 150};
		
		this.dungeon = dungeon;
		
		
		Image[] movementDown = {new Image("res/linkWalking.png"), new Image("res/linkWalking2.png")};
//		Image[] damaged = {damagedHeroPic1, heroPic1, damagedHeroPic2, heroPic2};
	
		
		this.down = new Animation(new SpriteSheet(walkingDown, 49, 84), 250);
		this.up = new Animation(new SpriteSheet(walkingUp, 49, 84), 250);
		this.left = new Animation(new SpriteSheet(walkingLeft, 49, 84), 250);
		this.right = new Animation(new SpriteSheet(walkingRight, 49, 84), 250);
		
		
		hitDown = new Animation(new SpriteSheet(damagedDown, 49, 84), 250);
		hitUp = new Animation(new SpriteSheet(damagedUp, 49, 84), 250);
		hitLeft = new Animation(new SpriteSheet(damagedLeft, 49, 84), 250);
		hitRight = new Animation(new SpriteSheet(damagedRight, 49, 84), 250);
		
		
		sprite = down; 
		
	}
	

	
	public void render(Graphics g) {
		
		if (healthBarWidth <= 0) {
			playerDeath();
		}
		
		drawHealthBar(g);
		
		playerCenterX = x + 25;
		playerCenterY = y + 50;
	
	
//		g.drawString(" Link's center X: " + playerCenterX +  "\n Link's center Y: " + playerCenterY, 500, 100);
//		g.drawString(" Boss center X: " + dungeon.boss.bossCenterX +  "\n boss's center Y: " + dungeon.boss.bossCenterY, 500, 150);
		g.drawString(" Link's X: " + x +  "\n Link's Y: " + y, 500, 400);
		
		
		
		sprite.draw(x, y);
		dungeon.currentRoom.pc.render(g);
		
		
		
	}
	
	
	public void update(Input input, int delta) throws SlickException {
		
		dungeon.currentRoom.pc.update(delta);
		

		if(y < -25)
		{
			
			inBossRoom = true;
			dungeon.currentRoom = dungeon.bossRoom;
			y = 600;
		}
		
		if(x > 890)
		{
			
			inBadRoom = true;
			enteredNewRoom = true;
			dungeon.currentRoom = dungeon.baddieRoom;
			x = 10;
		}
		
		
		
		 
//		speed *= delta; 	
		
		if (input.isKeyDown(Input.KEY_UP))
		{
			playerDirection = Direction.NORTH;
			
			if(playerHit)
				sprite = hitUp;
			else sprite = up;
			
			if (!dungeon.currentRoom.isBlocked(playerCenterX, playerCenterY - sprite.getHeight()/3 - delta * 0.1f))
	          {
				if(y < (0 - (walkingDown.getHeight()/2))) //edge of screen collision detection
					y = 0 - (walkingDown.getHeight()/2); //edge of screen collision detection
				else {
                sprite.update(delta);
                y -= delta* speed;
				}
            }
		}
		
		else if (input.isKeyDown(Input.KEY_DOWN))
		{
			
			playerDirection = Direction.SOUTH;
			
			if(playerHit)
				sprite = hitDown;
			else sprite = down;
			
			 if (!dungeon.currentRoom.isBlocked(playerCenterX, playerCenterY + sprite.getHeight()/3 + delta * 0.1f))
             {
				 if(y > (Zeldaesque.screenHeight - 90)) //edge of screen collision detection
						y = Zeldaesque.screenHeight - 90; //edge of screen collision detection
					else {
						
                 sprite.update(delta);
                 y += delta* speed;
					}
             }
		}
		
		 if (input.isKeyDown(Input.KEY_LEFT))
		{
			 playerDirection = Direction.EAST;

			 if(playerHit)
					sprite = hitLeft;
				else sprite = left;
			 
			if (!dungeon.currentRoom.isBlocked(playerCenterX - sprite.getWidth()/3- delta * 0.1f, playerCenterY))
            {
                sprite.update(delta);
                x -= delta* speed;
            }
		}
		 
		else if (input.isKeyDown(Input.KEY_RIGHT))
		{
			playerDirection = Direction.WEST;
			
			if(playerHit)
				sprite = hitRight;
			else sprite = right;
			
			 if (!dungeon.currentRoom.isBlocked(playerCenterX + sprite.getWidth()/3 + delta * 0.1f, playerCenterY))
             {
                 sprite.update(delta);
                 x += delta * speed;
             }
		}
		 
		 
		
		playerTileX = Math.round(playerCenterX)/dungeon.currentRoom.room.getTileWidth();
		playerTileY = Math.round(playerCenterY)/dungeon.currentRoom.room.getTileHeight();
		

		 
		 
		 
		 
		 for(int i = 0; i < dungeon.currentRoom.pc.getQuiverSize(); i++	) {
			 
			 Vector2f distanceToArrow = new Vector2f(dungeon.boss.bossCenterX - ProjectileController.TempArrow.getX(), dungeon.boss.bossCenterY - dungeon.currentRoom.pc.TempArrow.getY());
//			 System.out.println("distance to arrow" + distanceToArrow);
			 
			 if ((distanceToArrow.x < dungeon.boss.bossCollisionRadius.x && distanceToArrow.x > -dungeon.boss.bossCollisionRadius.x) && (distanceToArrow.y < dungeon.boss.bossCollisionRadius.y && distanceToArrow.y > -dungeon.boss.bossCollisionRadius.y))
			 {
				 System.out.println("a HIT!!");
			 
			 	dungeon.boss.hit = true;
			 	
			 	if (bossDamageCooldown <= 0)
				 dungeon.boss.bossHealthBarWidth -= 25;
			 	
			 		bossDamageCooldown = 300;
			 }
			 else dungeon.boss.hit = false;
		 		
		 }
		
		 
//collision with enemy starts here
		 
		 
		Vector2f distanceToBoss = new Vector2f (dungeon.boss.bossCenterX - playerCenterX, dungeon.boss.bossCenterY - playerCenterY);
		
		if((distanceToBoss.x < dungeon.boss.bossCollisionRadius.x && distanceToBoss.x > - dungeon.boss.bossCollisionRadius.x) && (distanceToBoss.y < dungeon.boss.bossCollisionRadius.y && distanceToBoss.y > - dungeon.boss.bossCollisionRadius.y)) {
			System.out.println("hit damage");
			
			Boss.angry = true;
			Boss.direction = 1;
			
			if(playerDamageCooldown == 0) {
				healthBarWidth -= 50;
			}
			playerDamageCooldown = 1000;
			

				if(distanceToBoss.x > dungeon.boss.bossCollisionRadius.x - 5) {
					sprite = hitLeft;
					sprite.update(delta);
	                x -= delta * 33f;
				}

				if(distanceToBoss.x < - dungeon.boss.bossCollisionRadius.x + 5) {
					sprite = hitRight;
					sprite.update(delta);
					x += delta * 33f;
				}

				if(distanceToBoss.y > dungeon.boss.bossCollisionRadius.y - 5) {
					sprite = hitUp;
					sprite.update(delta);
					y -= delta * 33f;
				}

				if(distanceToBoss.y < - dungeon.boss.bossCollisionRadius.y + 5) {
					sprite = hitDown;
					sprite.update(delta);
					y += delta * 33f;
				}
			System.out.println("Health: " + healthBarWidth);
		}
		
// collision with enemy ends here
		
		if (input.isKeyDown(Input.KEY_SPACE)) {
			
			if (projectileCooldown <= 0) {
			dungeon.currentRoom.pc.addArrow(new Arrow(x, y, this, this.playerDirection));
			projectileCooldown = 300;
			}
			
		}
		
		if(projectileCooldown > 0){
			projectileCooldown -= delta;
		}
		
		if(bossDamageCooldown > 0) {
			bossDamageCooldown -= delta;
		}
		
		if(playerDamageCooldown > 0) {
			playerHit = true;
			playerDamageCooldown -= delta;
			System.out.println("Player damage cooldown " +  playerDamageCooldown);
		}

		if(playerDamageCooldown == 0)
			playerHit = false;
			
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
