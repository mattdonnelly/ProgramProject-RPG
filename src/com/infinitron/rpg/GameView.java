package com.infinitron.rpg;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.view.SurfaceView;
import android.view.SurfaceHolder;

public class GameView extends SurfaceView implements SurfaceHolder.Callback {

	private SurfaceHolder surfaceHolder;
	private GameThread gameThread;
	private Renderer renderer;
	private FPSTracker fpsTracker;
	
	public GameView(Context context) {
		super(context);
		init();
	}
	
	public void init() {
		surfaceHolder = getHolder();
		surfaceHolder.addCallback(this);
		setFocusable(true);
		
		renderer = new Renderer();
		gameThread = new GameThread(this, renderer);
		
		fpsTracker = new FPSTracker();
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
				retry = false;
			} catch (InterruptedException e) {
				
			}
		}
	}
	
	@Override
	public void surfaceChanged(SurfaceHolder surfaceHolder, int format, int width, int height) {
		gameThread.surfaceChanged(surfaceHolder, format, width, height);
	}
	
	public void update() {
		
	}
	
	public void render(Canvas canvas) {
		canvas.drawColor(Color.MAGENTA);
	}
	
	public class GameThread extends Thread {
		
		private SurfaceHolder surfaceHolder;
		private GameView gameView;
		
		private Renderer renderer;
		
		private boolean running;
		private boolean paused;
		
		private static final int MAX_FPS = 60;
		private static final int CYCLE_TIME = 1000 / MAX_FPS;
		private static final int MAX_FRAME_SKIPS = 5;
				
		private int width = 0;
		private int height = 0;
		
		private Paint fpsPaint;
		
		public GameThread(GameView gameView, Renderer renderer) {
			this.gameView = gameView;
			this.surfaceHolder = gameView.getHolder();
			
			this.renderer = renderer;
			
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

			long currentTime = 0;
			long timeDifference = 0;
			int sleepTime = 0;
			int framesSkipped = 0;
			
			fpsTracker.start();

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
						gameView.update();
						gameView.render(canvas);
						drawFPS(canvas);
					}
				} finally {
					if (canvas != null) {
						surfaceHolder.unlockCanvasAndPost(canvas);
					}
				}
				
				fpsTracker.tick();
				
				timeDifference = System.currentTimeMillis() - currentTime;
				sleepTime = (int)(CYCLE_TIME - timeDifference);

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