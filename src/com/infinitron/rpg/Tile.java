package com.infinitron.rpg;

import android.graphics.Canvas;

public class Tile {
	
	public static final int SIZE = 16;
	
	private Sprite sprite;
	private boolean is_solid;
	private int size;
			
	public Tile(Sprite sprite, int size, boolean is_solid) {
		this.sprite = sprite;
		this.size = size;
		this.is_solid = is_solid;
	}
	
	public void draw(Canvas canvas, int y_pos, int x_pos) {
		if (x_pos * size >= 0 && x_pos * size < canvas.getWidth() && y_pos * size >= 0 && y_pos * size < canvas.getHeight()) 
			canvas.drawBitmap(sprite.getImage(), x_pos * size, y_pos * size, null);
	}
	
	public boolean solid() {
		return is_solid;
	}
	
}
