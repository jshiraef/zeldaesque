package com.zeldashtuff.jshiraef;

import static org.lwjgl.opengl.GL11.glPopMatrix;
import static org.lwjgl.opengl.GL11.glPushMatrix;
import static org.lwjgl.opengl.GL11.glRectd;
import static org.lwjgl.opengl.GL11.glRotated;
import static org.lwjgl.opengl.GL11.glTranslatef;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Set;

import org.jbox2d.collision.shapes.EdgeShape;
import org.jbox2d.collision.shapes.PolygonShape;
import org.jbox2d.common.Vec2;
import org.jbox2d.dynamics.Body;
import org.jbox2d.dynamics.BodyDef;
import org.jbox2d.dynamics.BodyType;
import org.jbox2d.dynamics.FixtureDef;
import org.jbox2d.dynamics.World;
import org.jbox2d.dynamics.joints.Joint;
import org.jbox2d.dynamics.joints.RevoluteJointDef;
import org.newdawn.slick.Animation;
import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.SpriteSheet;
import org.newdawn.slick.geom.Polygon;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.geom.Shape;
import org.newdawn.slick.tiled.TiledMap;

public class Dungeon {
	
	public static TiledMap room;
//	public static int currentRoom = 0;
	
	public Player player;
	public Boss boss;
	
	
	// old guy sample
	Image oldPriest = new Image("res/priest.png");
	Image priestWalkingDown = oldPriest.getSubImage(1, 1, 240, 104);
	Image priestIdle = oldPriest.getSubImage(1, 1, 60, 104);
	Animation priest, priestDown, lazyPriest;
	 
	Image chainLink = new Image("res/links.png");
	
	Room currentRoom;
	
	Room firstRoom;
	Room bossRoom;
	Room baddieRoom;
	Room blockRoom;
	
	public Color polygonColor = Color.white;
	
	 private Body m_bodyList;
	 private Joint m_jointList;

	  private int m_bodyCount;
	  private int m_jointCount;

	  private World world;
	  
	  private final Vec2 gravity = new Vec2(0, 1f);
	
	  private LinkedList<Polygon> chainLinks;
	  
	  private static Set<Body> bodies = new HashSet<Body>();
	  
	  BodyDef bd;
	  Body playerBody;
	  PolygonShape playerBoxShape, chainLinkShape;
	  FixtureDef chainProperties;
	  RevoluteJointDef joint;
	  
