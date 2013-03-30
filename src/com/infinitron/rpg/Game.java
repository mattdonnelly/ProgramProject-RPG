package com.infinitron.rpg;

import android.content.Context;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.view.MotionEvent;
import android.view.SurfaceView;
import android.view.SurfaceHolder;

public class Game extends SurfaceView implements SurfaceHolder.Callback {

	private SurfaceHolder surfaceHolder;
	private GameThread gameThread;
	private Item[][] itemCollection = new Item[3][10];//Would then call createItems to fill array
	
	public static Player player;
	public static Level level;
	public static Monster monsters[] = new Monster[10];
	public static NPC npc1;
	public static NPC npc2;
	public static NPC npc3;
	
	public Input input = new Input(Main.screen_width, Main.screen_height);

	protected int fps = 0;

	public Game(Context context) {
		super(context);
		init(context);
	}

	public void init(Context context) {
		surfaceHolder = getHolder();
		surfaceHolder.addCallback(this);
		setFocusable(true);
		
		level = new Level(context);
		
		GridSpriteSheet p1 = new GridSpriteSheet(BitmapFactory.decodeResource(context.getResources(), R.drawable.player16x18),16,18);
		
		Sprite[][] playerSprites = new Sprite[p1.rows()][p1.columns()];
		for (int i = 0; i < p1.rows(); i++) {
			for (int j = 0; j < p1.columns(); j++) {
				playerSprites[i][j] = new Sprite(p1, i, j);
			}
		}
		
		player = new Player("Elaine", playerSprites, 4, 4, 500, 200, GameThread.CYCLE_TIME);
		
		Sprite treeSprite = new Sprite(BitmapFactory.decodeResource(getResources(), R.drawable.tree));
		Sprite rockSprite = new Sprite(BitmapFactory.decodeResource(getResources(), R.drawable.rock));
				
		monsters[0] = new Monster("Tree", treeSprite, 13, 40, 20, 20, 0, 0);
		monsters[1] = new Monster("Tree", treeSprite, 38, 37, 20, 20, 0, 0);
		monsters[2] = new Monster("Tree", treeSprite, 18, 28, 20, 20, 0, 0);
		monsters[3] = new Monster("Tree", treeSprite, 7,  38, 20, 20, 0, 0);
		monsters[4] = new Monster("Tree", treeSprite, 8,  29, 20, 20, 0, 0);
		monsters[5] = new Monster("Rock", rockSprite, 38, 6,  40, 40, 0, 0);
		monsters[6] = new Monster("Rock", rockSprite, 20, 20, 40, 40, 0, 0);
		monsters[7] = new Monster("Rock", rockSprite, 18, 12, 40, 40, 0, 0);
		monsters[8] = new Monster("Rock", rockSprite, 5,  8,  40, 40, 0, 0);
		monsters[9] = new Monster("Rock", rockSprite, 8,  14, 40, 40, 0, 0);
		
		Sprite npcSprite = new Sprite(BitmapFactory.decodeResource(getResources(), R.drawable.npc_sprite));
		npc1 = new NPC("NPC1", npcSprite, 10, 30, "Go hit a rock!");
		npc2 = new NPC("NPC2", npcSprite, 70, 280, "This way quickly! -->");
		npc3 = new NPC("NPC3", npcSprite, 300, 150, "Many lands to explore!");
		
		gameThread = new GameThread(this);
	}

	public void surfaceCreated(SurfaceHolder surfaceHolder) {
		gameThread.surfaceCreated(surfaceHolder);
		gameThread.start();
	}

	public void surfaceDestroyed(SurfaceHolder surfaceHolder) {
		gameThread.surfaceDestroyed(surfaceHolder);

		boolean retry = true;
		while (retry) {
			try {
				gameThread.join();
				retry = false;
			} catch (InterruptedException e) {

			}
		}
	}
	
	public void surfaceChanged(SurfaceHolder surfaceHolder, int format, int width, int height) {
		gameThread.surfaceChanged(surfaceHolder, format, width, height);
	}

	@Override
	public void draw(Canvas canvas) {
		level.draw(canvas);
		
		for(int i = 0; i < monsters.length; i++){
			if(monsters[i] != null){
				monsters[i].draw(canvas, level);	//Draws all alive monsters in the level
			}
		}
		
		player.draw(canvas, level);
		
		npc1.draw(canvas, level);
		npc2.draw(canvas, level);
		npc3.draw(canvas, level);
	}

	public void update() {
		player.update();
		npc1.update(level);
		npc2.update(level);
		npc3.update(level);
		level.update(player);
		
		if (input.up) {
			Monster m = getMonsterAtTile(player.xTile(), player.yTile()-1);
			if (m  != null)
				player.attack(m, Player.State.UP);
			else
				player.moveUp();
		} else if (input.down) {
			Monster m = getMonsterAtTile(player.xTile(), player.yTile()+1);
			if (m  != null)
				player.attack(m, Player.State.DOWN);
			else
				player.moveDown();
		} else if (input.left) {
			Monster m = getMonsterAtTile(player.xTile()-1, player.yTile());
			if (m  != null)
				player.attack(m, Player.State.LEFT);
			else
				player.moveLeft();
		} else if (input.right) {
			Monster m = getMonsterAtTile(player.xTile()+1, player.yTile());
			if (m  != null)
				player.attack(m, Player.State.RIGHT);
			else
				player.moveRight();
		}
		
		for (int i = 0; i < monsters.length; i++){
			if (monsters[i] != null){
				monsters[i].update();
				// Delete dead monsters by setting them to NULL
				if (((Monster)monsters[i]).getHp() <= 0){
					monsters[i] = null;
				}
			}
		}
	}
	
