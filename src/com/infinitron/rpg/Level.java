package com.infinitron.rpg;

import java.util.ArrayList;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.util.Log;
import android.view.SurfaceView;

public class Level {
	
	public static final int TILE_SIZE = 16;
	
	private Bitmap map;
	private int width;
	private int height;
	public Tile map_tiles[][];
	public Tile decor_tiles[][];
	
	public Level (String map, GridSpriteSheet mapsheet, String decor, GridSpriteSheet decorsheet, int width, int height) {
		this.width = width;
		this.height = height;
		
		map_tiles = new Tile[height][width];
		decor_tiles = new Tile[height][width];
		generateMap(map, mapsheet);
		generateDecor(decor, decorsheet);
	}
	
	private void generateMap(String map, GridSpriteSheet mapsheet) {
		String[] tile_strings = map.split("\\W+");
		String tile_string;
		int y = 0;
		int x = 0;
		for (int i = 0; i < tile_strings.length; i++) {
			tile_string = tile_strings[i];
			y = i / width;
			x = i % width;
			if (tile_string.equals("grass"))		{map_tiles[y][x] = new Tile(new Sprite(mapsheet, 0, 0), 16, true); System.out.println("grass");}
			else if (tile_string.equals("flower"))	{map_tiles[y][x] = new Tile(new Sprite(mapsheet, 0, 1), 16, true); System.out.println("flower");}
			else if (tile_string.equals("path"))	{map_tiles[y][x] = new Tile(new Sprite(mapsheet, 1, 0), 16, true); System.out.println("path");}
			else 									{map_tiles[y][x] = new Tile(new Sprite(mapsheet, 15, 15), 16, true); System.out.println("void");}
			
		}
	}
	
	private void generateDecor(String decor, GridSpriteSheet decorsheet) {
		String[] tile_strings = decor.split("\\W+");
		String tile_string;
		int y = 0;
		int x = 0;
		for (int i = 0; i < tile_strings.length; i++) {
			tile_string = tile_strings[i];
			y = i / width;
			x = i % width;
			if (tile_string.equals("tree"))			{decor_tiles[y][x] = new Tile(new Sprite(decorsheet, 0, 0), 16, false); System.out.println("tree");}
			else if (tile_string.equals("rock"))	{decor_tiles[y][x] = new Tile(new Sprite(decorsheet, 0, 1), 16, false); System.out.println("rock");}
			else 									{decor_tiles[y][x] = new Tile(new Sprite(decorsheet, 15, 15), 16, true); System.out.println("empty");}
		}
	}
	
	/* draw the level tiles from a top-left position to the side of the canvas */
	public void drawLevel(Canvas canvas, int y_offset, int x_offset) {
		
		int no_of_tiles_wide = canvas.getWidth() / TILE_SIZE;
		int no_of_tiles_high = canvas.getHeight() / TILE_SIZE;
		
		for (int i = 0; i < no_of_tiles_high && i + x_offset < height; i++) {
			for (int j = 0; j < no_of_tiles_wide && j + y_offset < width; j++) {
				map_tiles[y_offset + i][x_offset + j].draw(canvas, i, j);
				decor_tiles[y_offset + i][x_offset + j].draw(canvas, i, j);
			}
		}
	}

}
