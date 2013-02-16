package com.infinitron.rpg;

import android.content.Context;
import android.graphics.BitmapFactory;
import android.view.SurfaceView;
import android.view.SurfaceHolder;

public class Game extends SurfaceView implements SurfaceHolder.Callback {

	private SurfaceHolder surfaceHolder;
	private GameThread gameThread;
	private GameRenderer renderer;
	
	// TESTING SPRITE RENDERING
	public SpriteSheet elaine = new SpriteSheet(BitmapFactory.decodeResource(getResources(), R.drawable.walk_elaine), 30, 47);
	public Sprite elaineSprite = new Sprite(elaine, 2);
	public GameObject elaineObject = new GameObject(elaineSprite, 20, 20);
	
	public int fps = 0;
	
	public Game(Context context) {
		super(context);
		init();
	}
	
	public void init() {
		surfaceHolder = getHolder();
		surfaceHolder.addCallback(this);
		setFocusable(true);
		
		renderer = new GameRenderer();
		renderer.addRenderable(elaineObject);
		
		gameThread = new GameThread(this, renderer);
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
}