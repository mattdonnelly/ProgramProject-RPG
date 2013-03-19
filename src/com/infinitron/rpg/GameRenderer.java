package com.infinitron.rpg;

import android.graphics.Canvas;
import android.graphics.Color;
import java.util.ArrayList;

public class GameRenderer {
	private ArrayList<GameObject> gameObjects = new ArrayList<GameObject>();
	
	public GameRenderer() {
		
	}

    public void setRenderables(ArrayList<GameObject> renderables) {
    	this.gameObjects = renderables;
    }

    public void addRenderable(GameObject r) {
    	synchronized(gameObjects){
		    gameObjects.add(r);
	    }
    }

    public void removeRenderable(int location){
	    synchronized(gameObjects){
		    gameObjects.remove(location);
	    }
    }

    public void removeRenderable(GameObject r){
	    synchronized(gameObjects){
		    gameObjects.remove(r);
	    }
    }

    public void drawFrame(Canvas canvas) {
	    synchronized(gameObjects){
		    for (GameObject object : gameObjects) {
		    	object.draw(canvas);
		    }
	    }

    }
}
