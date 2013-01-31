package com.infinitron.rpg;

import android.graphics.Canvas;
import android.graphics.Color;
import java.util.ArrayList;

public class Renderer {
	private ArrayList<Renderable> renderables = new ArrayList<Renderable>();
	
	public Renderer() {
		
	}

    public void setRenderables(ArrayList<Renderable> renderables) {
    	this.renderables = renderables;
    }

    public void addRenderable(Renderable r) {
    	synchronized(renderables){
		    renderables.add(r);
	    }
    }

    public void removeRenderable(int location){
	    synchronized(renderables){
		    renderables.remove(location);
	    }
    }

    public void removeRenderable(Renderable r){
	    synchronized(renderables){
		    renderables.remove(r);
	    }
    }

    public void drawFrame(Canvas canvas) {

	    canvas.drawColor(Color.MAGENTA);
	    
	    synchronized(renderables){
		    for (Renderable r : renderables) {
			   // r.update();
			    r.draw(canvas);
		    }
	    }

    }
}
