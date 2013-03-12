package com.infinitron.rpg;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.graphics.Rect;
import android.view.MotionEvent;

public class NPC extends GameObject {
	private final String text;
	private boolean state;
	private Rect src;
	private Paint paint = new Paint();
	private Sprite bubble;
	private float bubble_width;

	public NPC(String _name, Sprite _sprite, int _xPos, int _yPos,
			String _text, Sprite _bubble, boolean _state) {
		super(_name, _sprite, _xPos, _yPos);
		text = _text;
		state = _state;
		this.bubble = _bubble;
		
		paint.setColor(Color.BLACK);
		paint.setStyle(Style.FILL);
		paint.setTextSize(20);
		bubble_width = paint.measureText(text);
		src = new Rect(0,0,47,38);
	}

	// getters and setters
	public boolean isState() {
		return state;
	}

	public void setState(boolean state) {
		this.state = state;
	}

	public String getText() {
		return text;
	}

	public void update(Canvas canvas) {
		// change text - possibly have an on touch handler
		/**
		 * while(this.state && NPC_has_more_to_say) { ... } this.state = false;
		 */
		
		
		if (isState()) {
			// outrageous scaling used!
			bubble.draw(src, getXPos() - 40, 50, (int)bubble_width + 170, 150, paint, canvas);	// Able to scale dialog box *needs improving!*
			canvas.drawText(this.text, this.getXPos(), this.getYPos() - 90, paint);

		}
	}

	@Override
	public void doTouch(MotionEvent event) {
		if (event.getAction() == MotionEvent.ACTION_DOWN) {
			/**
			 * for(int i = 0; i < NPC.length(); i++) { find NPC touched }
			 */

		}
	}
}
