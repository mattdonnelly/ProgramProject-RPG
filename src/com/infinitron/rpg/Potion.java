package com.infinitron.rpg;

public class Potion extends GameObject{
	private final int hpBoost;
	
	public Potion(String _name, Sprite _sprite, int _xPos, int _yPos, int _hpBoost){
		super(_name, _sprite, _xPos, _yPos);
		this.hpBoost = _hpBoost;
	}
}