	public Monster getMonsterAtTile(int x, int y) {
		
		for (int i = 0; i < monsters.length; i++) {
			Monster temp = monsters[i];
			if (temp != null) {
				if (temp.xTile() == x && temp.yTile() == y) {
					return temp;
				}
			}
		}
		
		return null;
	}
	
	// 2d array of items, where the each row is a different type of item and the each column represents a different object of type item where the greater
	// the column index the greater the item level
	//Attributes for Items: 	String _name, Sprite _sprite, int _xPos, int _yPos, int _level, int _state, int _type
	//Attributes for Weapon: 	String _name, Sprite _sprite, int _xPos, int _yPos, int _level, int _state, int _type, int _damage
	//Attributes for Armor:		String _name, Sprite _sprite, int _xPos, int _yPos, int _level, int _state, int _type, int _defense
	//Attributes forPotion:		String _name, Sprite _sprite, int _xPos, int _yPos, int _level, int _state, int _type, int _hpBoost

	//Assuming state = 0 for all items and type = 1 for weapons, 2 for armor, 3 for potions
	public void createItems(Sprite itemSprites[][]){
											//Name			//Sprite				//XPos								//YPos				//Lvl    State    type dmg
		itemCollection[0][0] = new Weapon("Rusty Sword",	itemSprites[0][0],	itemCollection[0][0].getXPos(),	itemCollection[0][0].getYPos(),	1,		0,		1,	1);
		itemCollection[0][1] = new Weapon("Iron Sword",		itemSprites[0][1],	itemCollection[0][1].getXPos(),	itemCollection[0][1].getYPos(),	3,		0,		1,	5);
		itemCollection[0][2] = new Weapon("Steel Sword",	itemSprites[0][2],	itemCollection[0][2].getXPos(),	itemCollection[0][2].getYPos(),	5,		0,		1,	10);
																																								  //def
		itemCollection[1][0] = new Armor("Rusty Armor",		itemSprites[1][0],	itemCollection[1][0].getXPos(),	itemCollection[1][0].getYPos(),	1,		0,		2,	1);
		itemCollection[1][1] = new Armor("Leather Armor",	itemSprites[1][1],	itemCollection[1][1].getXPos(),	itemCollection[1][1].getYPos(),	2,		0,		2,	3);
		itemCollection[1][2] = new Armor("Iron Armor",		itemSprites[1][2],	itemCollection[1][2].getXPos(),	itemCollection[1][2].getYPos(),	3,		0,		2,	5);
																																								  //hpGiven
		itemCollection[2][0] = new Potion("Minor Potion",	itemSprites[2][0],	itemCollection[2][0].getXPos(),	itemCollection[1][0].getYPos(),	1,		0,		3,	10);
		itemCollection[2][1] = new Potion("Small Potion",	itemSprites[2][1],	itemCollection[2][1].getXPos(),	itemCollection[1][1].getYPos(),	3,		0,		3,	30);
		itemCollection[2][2] = new Potion("Medium Potion",	itemSprites[2][2],	itemCollection[2][2].getXPos(),	itemCollection[1][2].getYPos(),	5,		0,		3,	50);
	}
	
	//A method which returns an array of items which can consist of weapons,armor,potions or all
	//As parameters it takes four integers. 
	//The first integer specifies the lowest row to select in the item array, while the second integer specifies largest row to select
	//The third integer specifies the lowest column to select in the item array, while the fourth integer specifies largest column to select
	//Example: 4x4 array with (0,0) being 0, (0,1) being 1...... and (3,3) being 15 rowMax = 3,rowMin = 1,colMax = 3,colMin = 1 
	// 0	1	2	3
	// 4	5	6	7     -->	5	6	7
	// 8	9	10	11			9	10	11
	// 12	13	14	15			13	14	15
	public Item[][] getItemRange(int rowMin, int rowMax, int colMin, int colMax){
		int i, j, k = 0, h = 0;
		int rowIndex = rowMax - rowMin + 1;
		int colIndex = colMax - colMin + 1;
		Item[][] result = new Item[rowIndex][colIndex];
		for(i = rowMin; i <= rowMax; i++){
			for(j = colMin; j <= colMax; j++){
				result[k][h++] = itemCollection[i][j];
			}
			k++;
			h = 0;
		}
		return result;
	}

	// NOTE: when used with 2d array, copies over number of rows given by length
	// A method which takes two position markers and a length parameter. srcPos
	// specifies the start point of the array row itemCollection from which to start copying. destPos
	// specifies the start position of the return array. length specifies the number of elements to copy over
	public Item[][] getSimpleItemRange(int srcPos, int destPos, int length) {
		Item[][] result = new Item[length + 1][3];// SECOND ARRAY BOUND MUST BE SAME AS ITEMCOLLECTION
		System.arraycopy(itemCollection, srcPos, result, destPos, length);
		return result;
	}
	
    @Override
    public boolean onTouchEvent(MotionEvent event){
    	/*
    	 * Get area touched
    	 */
    	
    	input.onTouchEvent(event);
    	
    	if (input.pressed_down) {
//    		elaineNPC.setState(!elaineNPC.isState());
    	}
    	
    	return true;
    }
    
}