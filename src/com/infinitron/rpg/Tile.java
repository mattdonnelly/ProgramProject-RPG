package com.infinitron.rpg;

public class Tile {
	
	private Sprite sprite;
	private boolean is_solid;

	public Tile(Sprite sprite, boolean is_solid) {
		this.sprite = sprite;
		this.is_solid = is_solid;
	}
	
	public void draw() {
		
	}
	
	public boolean solid() {
		return is_solid;
	}
	
}
