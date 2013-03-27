package com.infinitron.rpg;

import android.graphics.Canvas;
/*
 * A class that represents an object in the game with attributes that give
 * an object an x and y co-ordinate in the world along with the name of the object
 * and its associated sprite for when it's drawn to the screen
 */
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

	public void draw(Canvas canvas, Level level) {
		canvas.drawBitmap(sprite.getImage(), x - (level.getScreenLeft() * Level.TILE_SIZE),  y - (level.getScreenTop() * Level.TILE_SIZE), null);
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
