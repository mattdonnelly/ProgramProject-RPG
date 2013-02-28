package com.infinitron.rpg;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.util.Log;
import android.view.SurfaceHolder;

public class GameThread extends Thread {
	
	private SurfaceHolder surfaceHolder;
	private Game game;
	
	private GameRenderer renderer;
	private Level level;
	
	private boolean running;
	private boolean paused;
	
	public final static int MAX_FPS = 50;	
	private final static int MAX_FRAME_SKIPS = 5;	
	public final static int CYCLE_TIME = 1000 / MAX_FPS;

	private final static int STAT_INTERVAL = 1000; //ms
	private long statusIntervalTimer = 0l;
	private int ticks = 0;
	
	private int width = 0;
	private int height = 0;
	
	private Paint fpsPaint;
	
	public GameThread(Game game, GameRenderer renderer, Level level) {
		this.game = game;
		this.surfaceHolder = game.getHolder();
		
		this.renderer = renderer;
		this.level = level;
			
		paused = false;
				
		fpsPaint = new Paint(); 
		fpsPaint.setColor(Color.WHITE); 
		fpsPaint.setStyle(Style.FILL); 
		fpsPaint.setTextSize(20); 
	}
	
	
	public void drawFPS(Canvas canvas) {
		canvas.scale(0.5f, 0.5f);

		canvas.drawText("" + game.fps + " FPS", width - 90, 40, fpsPaint);
	}
	
	@Override
	public void run() {
		Canvas canvas;
		
		int sleepTime;
		int framesSkipped;
		
		sleepTime = 0;
				
		while (running) {	
			canvas = null;
			
			// wait to start drawing again if paused
			while (paused) {
				synchronized (this) {
					try {
						wait();
					} catch (InterruptedException e) {
				    
					}
				}
		    }
			
			try {
				canvas = this.surfaceHolder.lockCanvas();
				synchronized (surfaceHolder) {
					
			    	canvas.scale(2.0f, 2.0f);
					
					final long beginTime = System.currentTimeMillis();
										
					framesSkipped = 0;
					
					this.game.update();
					canvas.drawColor(Color.MAGENTA);
					//this.level.drawLevel(canvas, 5, 5);
					this.renderer.drawFrame(canvas);
					this.drawFPS(canvas);
					
					final long endTime = System.currentTimeMillis() - beginTime;
					
					sleepTime = (int)(CYCLE_TIME - endTime);
					
					// Game is running faster than 60 fps so we send the thread to sleep to cap it at 60 fps
					if (sleepTime > 0) {
						try {
							Thread.sleep(sleepTime);	
						} catch (InterruptedException e) {
							
						}
					}
					
					// Game is running slower than 60 FPS so we need to skip some frames to catch up
					while (sleepTime < 0 && framesSkipped < MAX_FRAME_SKIPS) {
						this.game.update();
						sleepTime += CYCLE_TIME;
						framesSkipped++;
					}
					
					ticks++;
					statusIntervalTimer += CYCLE_TIME;
										
					// calculate fps
					if (statusIntervalTimer >= STAT_INTERVAL) {
						game.fps = ticks / (STAT_INTERVAL / 1000);
						
						statusIntervalTimer = 0;
						ticks = 0;
					}
				}
			} finally {
				if (canvas != null) {
					surfaceHolder.unlockCanvasAndPost(canvas);
				}
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