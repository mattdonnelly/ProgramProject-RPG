package com.infinitron.rpg;

import android.graphics.Bitmap;

public class SpriteSheet {

	protected Bitmap sheet;
	
	public SpriteSheet(Bitmap sheet) {
		this.sheet = sheet;
	}
	
	public Bitmap cut(int xpos, int ypos, int width, int height) {		
		return Bitmap.createBitmap(this.sheet, xpos, ypos, width, height);
	}
	
	public int getWidth() {
		return this.sheet.getWidth();
	}
	
	public int getHeight() {
		return this.sheet.getHeight();
	}
}
