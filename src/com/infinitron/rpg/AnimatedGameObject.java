package com.infinitron.rpg;

public class AnimatedGameObject extends GameObject {

	protected Sprite[] sprites;
	private int currentSprite;
	private final int updateTime;
	private final int frameTime;
	private int runningTime;
	
	public AnimatedGameObject(String name, Sprite[] sprites, int x, int y, int updateTime, int frameTime) {
		super(name, sprites[0], x, y);
		this.sprites = sprites;
		this.currentSprite = 0;
		this.updateTime = updateTime;
		this.frameTime = frameTime;
		this.runningTime = 0;
	}
	
	public void update() {
		if (runningTime > updateTime) {
			runningTime = 0;
			currentSprite++;
			
			if (currentSprite >= sprites.length)
				currentSprite = 0;
			
			this.sprite = sprites[currentSprite];
		}
		
		runningTime += frameTime;
	}
}
