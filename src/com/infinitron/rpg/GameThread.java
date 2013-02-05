package com.infinitron.rpg;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.view.SurfaceHolder;

public class GameThread extends Thread {
	
	private SurfaceHolder surfaceHolder;
	private Game game;
	
	private FPSTracker fpsTracker;
	
	private GameRenderer renderer;
	
	private boolean running;
	private boolean paused;
	
	private static final int MAX_FPS = 60;
	private static final int CYCLE_TIME = 1000 / MAX_FPS; // the amount of frames that can be fit in to a second at MAX_FPS
	private static final int MAX_FRAME_SKIPS = 5;
			
	private int width = 0;
	private int height = 0;
	
	private Paint fpsPaint;
	
	public GameThread(Game game, GameRenderer renderer) {
		this.game = game;
		this.surfaceHolder = game.getHolder();
		
		this.renderer = renderer;
		
		this.fpsTracker = new FPSTracker();
		
		paused = false;
		
		fpsPaint = new Paint(); 
		fpsPaint.setColor(Color.WHITE); 
		fpsPaint.setStyle(Style.FILL); 
		fpsPaint.setTextSize(20); 
	}
	
	
	public void drawFPS(Canvas canvas) {
		canvas.drawText("FPS: " + fpsTracker.getFPS(), width - 90, 40, fpsPaint);
	}
	
	@Override
	public void run() {
		Canvas canvas = null;

		int sleepTime = 0;
		int framesSkipped = 0;
		
		fpsTracker.start();
		
		while (running) {	
			
			// wait to start drawing again if paused
			while (paused) {
				synchronized (this) {
					try {
						wait();
					} catch (InterruptedException e) {
				    
					}
				}
		    }
			
			final long currentTime = System.currentTimeMillis();
			framesSkipped = 0;
			
			try {
				canvas = surfaceHolder.lockCanvas(null);
				synchronized (surfaceHolder) {
					// Update and Render
					game.update();
					renderer.drawFrame(canvas);
					drawFPS(canvas);
				}
			} finally {
				if (canvas != null) {
					surfaceHolder.unlockCanvasAndPost(canvas);
				}
			}
						
			final long timeDifference = System.currentTimeMillis() - currentTime;
			sleepTime = (int)(CYCLE_TIME - timeDifference);
			
			/* Game is running faster than 60 fps so we send the thread to sleep to cap it at 60 fps
			   and save some battery */
			if (sleepTime > 0) {
				try {
					Thread.sleep(sleepTime);
				} catch (InterruptedException e) {
					
				}
			}
			
			// Game is running slower than 60 FPS so we need to skip some frames to catch up
			while (sleepTime < 0 && framesSkipped < MAX_FRAME_SKIPS) {
				game.update();
				sleepTime += CYCLE_TIME;
				framesSkipped++;
			}
			
			fpsTracker.tick();
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