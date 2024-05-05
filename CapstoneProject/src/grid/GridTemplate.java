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
 * @version 5/5/24
 */
public abstract class GridTemplate {
	
	/** The grid array
	 */
	protected char[][] grid;
	
	
	/** Construct an empty 2D array with some default dimensions
	 */
	public GridTemplate()
	{
		grid = new char[20][20];
	}
	
	
	/** Construct an empty 2D array with dimensions width and height, then fill it with data from
	 * the file filename
	 * @param width The width of the grid.
	 * @param height The height of the grid.
	 * @param filename The text file to read from.
	 */
	public GridTemplate(int width, int height, String filename)
	{
		grid = new char[width][height];
		readData(filename, grid);
	}
	
	/**	Prints out a formatted version of this GridTemplate
	 */
	public String toString()
	{
		return null;
	}
	
	/** Draws this GridTemplate
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
		for (int i = 0; i < grid.length; i++) {
			float ry = rh * i + y;
			for (int j = 0; j < grid[0].length; j++) {
				float rx = rw * j + x;
				if (grid[i][j] == ' ') {
					marker.fill(255);
					marker.rect(rx, ry, rw, rh);
				} else if (grid[i][j] == '*') {
					marker.fill(0);
					marker.rect(rx, ry, rw, rh);
					marker.fill(255);
					marker.text('*', rx + rw/2 - 2, ry + rh/2 + 1);
				}else if(grid[i][j] == '#') {
					marker.fill(100);
					marker.rect(rx, ry, rw, rh);
					marker.fill(255);
					marker.text('#', rx + rw/2 - 1, ry + rh/2 + 1);
				}else if(grid[i][j] == 'X') {
					marker.fill(200);
					marker.rect(rx, ry, rw, rh);
					marker.fill(255);
					marker.text('X', rx + rw/2 - 1, ry + rh/2 + 1);
				}else if(grid[i][j] == '.') {
					marker.fill(150);
					marker.rect(rx, ry, rw, rh);
					marker.fill(255);
					marker.text('.', rx + rw/2 - 1, ry + rh/2 + 1);
				}
			}
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
		float rw = width / grid.length;
		float rh = height / grid[0].length;
		float distx = p.x - x;
		float disty = p.y - y;
		float xpos = distx / rw;
		float ypos = disty / rh;
		return new Point((int)ypos, (int)xpos);
	}
	
	/** Reads data from a text file and loads it into an array
	 * @param filename The text file to read from
	 * @param gameData The array to load the information into
	 */
	public void readData(String filename, char[][] gameData)
	{
		File dataFile = new File(filename);

		if (dataFile.exists()) {
			int count = 0;

			FileReader reader = null;
			Scanner in = null;
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
			
		} else {
			throw new IllegalArgumentException("Data file " + filename + " does not exist.");
		}
	}
	
}
