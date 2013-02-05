package com.infinitron.rpg;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.view.SurfaceHolder;

public class GameThread extends Thread {
	
	private SurfaceHolder surfaceHolder;
	private Game game;
	
	
	private GameRenderer renderer;
	
	private boolean running;
	private boolean paused;
	
	private static final int MAX_FPS = 60;
	private static final int CYCLE_TIME = 1000 / MAX_FPS;
	private static final int MAX_FRAME_SKIPS = 5;
			
	private int width = 0;
	private int height = 0;
	
	private Paint fpsPaint;
	
	public GameThread(Game game, GameRenderer renderer) {
		this.game = game;
		this.surfaceHolder = game.getHolder();
		
		this.renderer = renderer;
		
		paused = false;
		
		fpsPaint = new Paint(); 
		fpsPaint.setColor(Color.WHITE); 
		fpsPaint.setStyle(Style.FILL); 
		fpsPaint.setTextSize(20); 
	}

	
	@Override
	public void run() {
		Canvas canvas = null;

		long currentTime = 0;
		long timeDifference = 0;
		int sleepTime = 0;
		int framesSkipped = 0;
		
		while (running) {				
			while (paused) {
				synchronized (this) {
					try {
						wait();
					} catch (InterruptedException e) {
				    
					}
				}
		    }
			
			currentTime = System.currentTimeMillis();
			framesSkipped = 0;
			
			try {
				canvas = surfaceHolder.lockCanvas(null);
				synchronized (surfaceHolder) {
					game.update();
					renderer.drawFrame(canvas);
				}
			} finally {
				if (canvas != null) {
					surfaceHolder.unlockCanvasAndPost(canvas);
				}
			}
						
			timeDifference = System.currentTimeMillis() - currentTime;
			sleepTime = (int)(CYCLE_TIME - timeDifference);

			if (sleepTime > 0) {
				try {
					Thread.sleep(sleepTime);
				} catch (InterruptedException e) {
					
				}
			}

			while (sleepTime < 0 && framesSkipped < MAX_FRAME_SKIPS) {
				game.update();
				sleepTime += CYCLE_TIME;
				framesSkipped++;
			}
		}
	}
			
	public void surfaceCreated(SurfaceHolder surfaceholder) {
		synchronized (this) {
			running = true;
		}
	}

	public void surfaceDestroyed(SurfaceHolder surfaceholder) {
		synchronized (this) {
			running = false;
		}
	}
	
	public void surfaceChanged(SurfaceHolder surfaceholder, int format, int width, int height) {
		synchronized (this) {
			this.width = width;
			this.height = height;
		}
	}
}