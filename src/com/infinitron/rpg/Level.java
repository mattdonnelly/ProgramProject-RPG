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
			if (tile_string.equals("grass1"))		{map_tiles[y][x] = new Tile(new Sprite(mapsheet, 0, 0), 16, true); System.out.println("grass");}
			else if (tile_string.equals("rock"))	{map_tiles[y][x] = new Tile(new Sprite(mapsheet, 0, 2), 16, true); System.out.println("rock");}
			else if (tile_string.equals("water"))	{map_tiles[y][x] = new Tile(new Sprite(mapsheet, 0, 3), 16, true); System.out.println("path");}
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
			if (tile_string.equals("rocktl"))		{decor_tiles[y][x] = new Tile(new Sprite(decorsheet, 0, 0), 16, false); System.out.println("rocktl");}
			else if (tile_string.equals("rocktc"))	{decor_tiles[y][x] = new Tile(new Sprite(decorsheet, 0, 1), 16, false); System.out.println("rocktc");}
			else if (tile_string.equals("rocktr"))	{decor_tiles[y][x] = new Tile(new Sprite(decorsheet, 0, 2), 16, false); System.out.println("rocktr");}
			else if (tile_string.equals("rockml"))	{decor_tiles[y][x] = new Tile(new Sprite(decorsheet, 1, 0), 16, false); System.out.println("rockml");}
			else if (tile_string.equals("rockmr"))	{decor_tiles[y][x] = new Tile(new Sprite(decorsheet, 1, 2), 16, false); System.out.println("rockmr");}
			else if (tile_string.equals("rockbl"))	{decor_tiles[y][x] = new Tile(new Sprite(decorsheet, 2, 0), 16, false); System.out.println("rockbl");}
			else if (tile_string.equals("rockbc"))	{decor_tiles[y][x] = new Tile(new Sprite(decorsheet, 2, 1), 16, false); System.out.println("rockbc");}
			else if (tile_string.equals("rockbr"))	{decor_tiles[y][x] = new Tile(new Sprite(decorsheet, 2, 2), 16, false); System.out.println("rockbr");}
			
			else if (tile_string.equals("watertl"))	{decor_tiles[y][x] = new Tile(new Sprite(decorsheet, 0, 3), 16, false); System.out.println("watertl");}
			else if (tile_string.equals("watertc"))	{decor_tiles[y][x] = new Tile(new Sprite(decorsheet, 0, 4), 16, false); System.out.println("watertc");}
			else if (tile_string.equals("watertr"))	{decor_tiles[y][x] = new Tile(new Sprite(decorsheet, 0, 5), 16, false); System.out.println("watertr");}
			else if (tile_string.equals("waterml"))	{decor_tiles[y][x] = new Tile(new Sprite(decorsheet, 1, 3), 16, false); System.out.println("waterml");}
			else if (tile_string.equals("watermr"))	{decor_tiles[y][x] = new Tile(new Sprite(decorsheet, 1, 5), 16, false); System.out.println("watermr");}
			else if (tile_string.equals("waterbl"))	{decor_tiles[y][x] = new Tile(new Sprite(decorsheet, 2, 3), 16, false); System.out.println("waterbl");}
			else if (tile_string.equals("waterbc"))	{decor_tiles[y][x] = new Tile(new Sprite(decorsheet, 2, 4), 16, false); System.out.println("waterbc");}
			else if (tile_string.equals("waterbr"))	{decor_tiles[y][x] = new Tile(new Sprite(decorsheet, 2, 5), 16, false); System.out.println("waterbr");}
			
			else if (tile_string.equals("treetl"))	{decor_tiles[y][x] = new Tile(new Sprite(decorsheet, 0, 6), 16, false); System.out.println("treetl");}
			else if (tile_string.equals("treetc"))	{decor_tiles[y][x] = new Tile(new Sprite(decorsheet, 0, 7), 16, false); System.out.println("treetc");}
			else if (tile_string.equals("treetr"))	{decor_tiles[y][x] = new Tile(new Sprite(decorsheet, 0, 8), 16, false); System.out.println("treetr");}
			else if (tile_string.equals("treeml"))	{decor_tiles[y][x] = new Tile(new Sprite(decorsheet, 1, 6), 16, false); System.out.println("treeml");}
			else if (tile_string.equals("treemc"))	{decor_tiles[y][x] = new Tile(new Sprite(decorsheet, 1, 7), 16, false); System.out.println("treemc");}
			else if (tile_string.equals("treemr"))	{decor_tiles[y][x] = new Tile(new Sprite(decorsheet, 1, 8), 16, false); System.out.println("treemr");}
			else if (tile_string.equals("treebl"))	{decor_tiles[y][x] = new Tile(new Sprite(decorsheet, 2, 6), 16, false); System.out.println("treebl");}
			else if (tile_string.equals("treebc"))	{decor_tiles[y][x] = new Tile(new Sprite(decorsheet, 2, 7), 16, false); System.out.println("treebc");}
			else if (tile_string.equals("treebr"))	{decor_tiles[y][x] = new Tile(new Sprite(decorsheet, 2, 8), 16, false); System.out.println("treebr");}
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
