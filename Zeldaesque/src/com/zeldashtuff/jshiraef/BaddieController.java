package com.zeldashtuff.jshiraef;

import java.util.LinkedList;

import org.newdawn.slick.Graphics;

public class BaddieController {

	private LinkedList<Baddie> minions = new LinkedList<Baddie>();
	
	Baddie TempBaddie;
	
	public BaddieController() {
		
	}
	
	public void update() {
		
		for (int i = 0; i < minions.size(); i++) {
			TempBaddie = minions.get(i);
			
			TempBaddie.update();
		}
	}
	
	public void render(Graphics g) {
		for (int i = 0; i < minions.size(); i++) {
			minions.get(i).render(g);
		}
	}
	
	public void addBaddie(Baddie block) {
		minions.add(block);
	}
	
	public void removeBaddie(Baddie block) {
		minions.remove(block);
	}
}
