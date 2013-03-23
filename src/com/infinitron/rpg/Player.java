package com.infinitron.rpg;

public class Player extends GameObject {

	public enum State {
		UP, DOWN, LEFT, RIGHT, IDLE
	}
	
	private final int max_hp;
	private int hp;
	private Item[] inventory;
	private Potion potion;
	private Monster monster;
	private Weapon weapon;
	private Armor armor;
	private Sprite[][] sprites;
	private int currentSprite;
	private int updateTime;
	private int frameTime;
	private int runningTime;
	private int rowSprite = 0;
	private Player.State state;
	private boolean idle = true;
	private int lastX, lastY;
	
	public Player(String _name, Sprite _sprite, int _xPos, int _yPos, int _max_hp, int _hp, Item[] _inventory){
		super(_name, _sprite, _xPos, _yPos);
		max_hp = _max_hp;
		hp = _hp;
		inventory = new Item[_inventory.length];
		for(int i = 0; i < inventory.length; i++){
			inventory[i] = _inventory[i];
		}			
	}
	
	public Player(String name, Sprite[][] sprites, int x, int y, int _max_hp, int updateTime, int frameTime) {
		super(name, sprites[0][0], x, y);
		this.sprites = sprites;
		this.currentSprite = 0; // sprite in row
		this.rowSprite = 0; // correct direction to face
		this.updateTime = updateTime;
		this.frameTime = frameTime;
		this.runningTime = 0;
		this.max_hp = _max_hp;
		this.lastX = x;
		this.lastY = y;
	}

	@Override

	public void update() {
		if (!idle) {
			if (runningTime > updateTime) {
				runningTime = 0;
				currentSprite++;

				if (currentSprite >= sprites.length)
					currentSprite = 0;

				this.sprite = sprites[rowSprite][currentSprite];
			}
		} else {
			this.sprite = sprites[rowSprite][0];
		}
		runningTime += frameTime;
	}
	
	public int setCorrectSprites(Player.State s) { // Working off which Row to get
		idle = false;
		if (s == Player.State.UP)
			rowSprite = 0;
		else if (s == Player.State.DOWN)
			rowSprite = 2;
		else if (s == Player.State.LEFT)
			rowSprite = 3;
		else if (s == Player.State.RIGHT)
			rowSprite = 1;
		else
			idle = true; // idle
		return rowSprite;
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
	// getters and setters
	public int getDefense(){
		return armor.getDefense();
	}
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
