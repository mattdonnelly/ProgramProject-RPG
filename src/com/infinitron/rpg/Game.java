package com.infinitron.rpg;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.view.SurfaceView;
import android.view.SurfaceHolder;

public class Game extends SurfaceView implements SurfaceHolder.Callback {

	private SurfaceHolder surfaceHolder;
	private GameThread gameThread;
	private GameRenderer renderer;
	
	public Game(Context context) {
		super(context);
		init();
	}
	
	public void init() {
		surfaceHolder = getHolder();
		surfaceHolder.addCallback(this);
		setFocusable(true);
		
		renderer = new GameRenderer();
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