package com.infinitron.rpg;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.util.DisplayMetrics;
import android.view.WindowManager;

public class Level {

	public static final int TILE_SIZE = 16;
	
	private Context context;
		
	private Tile[] tileLookup;
	private Tile[] decorationsLookup;
	
	private byte[][] terrain;
	private byte[][] decorations;
	
	private Bitmap bufferedLevel;
	
	private int width;
	private int height;
	
	private int rows;
	private int columns;
	
	private int xOffset;
	private int yOffset;
	
	public Level(Context context) {
		this.context = context;
		
		DisplayMetrics metrics = new DisplayMetrics();
		((WindowManager)context.getSystemService(Context.WINDOW_SERVICE)).getDefaultDisplay().getMetrics(metrics);
		
		this.width = metrics.widthPixels / 2;
		this.height = metrics.heightPixels / 2;
		this.rows = height / TILE_SIZE;
		this.columns = width / TILE_SIZE;
		
		GridSpriteSheet terrainSheet = new GridSpriteSheet(BitmapFactory.decodeResource(context.getResources(), R.drawable.terrain), TILE_SIZE, TILE_SIZE);
		GridSpriteSheet decorationsSheet = new GridSpriteSheet(BitmapFactory.decodeResource(context.getResources(), R.drawable.decorations), TILE_SIZE, TILE_SIZE);
		
		this.tileLookup = new Tile[]{ new Tile(new Sprite(terrainSheet, 0, 0), false), 
									  new Tile(new Sprite(terrainSheet, 0, 1), false),
									  new Tile(new Sprite(terrainSheet, 0, 2), false),
									  new Tile(new Sprite(terrainSheet, 0, 3), false),
									  new Tile(new Sprite(terrainSheet, 0, 4), false),
									  new Tile(new Sprite(terrainSheet, 1, 0), false), 
									  new Tile(new Sprite(terrainSheet, 1, 1), false),
									  new Tile(new Sprite(terrainSheet, 1, 2), false),
									  new Tile(new Sprite(terrainSheet, 1, 3), false),
									  new Tile(new Sprite(terrainSheet, 1, 4), false),
									  new Tile(new Sprite(terrainSheet, 2, 0), false), 
									  new Tile(new Sprite(terrainSheet, 2, 1), false),
									  new Tile(new Sprite(terrainSheet, 2, 2), false),
									  new Tile(new Sprite(terrainSheet, 2, 3), false),
									  new Tile(new Sprite(terrainSheet, 2, 4), false),
									  new Tile(new Sprite(terrainSheet, 3, 0), false), 
									  new Tile(new Sprite(terrainSheet, 3, 1), false),
									  new Tile(new Sprite(terrainSheet, 3, 2), false),
									  new Tile(new Sprite(terrainSheet, 3, 3), false) };
		
		this.decorationsLookup = new Tile[]{ new Tile(new Sprite(decorationsSheet, 0, 0), true),
											 new Tile(new Sprite(decorationsSheet, 0, 1), true),
											 new Tile(new Sprite(decorationsSheet, 0, 2), true),
											 new Tile(new Sprite(decorationsSheet, 0, 3), true),
											 new Tile(new Sprite(decorationsSheet, 0, 4), true),
											 new Tile(new Sprite(decorationsSheet, 0, 5), true),
											 new Tile(new Sprite(decorationsSheet, 0, 6), true),
											 new Tile(new Sprite(decorationsSheet, 0, 7), true),
											 new Tile(new Sprite(decorationsSheet, 1, 0), true),
											 new Tile(new Sprite(decorationsSheet, 1, 1), true),
											 new Tile(new Sprite(decorationsSheet, 1, 2), true),
											 new Tile(new Sprite(decorationsSheet, 1, 3), true),
											 new Tile(new Sprite(decorationsSheet, 1, 4), true),
											 new Tile(new Sprite(decorationsSheet, 1, 5), true),
											 new Tile(new Sprite(decorationsSheet, 1, 6), true),
											 new Tile(new Sprite(decorationsSheet, 1, 7), true),
											 new Tile(new Sprite(decorationsSheet, 2, 0), true),
											 new Tile(new Sprite(decorationsSheet, 2, 1), true),
											 new Tile(new Sprite(decorationsSheet, 2, 2), true),
											 new Tile(new Sprite(decorationsSheet, 2, 3), true),
											 new Tile(new Sprite(decorationsSheet, 2, 4), true),
											 new Tile(new Sprite(decorationsSheet, 2, 5), true),
											 new Tile(new Sprite(decorationsSheet, 2, 6), true),
											 new Tile(new Sprite(decorationsSheet, 2, 7), true),
											 new Tile(new Sprite(decorationsSheet, 3, 0), true),
											 new Tile(new Sprite(decorationsSheet, 3, 1), true),
											 new Tile(new Sprite(decorationsSheet, 3, 2), true) };
		
		this.terrain = this.readFile("Terrain.txt");
		this.decorations = this.readFile("Decorations.txt");
		
		bufferedLevel = Bitmap.createBitmap(width, height, Config.RGB_565);
		this.setOrigin(xOffset, yOffset);
	}
	
	private byte[][] readFile(String filename) {	
		
		ArrayList <byte[]> lines = new ArrayList<byte[]>();
		
	    try {	    	
	    	InputStream inputStream = context.getAssets().open(filename);
		    InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
		    
			BufferedReader reader = new BufferedReader(inputStreamReader);
	    	
	        String line;
	        while ((line = reader.readLine()) != null) {
	             String[] row = line.split(",");
	             byte[] temp = new byte[row.length];
	             
	             for (int i = 0; i < row.length; i++)
	            	 temp[i] = Byte.parseByte(row[i]);
	             
	             lines.add(temp);
	        }
	        
	        inputStream.close();
	    }
	    catch (IOException ex) {
	        // handle exception
	    }
	    
	    byte[][] result = new byte[lines.size()][];
	    for (int i = 0; i < lines.size(); i++) {
	        byte[] row = lines.get(i);
	        result[i] = row;
	    }
	    
	    return result;
	}
	
	public void setOrigin(int x, int y) {
		if (x > columns)
			xOffset = columns - 16;
		else
			xOffset = x;
		
		if (y > rows)
			yOffset = rows - 16;
		else
			yOffset = y;
		
		Canvas bufferCanvas = new Canvas(bufferedLevel);
		
		for (int i = yOffset; i < rows + yOffset; i++) {
			for (int j = xOffset; j < columns + xOffset; j++) {		
				int tileNumber = terrain[i][j];
				tileLookup[tileNumber].draw(bufferCanvas, j * TILE_SIZE, i * TILE_SIZE);
				
				int decorationNumber = decorations[i][j];
				decorationsLookup[decorationNumber].draw(bufferCanvas, j * TILE_SIZE, i * TILE_SIZE);
			}
		}
	}
	
	public void draw(Canvas canvas) {
		canvas.drawBitmap(bufferedLevel, 0, 0, null);
	}
}