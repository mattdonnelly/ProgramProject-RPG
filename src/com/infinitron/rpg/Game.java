package com.infinitron.rpg;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.view.View;

public class Game extends View {
	private Bitmap bitmap;
 
	public Game(Context context) {
		super(context);
		bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.ic_launcher);
	}

	@Override
	protected void onDraw(Canvas canvas) {
		canvas.drawColor(Color.BLACK);
		canvas.drawBitmap(bitmap, 100, 100, null);
	}
}
