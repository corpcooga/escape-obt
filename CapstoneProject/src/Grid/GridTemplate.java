package Grid;

import java.awt.Point;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Array;
import java.util.Scanner;

import processing.core.PApplet;

/*

Coded by: Yashasvi Chitela
Modified on: 4/23/2024

*/

public abstract class GridTemplate {

	/** Add a field to represent the grid. This time, make it a 2D array of characters.
	 *  It is OK to make this field protected so it can be directly accessed by subclasses.  **/
	protected char[][] grid;
	
	
	
	/**
	 * Construct an empty 2D array with some default dimensions.
	 */
	public GridTemplate() {
		grid = new char[20][20];
	}
	
	
	/**
	 * Construct an empty 2D array with dimensions width and height, then fill it with data from the file filename.
	 * 
	 * @param width The width of the grid.
	 * @param height The height of the grid.
	 * @param filename The text file to read from.
	 */
	public GridTemplate(int width, int height, String filename) {
		grid = new char[width][height];
		readData(filename, grid);
	}
	
	
	/**
	 * 
	 * Code a toString() method that nicely prints the grid to the commandline.
	 * 
	 */
	public String toString() {
		return null;
	}
	
	
	/**
	 * (Graphical UI)
	 * Draws the grid on a PApplet.
	 * The specific way the grid is depicted is up to the coder.
	 * 
	 * @param marker The PApplet used for drawing.
	 * @param x The x pixel coordinate of the upper left corner of the grid drawing. 
	 * @param y The y pixel coordinate of the upper left corner of the grid drawing.
	 * @param width The pixel width of the grid drawing.
	 * @param height The pixel height of the grid drawing.
	 */
	public void draw(PApplet marker, float x, float y, float width, float height) {
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
	
	
	/**
	 * (Graphical UI)
	 * Determines which element of the grid matches with a particular pixel coordinate.
	 * This supports interaction with the grid using mouse clicks in the window.
	 * 
	 * @param p A Point object containing a graphical pixel coordinate.
	 * @param x The x pixel coordinate of the upper left corner of the grid drawing. 
	 * @param y The y pixel coordinate of the upper left corner of the grid drawing.
	 * @param width The pixel width of the grid drawing.
	 * @param height The pixel height of the grid drawing.
	 * @return A Point object representing a coordinate within the grid, or null if the pixel coordinate
	 * falls completely outside of the grid.
	 */
	public Point clickToIndex(Point p, float x, float y, float width, float height) {
		float rw = width / grid.length;
		float rh = height / grid[0].length;
		float distx = p.x - x;
		float disty = p.y - y;
		float xpos = distx/rw;
		float ypos = disty/rh;
		return new Point((int)ypos, (int)xpos);
	}
	


	public void readData (String filename, char[][] gameData) {
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
