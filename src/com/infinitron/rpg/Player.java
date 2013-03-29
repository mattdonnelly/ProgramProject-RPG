package com.infinitron.rpg;

import android.graphics.Canvas;

public class Player extends AnimatedGameObject {

	public enum State {
		UP, DOWN, LEFT, RIGHT, IDLE
	}
	
	private final int max_hp;
	private int hp;
	private Item[] inventory;
	private Potion potion;
	private Monster monster;
	private Armor armor;
	private Player.State state;
	private boolean idle;
	private int xTile, yTile;
	private int lastX, lastY;
	private Sprite[][] playerSprites;
	private int moveAdjust;
	private boolean attacking, canAttackAgain;
	
	private static final int DAMAGE_MODIFIER = 5;
	
	public Player(String name, Sprite[][] sprites, int xTile, int yTile, int _max_hp, int updateTime, int frameTime) {
		super(name, sprites[0], xTile * Level.TILE_SIZE, yTile * Level.TILE_SIZE, updateTime, frameTime);
		this.playerSprites = sprites;
		this.idle = true;
		this.max_hp = _max_hp;
		this.xTile = xTile;
		this.yTile = xTile;
		this.lastX = this.x;
		this.lastY = this.y;
		this.state = Player.State.DOWN;
		this.moveAdjust = Level.TILE_SIZE;
		this.attacking = false;
		this.canAttackAgain = true;
	}
	
	@Override
	public void update() {
		if (attacking) {
			if (this.state == Player.State.UP) {
				this.sprite = playerSprites[4][0];
			} else if (this.state == Player.State.RIGHT) {
				this.sprite = playerSprites[5][0];
			} else if (this.state == Player.State.DOWN) {
				this.sprite = playerSprites[6][0];
			} else if (this.state == Player.State.LEFT) {
				this.sprite = playerSprites[7][0];
			}
			
			super.update();
		} else if (idle) {
			if (this.state == Player.State.UP) {
				this.sprite = playerSprites[4][1];
			} else if (this.state == Player.State.RIGHT) {
				this.sprite = playerSprites[5][1];
			} else if (this.state == Player.State.DOWN) {
				this.sprite = playerSprites[6][1];
			} else if (this.state == Player.State.LEFT) {
				this.sprite = playerSprites[7][1];
			}
			
			if (runningTime > updateTime) {
				runningTime = 0;				
				doneFrame();
			}
			
			runningTime += frameTime;
		} else {
			if (this.state == Player.State.UP) {
				this.sprites = playerSprites[0];

				y--;
				
				if (y <= (lastY - moveAdjust)) {
					idle = true;
					yTile--;
				}
				
			} else if (this.state == Player.State.RIGHT) {
				this.sprites = playerSprites[1];
				
				x++;
				
				if (x >= (lastX + moveAdjust)) {
					idle = true;
					xTile++;
				}
				
			} else if (this.state == Player.State.DOWN) {
				this.sprites = playerSprites[2];

				y++;
				
				if (y >= (lastY + moveAdjust)) {
					idle = true;
					yTile++;
				}
				
			} else if (this.state == Player.State.LEFT) {
				this.sprites = playerSprites[3];

				x--;
				
				if (x <= (lastX - moveAdjust)) {
					idle = true;
					xTile--;
				}
			}
			
			super.update();
		}
	}
	
	@Override
	protected void doneFrame() {
		if (attacking) {
			monster.takeDamage(DAMAGE_MODIFIER);
			monster = null;
			attacking = false;
			canAttackAgain = false;
			
			if (this.state == Player.State.UP) {
				this.sprite = playerSprites[4][1];
			} else if (this.state == Player.State.RIGHT) {
				this.sprite = playerSprites[5][1];
			} else if (this.state == Player.State.DOWN) {
				this.sprite = playerSprites[6][1];
			} else if (this.state == Player.State.LEFT) {
				this.sprite = playerSprites[7][1];
			}
		} else if (!attacking) {
			canAttackAgain = true;
		}
	}
	
	@Override
	public void draw(Canvas canvas, Level level) {
		canvas.drawBitmap(sprite.getImage(), x - (level.getScreenLeft() * Level.TILE_SIZE), y - (level.getScreenTop() * Level.TILE_SIZE) - 2, null);
	}
	
	public void moveUp() {
		if (idle) {
			//this.sprite = playerSprites[4][1];
			state = Player.State.UP;

			if (!Game.level.tileAtIndexIsSolid(this.xTile, this.yTile-1)) {
				lastX = x;
				lastY = y;
				idle = false;
			}
		}
	}
	
	public void moveRight() {
		if (idle) {
			//this.sprite = playerSprites[5][1];
			state = Player.State.RIGHT;
			
			if (!Game.level.tileAtIndexIsSolid(this.xTile+1, this.yTile)) {
				lastX = x;
				lastY = y;
				idle = false;
			}
		}
	}

	public void moveDown() {
		if (idle) {
			//this.sprite = playerSprites[6][1];
			state = Player.State.DOWN;
			
			if (!Game.level.tileAtIndexIsSolid(this.xTile, this.yTile+1)) {
				lastX = x;
				lastY = y;
				idle = false;
			}
		}
	}
	
	public void moveLeft() {
		if (idle) {
			//this.sprite = playerSprites[7][1];
			state = Player.State.LEFT;
			
			if (!Game.level.tileAtIndexIsSolid(this.xTile-1, this.yTile)) {
				lastX = x;
				lastY = y;
				idle = false;
			}
		}
	}
	
	//Get players weapon damage and monster's defense
	//Calculate damage, ensuring that it is always at least 1 no matter
	//how high the monster's defense and pass this to the monster class
	public void attack(Monster monster, Player.State state) {
		if (!attacking && canAttackAgain) {
			this.state = state;
			this.monster = monster;
			attacking = true;
		}
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
	
	public int xTile() {
		return xTile;
	}
	
	public int yTile() {
		return yTile;
	}
	
	public void setInventory(Item[] _inventory){
		for(int i = 0; i < inventory.length; i++){
			inventory[i] = _inventory[i];
		}
	}
}
