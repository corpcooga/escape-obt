package grid;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;
import java.util.ArrayList;
import java.awt.geom.Rectangle2D;

import processing.core.PApplet;
import sprites.KeyFragment;
import sprites.Wall;

/** This class represents the game's grid
 * @author Boon Chew, Nikunj Govil, Yashasvi Chitela
 * @version 5/12/24
 */
public class Level extends Rectangle2D.Double {
	
	private char[][] grid;
	private ArrayList<Wall> walls;
	private ArrayList<KeyFragment> keyFragments;
	
	
	/** Construct an empty 2D array with dimensions width and height, then fill it with data from 
	 * the file filename
	 * @param width The width of the grid
	 * @param height The height of the grid
	 * @param filename The text file to read from
	 */
	public Level(int width, int height, String filename)
	{
		grid = new char[width][height];
		if (filename != null)
			readData(filename, grid);
		
		x = 0;
		y = 0;
		this.width = width * 100;
		this.height = height * 100;
		
		walls = new ArrayList<Wall>();
		keyFragments = new ArrayList<KeyFragment>();
		readSprites();
	}
	
	/** Construct an empty 2D array with some default dimensions
	 */
	public Level()
	{
		this(20, 20, null);
	}
	
	
	/** Gets all Walls in this Level
	 * @return An ArrayList containing all Walls in this Level
	 */
	public ArrayList<Wall> getWalls()
	{
		return walls;
	}
	
	public void readSprites()
	{
//		Sprite width and heights
		float sw = (float)width / grid[0].length;
		float sh = (float)height / grid.length;
		
		for (int i = 0; i < grid.length; i++)
			for (int j = 0; j < grid[0].length; j++)
			{
//				Sprite x and y's
				float sx = (float)x + sw * j;
				float sy = (float)y + sh * i;
				
//				Wall
				if (grid[i][j] == '#')
					walls.add(new Wall((int)sx, (int)sy, (int)sw, (int)sh));
				
//				Key Fragment
				else if (grid[i][j] == '*')
					keyFragments.add(new KeyFragment((int)sx, (int)sy, (int)sw, (int)sh));
			}
	}
	
	/** Draws this Level
	 * @param marker The PApplet used for drawing
	 * @param visible A Rectangle2D representing the visible in game
	 */
	public void draw(PApplet marker, Rectangle2D.Double visible)
	{
		for (Wall wall : walls)
			if (wall.inLimits(visible))
				wall.draw(marker);
		
//		float rw = (float)width / grid[0].length;
//		float rh = (float)height / grid.length;
		
//		for (int i = 0; i < grid.length; i++)
//			for (int j = 0; j < grid[0].length; j++)
//			{
//				float rx = (float)x + rw * j;
//				float ry = (float)y + rh * i;
//				
////				doesn't draw tiles that are offscreen
//				if (rx > visible.x + visible.width || ry > visible.y + visible.height || 
//						rx + rw < visible.x || ry + rh < visible.y)
//					continue;
//				
//				if (grid[i][j] == '#') {
//					Wall wall = new Wall((int)rx, (int)ry, (int)rw, (int)rh);
//					if (!walls.contains(wall))
//						walls.add(wall);
//					wall.draw(marker);
//				} else {
//					marker.fill(255);
//					marker.noStroke();
//					marker.rect(rx, ry, rw, rh);
//				}
//			}
	}
	
//	/** Converts click coordinates to index values that correspond to the grid
//	 * @param p A Point object containing a graphical pixel coordinate
//	 * @param x The x-coordinate of the upper left corner of the grid drawing
//	 * @param y The y-coordinate of the upper left corner of the grid drawing
//	 * @param width The pixel width of the grid drawing
//	 * @param height The pixel height of the grid drawing
//	 * @return A Point object representing a coordinate within the grid, or null if the pixel
//	 * coordinate falls completely outside of the grid
//	 */
//	public Point clickToIndex(Point p, float x, float y, float width, float height)
//	{
//		return new Point((int)((p.getX() - x) / (width / grid.length)), 
//				(int)((p.getY() - y) / (height / grid[0].length)));
//	}
	
	/** Reads data from a text file and loads it into an array
	 * @param filename The text file to read from
	 * @param gameData The array to load the information into
	 */
	public void readData(String filename, char[][] gameData)
	{
		File dataFile = new File(filename);

		if (dataFile.exists()) {
			FileReader reader = null;
			Scanner in = null;
			int count = 0;
			
			try {
					reader = new FileReader(dataFile);
					in = new Scanner(reader);
					
					while (in.hasNext()) {
						String line = in.nextLine();
						for(int i = 0; i < line.length(); i++)
							if (count < gameData.length && i < gameData[count].length)
								gameData[count][i] = line.charAt(i);
						count++;
					}

			} catch (IOException ex) {
				throw new IllegalArgumentException("Data file " + filename + " cannot be read.");
			} finally {
				if (in != null)
					in.close();
			}
		} else
			throw new IllegalArgumentException("Data file " + filename + " does not exist.");
	}
	
	/**	Prints out a formatted version of this Level
	 */
	public String toString()
	{
		StringBuffer out = new StringBuffer("");
		for (char[] row : grid) {
			for (char c : row)
				out.append(c);
			out.append("\n");
		}
		return out.toString();
	}
	
}
