package com.infinitron.rpg;

import android.graphics.Bitmap;

public class SpriteSheet {
	
	private Bitmap sheet;
	private int tileWidth;
	private int tileHeight;
	private int size;
	private int rows;
	private int columns;
	
	public SpriteSheet(Bitmap sheet, int tileWidth, int tileHeight) {
		this.sheet = sheet;
		this.tileWidth = tileWidth;
		this.tileHeight = tileHeight;
		this.columns = sheet.getWidth() / tileWidth;
		this.rows = sheet.getHeight() / tileHeight;
		this.size = columns * rows;
	}
	
	public Bitmap cut(int index) {
		int selectedColumn = 0;
		if (this.columns != 0)
			selectedColumn = index % this.columns;
		
		int selectedRow = 0;
		if (this.rows != 0)
			selectedRow = (index - selectedColumn) / this.rows;
						
		Bitmap cutBitmap = Bitmap.createBitmap(this.sheet, selectedColumn * tileWidth, selectedRow * tileHeight, this.tileWidth, this.tileHeight);
		
		return cutBitmap;
	}
	
	public int size() {
		return this.size;
	}
}