package com.infinitron.rpg;

public class FPSTracker {
	
	private long currentTime, lastTime;
	private int ticks;
	private int fps;
	
	public FPSTracker() {
		lastTime = 0;
		ticks = 0;
		fps = 0;
	}
	
	public void start() {
		lastTime = System.currentTimeMillis();
	}
	
	public void tick() {
		ticks++;
		
		currentTime = System.currentTimeMillis();
		if (currentTime - lastTime >= 1000) {
			fps = ticks;
			ticks = 0;
			lastTime = currentTime;
		}
	}
	
	public int getFPS() {
		return fps;
	}
}
