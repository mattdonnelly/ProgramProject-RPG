package com.infinitron.rpg;

import android.graphics.Canvas; 

public class Tile {
	
	private Sprite tile;
	private boolean isSolid;

	public Tile(Sprite tile, boolean isSolid) {
		this.tile = tile;
		this.isSolid = isSolid;
	}
	
	public void draw(Canvas canvas, int x, int y) {
		tile.draw(x, y, canvas);
	}
	
	public boolean isSolid() {
		return isSolid;
	}
}