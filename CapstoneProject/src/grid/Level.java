package grid;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;
import java.util.ArrayList;
import java.awt.Point;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;

import processing.core.PApplet;
import sprites.*;

/** This class represents the game's grid
 * @author Boon Chew, Nikunj Govil, Yashasvi Chitela
 * @version 5/16/24
 */
public class Level extends Rectangle2D.Double {
	
	public static final int TILE_SIZE = 80;
	
	private ArrayList<Wall> walls;
	private ArrayList<KeyFragment> keyFragments;
	private ArrayList<Tickler> ticklers;
	private Player player;
	private Exit exit;
	
	private char[][] grid;
	private int numLevel, levelFragments;
	
	
	/** Constructs a Level of a specified number
	 * @param numLevel
	 */
	public Level(int numLevel)
	{
		setupLevel(numLevel);
	}
	
	/** Construct a level 1 Level
	 */
	public Level()
	{
		this(1);
	}
	
	
	/** Sets up this Level with the specified number level
	 * @param numLevel The level number to set up
	 */
	public void setupLevel(int numLevel)
	{
		String fileName = "resources/levels/level" + numLevel + ".txt";
		Point dimensions = readDataDimensions(fileName);
		
		grid = new char[dimensions.x][dimensions.y];
		readData(fileName, grid);
		
		x = 0;
		y = 0;
		width = dimensions.x * TILE_SIZE;
		height = dimensions.y * TILE_SIZE;
		
		walls = new ArrayList<Wall>();
		keyFragments = new ArrayList<KeyFragment>();
		ticklers = new ArrayList<Tickler>();
		loadSprites();
		
		levelFragments = keyFragments.size();
		this.numLevel = numLevel;
	}
	
	/** Gets a Sprite's coordinates in this Level using their literal coordinates
	 * @param sprite the Sprite to get the Level coordinates of
	 * @return a Point object containing array coordinates of a Sprite
	 */
	public Point getSpriteArrayCoordinates(Sprite sprite)
	{
		Point2D.Double spriteCenter = sprite.getCenter();
		int realX = (int)((spriteCenter.getX() + x) / TILE_SIZE);
		int realY = (int)((spriteCenter.getY() + y) / TILE_SIZE);
		return new Point(realX, realY);
	}
	
	/** Gets the distance between two Sprites in this Level
	 * @param s1 The first Sprite used to calculate the distance
	 * @param s2 The second Sprite used to calculate the distance
	 * @return The maximum of the x-distance and y-distance between the Sprites
	 */
	public int getSpriteDistance(Sprite s1, Sprite s2)
	{
		Point coord1 = getSpriteArrayCoordinates(s1);
		Point coord2 = getSpriteArrayCoordinates(s2);
		int xDist = Math.abs(coord1.x - coord2.x);
		int yDist = Math.abs(coord1.y - coord2.y);
		return Math.max(xDist, yDist);
	}
	
	/** Draws this Level and handles Sprite behavior
	 * @param marker The PApplet used for drawing
	 * @param visible A Rectangle2D representing the visible in-game area
	 */
	public void draw(PApplet marker, Rectangle2D.Double visible)
	{
		player.act(this);
		
		int playerVision = player.getVisionRange();
		
		if (getSpriteDistance(player, exit) <= playerVision)
			exit.draw(marker);
		for (Wall wall : walls)
			if (getSpriteDistance(player, wall) <= playerVision)
				wall.draw(marker);
		for (KeyFragment key : keyFragments)
			if (getSpriteDistance(player, key) <= playerVision)
				key.draw(marker);
		for (Tickler tickler : ticklers) {
			tickler.act(this);
			if (getSpriteDistance(player, tickler) <= playerVision)
				tickler.draw(marker);
		}
		player.draw(marker);
	}
	
	/** Removes the KeyFragment at the specified index, then updates exit status
	 * @param idx The index of the KeyFragment to remove
	 */
	public void removeKeyFragment(int idx)
	{
		keyFragments.remove(idx);
		if (player.getNumFragments() == levelFragments)
			exit.open();
	}
	
	/** Gets all Walls in this Level
	 * @return An ArrayList containing all Walls in this Level
	 */
	public ArrayList<Wall> getWalls()
	{
		return walls;
	}
	
	/** Gets all KeyFragments in this Level
	 * @return An ArrayList containing all KeyFragments in this Level
	 */
	public ArrayList<KeyFragment> getKeyFragments()
	{
		return keyFragments;
	}
	
	/** Gets all Ticklers in this Level
	 * @return An ArrayList containing all Ticklers in this Level
	 */
	public ArrayList<Tickler> getTicklers()
	{
		return ticklers;
	}
	
	/** Gets the Player in this Level
	 * @return A Player object that represents this Level's Player
	 */
	public Player getPlayer()
	{
		return player;
	}
	
	/** Gets the Exit in this Level
	 * @return An Exit object that represents this Level's Exit
	 */
	public Exit getExit()
	{
		return exit;
	}
	
	/** Gets the level number of this Level
	 * @return The level number of this Level
	 */
	public int getLevel()
	{
		return numLevel;
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
	
	/** Loads all Sprites from the grid to the corresponding ArrayList
	 */
	private void loadSprites()
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
					keyFragments.add(new KeyFragment(
							(int)sx + (int)sw / 4, (int)sy + (int)sh / 4, (int)sw / 2, (int)sh / 2));
				
//				Tickler
				else if (grid[i][j] == 'T')
					ticklers.add(new Tickler(
							(int)sx + (int)sw / 4, (int)sy + (int)sh / 4, (int)sw / 2, (int)sh / 2));
				
//				Player
				else if (grid[i][j] == 'P')
					player = new Player(
							(int)sx + (int)(sw * 0.3), (int)sy + (int)(sh * 0.3), 
							(int)(sw * 0.4), (int)(sh * 0.4));
				
//				Exit
				else if (grid[i][j] == 'X')
					exit = new Exit((int)sx, (int)sy, (int)sw, (int)sh);
			}
	}
	
	/** Reads data from a text file and loads it into an array
	 * @param filename The text file to read from
	 * @param gameData The array to load the information into
	 */
	private void readData(String filename, char[][] gameData)
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
	
	/** Gets the dimensions of a data file
	 * @param filename The text file to read from
	 * @return A Point object containing the bottom right corner of the file
	 */
	private Point readDataDimensions(String filename)
	{
		File dataFile = new File(filename);
		
		if (dataFile.exists()) {
			int rows = 0, cols = 0;
			Scanner in = null;
			
			try {
				in = new Scanner(new FileReader(dataFile));
				String lastLine = "";
				
				while (in.hasNext()) {
					lastLine = in.nextLine();
					rows++;
				}
				cols = lastLine.length();
				return new Point(rows, cols);

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
