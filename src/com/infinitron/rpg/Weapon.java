package com.infinitron.rpg;

public class Weapon extends Item{
	protected final int damage;
	
	public Weapon(String _name, Sprite _sprite, int _xPos, int _yPos, int _level, int _state, int _type, int _damage){
		super(_name, _sprite, _xPos, _yPos, _level, _state, _type);
		damage = _damage;
	}
	public int getDamage(){
		return damage;
	}
}
