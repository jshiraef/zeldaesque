package com.zeldashtuff.jshiraef;

import java.util.LinkedList;

import org.newdawn.slick.Graphics;



public class ProjectileController {
	
private LinkedList<Arrow> quiver = new LinkedList<Arrow>();
	
	Arrow TempArrow;
	
	Zeldaesque game;
	
	public ProjectileController () {
	
		
	}
	
	public void update() {
		 for(int i = 0; i < quiver.size(); i++) {
			 TempArrow = quiver.get(i);
			 
			 if(TempArrow.getY() < 0)
				 removeArrow(TempArrow);
			 
			 TempArrow.update();
		 }
	}
	
	public void render (Graphics g) {
		for (int i = 0; i < quiver.size(); i++) {
			quiver.get(i).render(g);
		}
	}
	
	public void addArrow(Arrow block) {
		quiver.add(block);
	}
	
	public void removeArrow(Arrow block) {
		quiver.remove(block);
	}

}


