package com.infinitron.rpg;

import android.graphics.Bitmap;

public class Sprite {

	private Bitmap bitmap;
	
	public Sprite(GridSpriteSheet spriteSheet, int index) {
		this.bitmap = spriteSheet.cut(index);
	}
	
	public Sprite(GridSpriteSheet spriteSheet, int y, int x) {
		this.bitmap = spriteSheet.cut(y, x);
	}
	
	public Bitmap getImage() {
		return this.bitmap;
	}
}