	  float anchorY;
	
	
	
	
	public Dungeon() throws SlickException {
		
		
		boss = new Boss(this);
		player = new Player(this);
		
		
		firstRoom = new Room("room5");
		bossRoom = new Room("room4");
		baddieRoom = new Room("room6");
		blockRoom = new Room("MovableBlockRoom");
		
		this.world = new World(gravity);
	
		this.priestDown = new Animation(new SpriteSheet(priestWalkingDown, 60, 104), 200);
		this.lazyPriest = new Animation(new SpriteSheet(priestIdle, 60, 104), 200);
		
		
		currentRoom = firstRoom;
		priest = lazyPriest;
		
		this.chainLinks = new LinkedList<Polygon>();
		
		 playerBody = null;
		
			bd = new BodyDef();
			playerBody = world.createBody(bd);
			playerBoxShape = new PolygonShape();
			playerBoxShape.setAsBox(1.6f, 1.6f);
			playerBody.createFixture(playerBoxShape, 0.0f);
		
		
			chainLinkShape = new PolygonShape();
			chainLinkShape.setAsBox(0.6f, 0.125f);
			chainProperties = new FixtureDef();
			chainProperties.shape = chainLinkShape;
			chainProperties.density = 20.0f;
			chainProperties.friction = 0.2f;
			joint = new RevoluteJointDef();
			joint.localAnchorA.set(player.getX()/30, player.getY()/30);
			joint.collideConnected = false;
			anchorY = player.getY()/30f;
			Body prevBody = playerBody;
			
			for (int i = 0; i < 5; ++i) {
				BodyDef bd = new BodyDef();
				bd.type = BodyType.DYNAMIC;
				bd.position.set(0.5f + i, anchorY);
				Body body = world.createBody(bd);
			
				// tying slick image to jbox2d shape
//				Polygon link = new Polygon();
//				link.setX(bd.position.x * 30);
//				link.setY(bd.position.y);
//				chainLinks.add(link);
//				System.out.println("the position of the link in the chain is: " + body.getPosition().x + " , " + body.getPosition().y);
				
				body.createFixture(chainProperties);
				Vec2 anchor = new Vec2(i, anchorY);
				joint.initialize(prevBody, body, anchor);
				world.createJoint(joint);
				prevBody = body;
				bodies.add(body);
			}
		
		
	}
	
	
	public void render(Graphics g)	{
		
		currentRoom.room.render(0, 0);
		
		if(player.inBossRoom)
		{
			bossRoom.loadRoom();
			boss.render(g);
		} 
		
		player.render(g);
		
		if(player.inBaddieRoom)
		{
			
	//		boss.render(g);
			currentRoom.bc.render(g);
		}
		
		if(player.inBlockRoom)
		{
			blockRoom.loadRoom();
			g.setColor(Color.white);
			g.drawString("use G to grab and move blocks!", 300, 100);
		}
		
//		priest.draw(player.x + 100, player.y + 50);
		
//		for(int i = 0; i < chainLinks.size(); i++) 
//		{
//			
//			Polygon p = chainLinks.get(i);
//			chainLink.draw(chainLinks.get(i).getX(), chainLinks.get(i).getY());
//		}
		
//		g.setColor(polygonColor);
//		g.draw(q);
		
		for (int i = 0; i < bodies.size(); i++) 
		{
			for(Body body: bodies)
			{
			if(body.getType() == BodyType.DYNAMIC) 
			{
				Vec2 bodyPosition = body.getPosition().mul(30);
				Rectangle link = new Rectangle(bodyPosition.x, bodyPosition.y, 36, 7.5f);
				System.out.println("the converted box 2d position of the body is: "  + bodyPosition.x + " , " + bodyPosition.y);
//				System.out.println("the actual box 2d position of the body is: "  + body.getPosition().x + " , " + body.getPosition().y);
//				g.rotate(0, 0, (float) Math.toDegrees(body.getAngle()));
				chainLink.draw(player.getX() + 36 * i, (float) (player.getY() + 7.5 * i));
				chainLink.setRotation((float) Math.toDegrees(body.getAngle()));
			}
			}
			
		}
		
		
	}
	
	public void update (GameContainer container, int delta) throws SlickException {
		anchorY = player.getY()/30f;
		bd.position.set(player.getX()/30, player.getY()/30);
		
		if(player.inBossRoom)
		{
			boss.update(delta);
		} 
		
		if(player.inBaddieRoom) {
			baddieRoom.bc.update(delta);
		}
		
		if(player.inBaddieRoom && player.enteredNewRoom) {
			baddieRoom.bc.addBaddie(new Baddie(currentRoom.getPixelWidth()/3, currentRoom.getPixelHeight()/3, currentRoom.getPixelWidth()/3 + 25, currentRoom.getPixelHeight()/3 + 34, this));
			baddieRoom.bc.addBaddie(new Baddie(currentRoom.getPixelWidth()/2, currentRoom.getPixelHeight()/2, (currentRoom.getPixelWidth()/2) + 25, (currentRoom.getPixelHeight()/3) + 34, this));
			baddieRoom.bc.addBaddie(new Baddie((currentRoom.getPixelWidth()/3) * 2, currentRoom.getPixelHeight()/4, (currentRoom.getPixelWidth()/3) * 2 + 25, currentRoom.getPixelHeight()/4 + 34, this));
			player.enteredNewRoom = false;
		}
		
//		System.out.println("Tile ID: " room.getTileID());
		
		player.update(container.getInput(), delta);
		
		
		if(container.getInput().isKeyDown(Input.KEY_DOWN))
		{
			priest = priestDown;
		}
		else priest = lazyPriest;
		
		world.step(.01f, 8, 3);
		
	}
	
	
	
	
	
	

}
