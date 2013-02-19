package com.infinitron.rpg;

import java.util.ArrayList;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.util.Log;
import android.view.SurfaceView;

public class Level {
	
	private Bitmap map;
	private int width;
	private int height;
	public Tile tiles[][];
	
	// RGB values to find tiles types from map image
	public int grass_col = 0xff00ff00;
	public int path_col = 0xff848284;
	public int flower_col = 0xffffff00;
	public int dark_grass_col = 0xff009a00;
	public int tree_col = 0xffff9a00;
	
	public Level (Bitmap map, GridSpriteSheet tilesheet, int width, int height) {
		assert(width == map.getWidth() && height == map.getHeight()); // asserts correct quality res was used
		this.width = width;
		this.height = height;
		this.map = map;
		
		tiles = new Tile[height][width];
		generateLevel(map, tilesheet, width, height);
	}
	
	private void generateLevel(Bitmap bitmap, GridSpriteSheet tilesheet, int width, int height) {
		int pixel;
		for (int y = 0; y < height; y++) {
			for (int x = 0; x < width; x++) {
				
				pixel = bitmap.getPixel(x, y);
				Log.d("Level", y + ", " + x + ": " + Integer.toHexString(pixel));
				
				// find tile by pixel colour
				if (pixel == grass_col)				tiles[y][x] = new Tile(new Sprite(tilesheet, 0, 0), 16, false);
				else if (pixel == flower_col)		tiles[y][x] = new Tile(new Sprite(tilesheet, 0, 1), 16, false);
				else if (pixel == dark_grass_col)	tiles[y][x] = new Tile(new Sprite(tilesheet, 0, 2), 16, false);
				else if (pixel == path_col)			tiles[y][x] = new Tile(new Sprite(tilesheet, 1, 0), 16, false);
				else if (pixel == tree_col)			tiles[y][x] = new Tile(new Sprite(tilesheet, 2, 0), 16, true);
				
				else tiles[y][x] = new Tile(new Sprite(tilesheet, 15, 15), 16, false); // void tile
			}
		}
	}
	
	public void drawLevel(Canvas canvas) {

		for (int i = 0; i < height; i++) {
			for (int j = 0; j < width; j++) {
				tiles[i][j].draw(canvas, i, j);
			}
		}
		    
	}
	
}
