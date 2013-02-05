package com.infinitron.rpg;

import android.graphics.Canvas;
import android.graphics.Color;
import java.util.ArrayList;

public class Renderer {
	private ArrayList<GameObject> gameObjects = new ArrayList<GameObject>();
	
	public Renderer() {
		
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

	    canvas.drawColor(Color.MAGENTA);
	    
	    synchronized(gameObjects){
		    for (GameObject r : gameObjects) {
			   // r.update();
			    r.draw(canvas);
		    }
	    }

    }
}
