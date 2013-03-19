package com.infinitron.rpg;

import android.graphics.Canvas;

public class GameObject {
	
	protected int x, y;
	protected String name;
	protected Sprite sprite;
	
	public GameObject(String _name, Sprite _sprite, int _x, int _y) {
		this.name = _name;
		this.sprite = _sprite;
		this.x = _x;
		this.y = _y;
	}

	public void draw(Canvas canvas) {
		canvas.drawBitmap(sprite.getImage(), x, y, null);
	}
	
	public void loadImage(){
		
	}
	public int getXPos(){
		return this.x;
	}
	public int getYPos(){
		return this.y;
	}
	
	
	public void update(){
	}
	
	public void move(){
		
	}
	
	public void draw(){
		
	}

	public void doTouch(String s) {
	}
}
