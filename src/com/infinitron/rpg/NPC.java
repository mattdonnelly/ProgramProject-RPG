package com.infinitron.rpg;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.graphics.RectF;

public class NPC extends GameObject {
	private final String text;
	private RectF rec;
	private Paint paint = new Paint();
	private float bubble_width;
	private int BUBBLE_OFFSET = 30;
	private int BUBBLE_HEIGHT = 15;
	private float CORNER_RADIUS = 2;

	public NPC(String _name, Sprite _sprite, int _xPos, int _yPos, String _text) {
		super(_name, _sprite, _xPos, _yPos);
		text = _text;		
		paint.setStyle(Style.STROKE);
		paint.setTextSize(15);
		
		bubble_width = paint.measureText(text);
		rec = new RectF(this.getXPos()+BUBBLE_OFFSET, this.getYPos(), this.getXPos()+(int)bubble_width+BUBBLE_OFFSET, 
						this.getYPos()+BUBBLE_HEIGHT);
	}
	
	@Override
	public void draw(Canvas canvas, Level level) {
		super.draw(canvas, level);
		canvas.drawRoundRect(rec, CORNER_RADIUS, CORNER_RADIUS, paint);
		canvas.drawText(this.text, this.getXPos()+BUBBLE_OFFSET-(level.getScreenLeft() * Level.TILE_SIZE),
						this.getYPos()+ BUBBLE_HEIGHT - (level.getScreenTop() * Level.TILE_SIZE), paint);
	}
	
	public void update(Level level) {
		rec.set(this.getXPos()+BUBBLE_OFFSET-(level.getScreenLeft() * Level.TILE_SIZE),
				this.getYPos()-(level.getScreenTop() * Level.TILE_SIZE), 
				this.getXPos()+(int)bubble_width+BUBBLE_OFFSET-(level.getScreenLeft() * Level.TILE_SIZE), 
				this.getYPos()+BUBBLE_HEIGHT-(level.getScreenTop() * Level.TILE_SIZE));
	}

	@Override
	public void doTouch(String s) {
		
	}
	
	// getters and setters
	public String getText() {
		return text;
	}
}
