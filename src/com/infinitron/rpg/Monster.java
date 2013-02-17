package com.infinitron.rpg;

public class Monster extends GameObject{
	private final int max_hp;
	private int hp;
	private final int attack;
	private final int defense;
	
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

	// getters and setters
	public int getDefense() {
		return defense;
	}

	public int getAttack() {
		return attack;
	}

	public int getHp() {
		return hp;
	}

	public void setHp(int hp) {
		this.hp = hp;
	}

	public int getMax_hp() {
		return max_hp;
	}
}
