package com.infinitron.rpg;

public class NPC extends GameObject{
	private final String text;
	private boolean state;
	
	public NPC(String _name, Sprite _sprite, int _xPos, int _yPos, String _text, boolean _state){
		super(_name, _sprite, _xPos, _yPos);
		text = _text;
		state = _state;
	}
	
	// getters and setters
	public boolean isState() {
		return state;
	}
	
	public void setState(boolean state) {
		this.state = state;
	}
	
	public String getText() {
		return text;
	}
}
