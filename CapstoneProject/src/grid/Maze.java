package grid;

import java.awt.Point;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Array;
import java.util.Scanner;

import processing.core.PApplet;

/** This class represents the game's grid
 * @author Boon Chew, Nikunj Govil, Yashasvi Chitela
 * @version 5/7/24
 */
public class Maze {
	
	/** The grid array
	 */
	protected char[][] grid;
	private double x, y;
	
	/** Construct an empty 2D array with some default dimensions
	 */
	public Maze()
	{
		grid = new char[20][20];
		x = 0;
		y = 0;
	}
	
	
	/** Construct an empty 2D array with dimensions width and height, then fill it with data from
	 * the file filename
	 * @param width The width of the grid
	 * @param height The height of the grid
	 * @param filename The text file to read from
	 */
	public Maze(int width, int height, String filename)
	{
		grid = new char[width][height];
		readData(filename, grid);
	}
	
	/**	Prints out a formatted version of this Maze
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
	
	/** Draws this Maze
	 * @param marker The PApplet used for drawing
	 * @param x The x-coordinate of the upper left corner of the grid drawing
	 * @param y The y-coordinate of the upper left corner of the grid drawing
	 * @param width The pixel width of the grid drawing
	 * @param height The pixel height of the grid drawing
	 */
	public void draw(PApplet marker, float x, float y, float width, float height)
	{
		float rw = width / grid[0].length;
		float rh = height / grid.length;
		for (int i = 0; i < grid.length; i++)
			for (int j = 0; j < grid[0].length; j++) {
				float rx = x + rw * j;
				float ry = y + rh * i;
				
//				doesn't draw tiles that are offscreen
				if (rx > marker.width || ry > marker.height || rx + rw < 0 || ry + rh < 0)
					continue;
				
				if (grid[i][j] == ' ')
					marker.fill(255);
				else if(grid[i][j] == '*')
					marker.fill(0);
				marker.noStroke();
				
				marker.rect(rx, ry, rw, rh);
			}
	}
	
	/** Converts click coordinates to index values that correspond to the grid
	 * @param p A Point object containing a graphical pixel coordinate
	 * @param x The x-coordinate of the upper left corner of the grid drawing
	 * @param y The y-coordinate of the upper left corner of the grid drawing
	 * @param width The pixel width of the grid drawing
	 * @param height The pixel height of the grid drawing
	 * @return A Point object representing a coordinate within the grid, or null if the pixel
	 * coordinate falls completely outside of the grid
	 */
	public Point clickToIndex(Point p, float x, float y, float width, float height)
	{
		return new Point((int)((p.getX() - x) / (width / grid.length)), 
				(int)((p.getY() - y) / (height / grid[0].length)));
	}
	
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
	
}
