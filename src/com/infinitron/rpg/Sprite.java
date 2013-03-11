package com.infinitron.rpg;


import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;

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
		canvas.drawBitmap(this.bitmap, x, y, null);
	}
	public void	draw(Rect src, int bottom, int left, int right, int top, Paint paint, Canvas canvas) {
		Rect dest = new Rect(bottom, left, right, top);
		canvas.drawBitmap(this.bitmap, src, dest, paint);
	}
}