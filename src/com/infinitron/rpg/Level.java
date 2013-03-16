package com.infinitron.rpg;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.util.Log;

public class Level {

	public static final int TILE_SIZE = 16;
	public int pixels_wide;
	public int pixels_high;

	private Bitmap map;
	private int map_pixels_wide;
	private int map_pixels_high;
	private int tiles_wide;
	private int tiles_high;
	public Tile map_tiles[][];

	public Level (Bitmap map_bitmap, String map_text, int width, int height) {
		this.map = map_bitmap;
		this.tiles_wide = width;
		this.tiles_high = height;
		this.map_pixels_wide = map.getWidth();
		this.map_pixels_high = map.getHeight();
		
		this.pixels_wide = tiles_wide * Tile.SIZE;
		this.pixels_high = tiles_high * Tile.SIZE;

		this.map_tiles = new Tile[height][width];

		generateMap(map_text);
	}
	
	/* returns true if the tile at a given x,y co-ordinate is unpassable */
	public boolean isSolid(int x, int y) {
		return map_tiles[y][x].isSolid();
	}

	/* build map data from decoded text file */
	private void generateMap(String map) {
		String[] tile_strings = map.split("\\W+");
		String tile_string;
		int y = 0;
		int x = 0;
		for (int i = 0; i < tile_strings.length; i++) {
			tile_string = tile_strings[i];
			y = i / tiles_wide;
			x = i % tiles_wide;
			if (tile_string.equals("grass"))		map_tiles[y][x] = new Tile("grass", false);
			else if (tile_string.equals("dirt"))	map_tiles[y][x] = new Tile("dirt", 	false);
			else if (tile_string.equals("water"))	map_tiles[y][x] = new Tile("water", true);
			else if (tile_string.equals("tree"))	map_tiles[y][x] = new Tile("tree",	true);
			else if (tile_string.equals("cliff"))	map_tiles[y][x] = new Tile("cliff",	true);
			else if (tile_string.equals("rock"))	map_tiles[y][x] = new Tile("rock",	true);
			else 									map_tiles[y][x] = new Tile("void",	false);

		}
	}

	/* Part of a large bitmap is draw onto the screen */
	public void draw(Canvas canvas, int x_screen_pos, int y_screen_pos) {
		
		int screen_width = canvas.getWidth() / 2; // no of map pixels to fit on x-axis of screen
		int screen_height = canvas.getHeight() / 2; // no of map pixels to fit on y-axis of screen
		
		if (x_screen_pos < 0) { 
			x_screen_pos = 0;
		} else if (x_screen_pos + screen_width > map_pixels_wide) {
			x_screen_pos = map_pixels_wide - screen_width;
		}
		
		if (y_screen_pos < 0) { 
			y_screen_pos = 0;
		} else if (y_screen_pos + screen_height > map_pixels_high) {
			y_screen_pos = map_pixels_high - screen_height;
		}

		
		Rect sourceRect = new Rect(x_screen_pos, y_screen_pos, x_screen_pos + screen_width, y_screen_pos + screen_height);
		Rect destRect = new Rect(0, 0, canvas.getWidth() / 2, canvas.getHeight() / 2);

		canvas.drawBitmap(map, sourceRect, destRect, null);

	}

}