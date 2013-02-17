package com.infinitron.rpg;

public class Player extends GameObject{
	private int max_hp;
	private int hp;
	private Item[] inventory;
	
	public Player(String _name, Sprite _sprite, int _xPos, int _yPos, int _max_hp, int _hp, Item[] _inventory){
		super(_name, _sprite, _xPos, _yPos);
		max_hp = _max_hp;
		hp = _hp;
		inventory = new Item[_inventory.length];
		for(int i = 0; i < inventory.length; i++){
			inventory[i] = _inventory[i];
		}			
	}
	
	public void update(){
		
	}
	
	public void attack(){
		// select weapon player wields
		// combine weapon modifier and monster defense
		// call monster takeDamage(combined modifier and defense)
	}
	
	public void takeDamage(int damage){
		hp -= damage;
		if(hp <= 0){
			// player should die
		}
	}
	
	public void heal(){
		
	}
}
