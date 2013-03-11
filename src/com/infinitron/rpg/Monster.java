package com.infinitron.rpg;

public class Monster extends GameObject{
	private final int max_hp;
	private int hp;
	private final int attack;
	private final int defense;
	private Player player;
	
	public Monster(String name, Sprite sprite, int xPos, int yPos, int _max_hp, int _hp, int _attack, int _defense){
		super(name, sprite, xPos, yPos);
		max_hp = _max_hp;
		hp = _hp;
		attack = _attack;
		defense = _defense;
	}

	@Override

	public void update(){
		
	}
	
//	public void takeTurn(){
//		
//	}
	//Get monsters attack and players defense
	//Calculate damage, ensuring that it is always at least 1 no matter
	//how high the player's defense and pass this to the player class
	public void attack(){
		int result = attack - player.getDefense();
		if(result <= 0)result = 1;
		player.takeDamage(result);
	}
	
	public void takeDamage(int damage){
		hp -= damage;
	}
	
	public void heal(){
		
	}

	// getters and setters
	public int getDefense() {
		return defense;
	}

	public int getAttack() {
		return attack;
	}

	public int getHp() {
		return hp;
	}

	public void setHp(int hp) {
		this.hp = hp;
	}

	public int getMax_hp() {
		return max_hp;
	}
}
