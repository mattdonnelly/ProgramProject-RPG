package com.infinitron.rpg;

import android.graphics.BitmapFactory;
import android.graphics.Canvas;

public class GameObject {
	
	protected int x, y;
	protected String name;
	protected Sprite sprite;
	
	Monster[] monsterCollection = new Monster[10];
	Item[][] itemCollection = new Item[3][10];
	
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
	
	public void update(){
		
	}
	
	public void move(){
		
	}
	
	public void draw(){
		
	}
	
	// 1d array full of monster objects 
	public void createMonsters(Sprite sprite){
		for(int i = 0; i < monsterCollection.length; i++){
			monsterCollection[i] = new Monster("The Thing", sprite, 0, 0, 0, 0, 0, 0);
		}
	}
	
	// 2d array of items, where the each row is a different type of item and the each column represents a different object of type item where the greater
	// the column index the greater the item level
	public void createItems(){
		for(int j = 0; j < itemCollection.length; j++){
			for(int i = 0; i < itemCollection[j].length; i++){
				if(j == 0){
					//itemCollection[j][i] = new Weapon();
				}else if(j == 1){
					//itemCollection[j][i] = new Armor();
				}else if(j == 2){
					//itemCollection[j][i] = new Potion();
				}
			}
		}
	}
}
