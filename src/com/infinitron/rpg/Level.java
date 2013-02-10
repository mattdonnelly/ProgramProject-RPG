package com.infinitron.rpg;

import android.graphics.Canvas;

public class Level {

	private int width;
	private int height;
	public Tile tiles[][];
	
	public Level (String path, int width, int height) {
		this.width = width;
		this.height = height;
		tiles = new Tile[width][height];
		generateLevel();
	}
	
	private void generateLevel() {
		
	}
	
	public void drawLevel(Canvas canvas) {
		
	}
	
}
