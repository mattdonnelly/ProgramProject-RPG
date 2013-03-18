package com.infinitron.rpg;

public class Tile {
	
	public static final int SIZE = 16;
	
	private boolean is_solid;
	private String name;
			
	public Tile(String name, boolean is_solid) {
		this.name = name;
		this.is_solid = is_solid;
	}
	
	public boolean isSolid() {
		return is_solid;
	}
	
}
