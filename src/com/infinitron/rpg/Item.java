package com.infinitron.rpg;

public class Item extends GameObject{
	protected final int level;
	protected int state;
	protected int type;
	
	public Item(String _name, Sprite _sprite, int _xPos, int _yPos, int _level, int _state, int _type){
		super(_name, _sprite, _xPos, _yPos);
		level = _level;
		state = _state;
		type = _type;
	}
}
