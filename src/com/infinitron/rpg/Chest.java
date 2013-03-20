package com.infinitron.rpg;

public class Chest extends GameObject{
	private final int level;
	private Item[] loot;
	private boolean opened;
	
	public Chest(String _name, Sprite _sprite, int _xPos, int _yPos, int _level, boolean _opened, Item[] _loot){
		super(_name, _sprite, _xPos, _yPos);
		level = _level;
		opened = _opened;
		loot = new Item[_loot.length];
		for(int i = 0; i < loot.length; i++){//Transfer passed in loot to object
			loot[i] = _loot[i];
		}	
	}
	
	// getters and setters
	public boolean isOpened() {
		return opened;
	}
	
	public void setOpened(boolean opened) {
		this.opened = opened;
	}
	
	public Item[] getLoot() {
		return loot;
	}
	
	public void setLoot(Item[] _loot) {
		for(int i = 0; i < loot.length; i++){
			loot[i] = _loot[i];
		}
	}
	
	public int getLevel() {
		return level;
	}
}
