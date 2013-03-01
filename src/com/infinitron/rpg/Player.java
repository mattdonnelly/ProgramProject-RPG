package com.infinitron.rpg;

public class Player extends GameObject{
	private final int max_hp;
	private int hp;
	private Item[] inventory;
	private Potion potion;
	private Monster monster;
	private Weapon weapon;
	private Armor armor;
	
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
	//Get players weapon damage and monster's defense
	//Calculate damage, ensuring that it is always at least 1 no matter
	//how high the monster's defense and pass this to the monster class
	public void attack(){
		int damage = weapon.getDamage();
		int defense = monster.getDefense();
		int result = damage - defense;
		if(result <= 0)result = 1;
		monster.takeDamage(result);
	}
	
	public void takeDamage(int damage){
		hp -= damage;
		if(hp <= 0){
			// player should die
		}
	}
	//Call potion object and add it's value to current hp. If current hp then
	//exceeds max hp, it will be set to max hp
	public void heal(){
		hp += potion.getHpBoost();
		if(hp > max_hp)hp = max_hp;
	}
	public int getDefense(){
		return armor.getDefense();
	}

	// getters and setters
	public int getMax_hp(){
		return max_hp;
	}
	
	public int getHP(){
		return hp;
	}
	
	public void setHP(int _hp){
		hp = _hp;
	}

	public Item[] getInventory(){
		return inventory;
	}
	
	public void setInventory(Item[] _inventory){
		for(int i = 0; i < inventory.length; i++){
			inventory[i] = _inventory[i];
		}
	}
}
