package com.infinitron.rpg;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.util.Log;
import android.view.SurfaceView;
import android.view.SurfaceHolder;

public class GameView extends SurfaceView implements SurfaceHolder.Callback {

	private SurfaceHolder surfaceHolder;
	private GameThread gameThread;
	
	public GameView(Context context) {
		super(context);
		init();
	}
	
	public void init() {
		surfaceHolder = getHolder();
		surfaceHolder.addCallback(this);
		gameThread = new GameThread(surfaceHolder);
	}

	@Override
	public void surfaceCreated(SurfaceHolder surfaceHolder) {
		gameThread.surfaceCreated(surfaceHolder);
		gameThread.start();
	}

	@Override
	public void surfaceDestroyed(SurfaceHolder surfaceHolder) {
		gameThread.surfaceDestroyed(surfaceHolder);
		
		boolean retry = true;
		while (retry) {
			try {
				gameThread.join();
			} catch (InterruptedException e) {
				retry = true;
			}
		}
	}
	
	@Override
	public void surfaceChanged(SurfaceHolder surfaceHolder, int format, int width, int height) {
		gameThread.surfaceChanged(surfaceHolder, format, width, height);
	}
	
	public class GameThread extends Thread {
		
		private SurfaceHolder surfaceHolder;
		
		private boolean running;
		private boolean paused;
		
		private static final int MAX_FPS = 50;
		private static final int CYCLE_TIME = 1000 / MAX_FPS;
		private static final int MAX_FRAME_SKIPS = 5;
		
		private int fps;
		
		private int width;
		private int height;
		
		public GameThread(SurfaceHolder surfaceHolder) {
			this.surfaceHolder = surfaceHolder;
			paused = false;
		}
		
		// render() and update() need to be separated in to their own threads
		
		public void update() {
			
		}
		
		public void render() {
			Canvas canvas = null;
			
			try {
				canvas = surfaceHolder.lockCanvas();
				
				synchronized (surfaceHolder) {
					canvas.drawColor(Color.MAGENTA);
					
					Paint paint = new Paint(); 
					paint.setColor(Color.WHITE); 
					paint.setStyle(Style.FILL); 
					paint.setTextSize(20); 
					canvas.drawText("FPS: " + fps, width - 90, 40, paint);
				}
			} finally {
                if (canvas != null){
                    surfaceHolder.unlockCanvasAndPost(canvas);
                }
            }
		}
		
		@Override
		public void run() {
			long beginTime = 0, currentTime = 0, timeDifference = 0, sleepTime = 0;
			long lastSecond = System.currentTimeMillis();
			int ticks = 0, framesSkipped = 0;
			
			while (running) {
				synchronized (this) {
				    while (paused) {
				        try {
				           wait();
				        } catch (InterruptedException e) {
				        
				        }
				    }
				}
				
				framesSkipped = 0;
				beginTime = System.currentTimeMillis();
				
				update();
				render();
				
				ticks++;
				
				currentTime = System.currentTimeMillis();
				if (currentTime - lastSecond >= 1000) {
					fps = ticks;
					ticks = 0;
					lastSecond = currentTime;
				}
			
				timeDifference = beginTime - currentTime;
				sleepTime = CYCLE_TIME - timeDifference;
				
				if (sleepTime > 0) {
					try {
						Thread.sleep(sleepTime);
					} catch (InterruptedException e) {
					
					}
				}
				
				while (sleepTime < 0 && framesSkipped < MAX_FRAME_SKIPS) {
					update(); 
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
}