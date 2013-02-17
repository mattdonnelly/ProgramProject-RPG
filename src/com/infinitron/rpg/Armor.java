package com.infinitron.rpg;

public class Armor extends GameObject{
	private final int defense;
	
	public Armor(String _name, Sprite _sprite, int _xPos, int _yPos, int _defense){
		super(_name, _sprite, _xPos, _yPos);
		defense = _defense;
	}
}
