package com.infinitron.rpg;

import android.graphics.Canvas;

public class GameObject {
	
	private int x, y;
	
	private Sprite sprite;
	
	public GameObject(Sprite sprite, int x, int y) {
		this.sprite = sprite;
		this.x = x;
		this.y = y;
	}

	public void draw(Canvas canvas) {
		canvas.drawBitmap(sprite.getImage(), x, y, null);
	}
}
