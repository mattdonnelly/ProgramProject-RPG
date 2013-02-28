package com.infinitron.rpg;

import android.graphics.Bitmap;
import android.graphics.Canvas;

public class Sprite {

	private Bitmap bitmap;
	
	public Sprite(SpriteSheet spriteSheet, int x, int y, int width, int height) {
		this.bitmap = spriteSheet.cut(x, y, width, height);
	}
	
	public Sprite(GridSpriteSheet spriteSheet, int index) {
		this.bitmap = spriteSheet.cut(index);
	}
	
	public Sprite(GridSpriteSheet spriteSheet, int x, int y) {
		this.bitmap = spriteSheet.cut(x, y);
	}
	
	public Bitmap getImage() {
		return this.bitmap;
	}
	
	public void draw(int x, int y, Canvas canvas) {
		canvas.drawBitmap(this.bitmap, (float)x, (float)y, null);
	}
}