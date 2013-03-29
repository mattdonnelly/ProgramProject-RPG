package com.infinitron.rpg;

public class AnimatedGameObject extends GameObject {

	protected Sprite[] sprites;
	private int currentSprite;
	protected final int updateTime;
	protected final int frameTime;
	protected int runningTime;
	
	public AnimatedGameObject(String name, Sprite[] sprites, int x, int y, int updateTime, int frameTime) {
		super(name, sprites[0], x, y);
		this.sprites = sprites;
		this.currentSprite = 0;
		this.updateTime = updateTime;
		this.frameTime = frameTime;
		this.runningTime = 0;
	}
	
	@Override
	public void update() {
		if (runningTime > updateTime) {
			runningTime = 0;
			currentSprite++;
			
			if (currentSprite >= sprites.length)
				currentSprite = 0;
			
			this.sprite = sprites[currentSprite];
			
			doneFrame();
		}
		
		runningTime += frameTime;
	}
	
	protected void doneFrame() {
		
	}
	
	protected void resetTimer() {
		runningTime = 0;
	}
}
