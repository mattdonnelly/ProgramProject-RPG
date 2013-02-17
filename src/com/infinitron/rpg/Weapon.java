package com.infinitron.rpg;

public class Weapon extends GameObject{
	protected int damage;
	
	public Weapon(String _name, Sprite _sprite, int _xPos, int _yPos, int _damage){
		super(_name, _sprite, _xPos, _yPos);
		damage = _damage;
	}
	
}
