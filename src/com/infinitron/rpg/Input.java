package com.infinitron.rpg;

import android.view.MotionEvent;

public class Input {
	
	public boolean pressed_down;
	public boolean up, down, left, right, idle = true;
	private int screen_width, screen_height;
	
	public Input (int screen_width, int screen_height) {
		this.up = false;
		this.down = false;
		this.right = false;
		this.left = false;
		
		this.screen_width = screen_width;
		this.screen_height = screen_height;
	}
	
	public void update() {
		
	}
	public String setMotion(String s) {
		return s;
	}
	public boolean onTouchEvent(MotionEvent event) {
		if (pressed_down == true) {
    		pressed_down = false;
    	}
		
	    int x = (int)event.getX();
	    int y = (int)event.getY();
	    switch (event.getAction()) {
	        case MotionEvent.ACTION_DOWN:
	        	pressed_down = true;
	        case MotionEvent.ACTION_MOVE:
	        	this.idle = false;
	        	if (x > 0 && x < screen_width / 3) {
	        		
	        		left = true;
	        	} else {
	        		left = false;
	        	} 
	        	if (x > screen_width / 3 * 2 && x < screen_width) {
	        		right = true;
	        	} else {
	        		right = false;
	        	}
	        	if (y > 0 && y < screen_height / 3) {
	        		up = true;
	        	} else {
	        		up = false;
	        	}
	        	if (y > screen_height / 3 * 2 && y < screen_height) {
	        		down = true;
	        	} else {
	        		down = false;
	        	}
	        	break;
	        case MotionEvent.ACTION_UP:
	        	pressed_down = false;
	        	up = down = left = right = false;
	        	this.idle = true;
	        	break;
	    }
	    return true;
	}

}
