package com.infinitron.rpg;

public class Potion extends Item{
	private final int hpBoost;
	
	public Potion(String _name, Sprite _sprite, int _xPos, int _yPos, int _level, int _state, int _type, int _hpBoost){
		super(_name, _sprite, _xPos, _yPos, _level, _state,_type);
		this.hpBoost = _hpBoost;
	}

	public int getHpBoost(){
		return hpBoost;
	}
}
