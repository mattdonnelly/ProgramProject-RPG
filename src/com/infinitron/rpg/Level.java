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
	public static final int EMPTY_DECORATION = 31;
	
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
		
		this.tileLookup = new Tile[terrainSheet.size()];
		for (int i = 0; i < terrainSheet.rows(); i++) {
			for (int j = 0; j < terrainSheet.columns(); j++) {
				this.tileLookup[(i * terrainSheet.columns()) + j] = new Tile(new Sprite(terrainSheet, i, j), false);
			}
		}
		
		this.decorationsLookup = new Tile[decorationsSheet.size()];
		for (int i = 0; i < decorationsSheet.rows(); i++) {
			for (int j = 0; j < decorationsSheet.columns(); j++) {
				if ((i * decorationsSheet.columns()) + j == EMPTY_DECORATION)
					this.decorationsLookup[(i * decorationsSheet.columns()) + j] = new Tile(new Sprite(decorationsSheet, i, j), false);
				else
					this.decorationsLookup[(i * decorationsSheet.columns()) + j] = new Tile(new Sprite(decorationsSheet, i, j), true);
			}
		}
		
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
	
	public void update(Player player) {

		int new_origion_x = xOffset;
		if (player.getXPos() < (xOffset * 16)) {
			new_origion_x = xOffset - ((Main.screen_width / 2) / 16);
		} else if (player.getXPos() > (xOffset * 16) + (Main.screen_width / 2)) {
			new_origion_x = xOffset + ((Main.screen_width / 2) / 16);
		}
		
		int new_origion_y = yOffset;
		if (player.getYPos() < (yOffset * 16)) {
			new_origion_y = yOffset - ((Main.screen_height / 2) / 16);
		} else if (player.getYPos() > (yOffset * 16) + (Main.screen_height / 2)) {
			new_origion_y = yOffset + ((Main.screen_height / 2) / 16);
		}
		
		// only update origion if it needs to be changed
		if (new_origion_x != xOffset || new_origion_y != yOffset) {
			setOrigin(new_origion_x, new_origion_y);
		}
	}
	
	public void setOrigin(int x, int y) {

		if (x < 0) {
			xOffset = 0;
		} else if (x + columns > terrain[0].length) {
			xOffset = terrain[0].length - columns;
		} else {
			xOffset = x;
		}
		
		if (y < 0) {
			yOffset = 0;
		} else if (y + rows > terrain.length) {
			yOffset = terrain.length - rows;
		} else {
			yOffset = y;
		}
		
		Canvas bufferCanvas = new Canvas(bufferedLevel);
		
		for (int i = 0; i < rows; i++) {
			for (int j = 0; j < columns; j++) {
				int tileNumber = terrain[i + yOffset][j + xOffset];
				tileLookup[tileNumber].draw(bufferCanvas, j * TILE_SIZE, i * TILE_SIZE);
				
				int decorationNumber = decorations[i + yOffset][j + xOffset];
				decorationsLookup[decorationNumber].draw(bufferCanvas, j * TILE_SIZE, i * TILE_SIZE);
			}
		}
	}
	
	public void draw(Canvas canvas) {
		canvas.drawBitmap(bufferedLevel, 0, 0, null);
	}
	
	public boolean tileAtIndexIsSolid(int x, int y) {
		int tileNumber = decorations[y][x];
		return tileNumber != EMPTY_DECORATION;
	}
	
	public int getScreenTop() {
		return yOffset;
	}
	
	public int getScreenLeft() {
		return xOffset;
	}
	
	public int getScreenRight() {
		return yOffset + rows;
	}
	
	public int getScreenBottom() {
		return yOffset + columns;
	}
}