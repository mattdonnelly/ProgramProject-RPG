package com.infinitron.rpg;

import android.graphics.Bitmap;

public class GridSpriteSheet extends SpriteSheet {
	
	private int tileWidth;
	private int tileHeight;
	private int size;
	private int rows;
	private int columns;
	
	public GridSpriteSheet(Bitmap sheet, int tileWidth, int tileHeight) {
		super(sheet);
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
		return Bitmap.createBitmap(this.sheet, selectedColumn * tileWidth, selectedRow * tileHeight, this.tileWidth, this.tileHeight);
	}
	
	public Bitmap cut(int row, int col) {
		return Bitmap.createBitmap(sheet, col * tileWidth, row * tileHeight, tileWidth, tileHeight);
	}
	
	public int size() {
		return this.size;
	}
}