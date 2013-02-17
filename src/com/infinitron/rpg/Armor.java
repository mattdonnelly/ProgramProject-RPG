package com.infinitron.rpg;

public class Armor extends Item{
	private final int defense;
	
	public Armor(String _name, Sprite _sprite, int _xPos, int _yPos, int _level, int _state, int _type, int _defense){
		super(_name, _sprite, _xPos, _yPos, _level, _state, _type);
		defense = _defense;
	}

	public int getDefense(){
		return defense;
	}
}
