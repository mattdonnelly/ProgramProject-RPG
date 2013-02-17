package com.infinitron.rpg;

public class Monster extends GameObject{
	private int max_hp;
	private int hp;
	private int attack;
	private int defense;
	
	public Monster(String name, Sprite sprite, int xPos, int yPos, int _max_hp, int _hp, int _attack, int _defense){
		super(name, sprite, xPos, yPos);
		max_hp = _max_hp;
		hp = _hp;
		attack = _attack;
		defense = _defense;
	}
	
	public void update(){
		
	}
	
//	public void takeTurn(){
//		
//	}
	
	public void attack(){
		
	}
	
	public void takeDamage(){
		// Pass in player to get items
	}
	
	public void heal(){
		
	}
}
