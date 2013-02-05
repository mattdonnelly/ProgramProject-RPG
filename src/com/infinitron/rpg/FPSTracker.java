package com.infinitron.rpg;

public class FPSTracker {

	private long lastTime;
	private int ticks;
	private int fps;

	public FPSTracker() {
		lastTime = 0;
		ticks = 0;
		fps = 0;
	}

	public synchronized void start() {
		lastTime = System.currentTimeMillis();
	}

	public synchronized void tick() {
		ticks++;

		final long currentTime = System.currentTimeMillis();
		
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
