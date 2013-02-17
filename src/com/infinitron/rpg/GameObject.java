package com.infinitron.rpg;

import android.graphics.Canvas;

public class GameObject {
	
	protected int x, y;
	protected String name;
	protected Sprite sprite;
	
	Monster[] monsterCollection = new Monster[10];
	Item[][] itemCollection = new Item[3][10];
	
	public GameObject(String name, Sprite sprite, int x, int y) {
		this.name = name;
		this.sprite = sprite;
		this.x = x;
		this.y = y;
	}

	public void draw(Canvas canvas) {
		canvas.drawBitmap(sprite.getImage(), x, y, null);
	}
}
