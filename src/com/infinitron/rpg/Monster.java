package com.infinitron.rpg;
/*
 * A class representing monsters in the game. Monsters have
 * a certain amount of health along with values for how strong
 * they are and how difficult they are to kill
 */
public class Monster extends AnimatedGameObject{
	
	private final int max_hp;
	private int hp;
	private final int attack;
	private final int defense;
	private Player player; 
	private int velocity = 1;
	
	public Monster(String name, Sprite[] sprite, int xPos, int yPos, int _max_hp, int _hp, int _attack, int _defense, int updateTime, int frameTime){
		super(name, sprite, xPos, yPos, updateTime, frameTime);
		max_hp = _max_hp;
		hp = _hp;
		attack = _attack;
		defense = _defense;
	}

	@Override
	public void update(){
		super.update();
		
		this.x += velocity;
		if(this.x < 0 || this.x > 225){
			velocity = -velocity;
		}
	}
	
	/*
	 * Get monsters attack and players defense
	 * Calculate damage, ensuring that it is always at least 1 no matter
	 * how high the player's defense and pass this to the player class
	 */
	public void attack(){
		int result = attack - player.getDefense();
		if(result <= 0){
			result = 1;
		}
		player.takeDamage(result);
	}
	
	/*
	 * Represents a monster taking damage, where
	 * hp is reduced. If hp goes below 0 then it is
	 * reset to zero
	 */
	public void takeDamage(int damage){
		hp -= damage;
		if(hp < 0){
			hp = 0;
		}
	}
	
	//Call potion object and add it's value to current hp. If current hp then
	//exceeds max hp, it will be set to max hp
	public void heal(Potion potion){
		hp += potion.getHpBoost();
		if(hp > max_hp){
			hp = max_hp;
		}
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
